(ns aoc2015.06-2
  (:require [clojure.string :as str]
            [clojure.set :as set]
            [clojure.java.io :as io]))

(defn draw [[x1 y1] [x2 y2]]
  (set (for [x (range x1 (inc x2))
             y (range y1 (inc y2))]
         [x y])))

(def turn-on set/union)
(def turn-off set/difference)
(def get-on set/intersection)

(defn get-off [state lights]
  (set/difference lights state))

(defn toggle [state lights]
  (let [on (get-on state lights)
        off (get-off state lights)]
    (-> state
        (turn-on off)
        (turn-off on))))

(defn update-state [grid {:keys [action coords]}]
  (let [[p1 p2] coords
        lights (draw p1 p2)
        f action]
    (f grid lights)))

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

(comment
  (def data (map parse-line (str/split-lines (slurp (io/resource "aoc2015/06.txt")))))
  (time (count (reduce update-state #{} data))))

