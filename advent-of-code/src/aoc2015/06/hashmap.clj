(ns aoc2015.06.hashmap
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn draw [[x1 y1] [x2 y2]]
  (for [x (range x1 (inc x2))
        y (range y1 (inc y2))]
    [x y]))

(defn turn-on [state coords]
  (if (contains? state coords)
    (update state coords inc)
    (assoc state coords 1)))

(defn turn-off [state coords]
  (if (contains? state coords)
    (update state coords (fn [x] (if (zero? x) x (dec x))))
    (assoc state coords 0)))

(defn toggle [state coords]
  (if (contains? state coords)
    (update state coords + 2)
    (assoc state coords 2)))

(defn get-action [line]
  (cond
    (str/starts-with? line "turn on") turn-on
    (str/starts-with? line "turn off") turn-off
    (str/starts-with? line "toggle") toggle))

(defn get-coords [line]
  (let [[[_ x y] [_ a b]] (re-seq #"(\d+),(\d+)" line)]
    [[(parse-long x) (parse-long y)]
     [(parse-long a) (parse-long b)]]))

(defn parse-line [line]
  {:action (get-action line)
   :coords (get-coords line)})

(defn update-state [grid {:keys [action coords]}]
  (let [[p1 p2] coords
        f action
        coll (draw p1 p2)]
    (reduce (fn [state x] (f state x)) grid coll)))

(defn total [state]
  (reduce + (vals state)))

(comment
  (def data (map parse-line (str/split-lines (slurp (io/resource "aoc2015/06.txt")))))
  (time (total (reduce update-state {} data))))
