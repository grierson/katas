(ns aoc2015.06.2dvector
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn make-grid [columns rows]
  (->> (repeat columns false)
       (into [])
       (repeat rows)
       (into [])))

(defn turn-on [grid coords]
  (assoc-in grid coords true))

(defn turn-off [grid coords]
  (assoc-in grid coords false))

(defn toggle [grid coords]
  (update-in grid coords not))

(defn draw [[x1 y1] [x2 y2]]
  (for [x (range x1 (inc x2))
        y (range y1 (inc y2))]
    [x y]))

(defn update-state [grid {:keys [action coords]}]
  (let [[p1 p2] coords
        f action
        coll (draw p1 p2)]
    (reduce (fn [state x] (f state x)) grid coll)))

(update-state (make-grid 10 10) {:action turn-on
                                 :coords [[0 0] [2 2]]})

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

(defn amount-on [grid]
  (count (filter true? (flatten grid))))

(comment
  (def data (map parse-line (str/split-lines (slurp (io/resource "aoc2015/06.txt")))))
  (time (amount-on (reduce update-state (make-grid 1000 1000) data))))
