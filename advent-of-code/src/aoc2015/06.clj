(ns aoc2015.06
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn get-action [line]
  (cond
    (str/starts-with? line "turn on") :on
    (str/starts-with? line "turn off") :off
    (str/starts-with? line "toggle") :toggle))

(defn get-coords [line]
  (let [[[_ x y] [_ a b]] (re-seq #"(\d+),(\d+)" line)]
    [[(parse-long x) (parse-long y)]
     [(parse-long a) (parse-long b)]]))

(defn parse-line [line]
  {:action (get-action line)
   :coords (get-coords line)})

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

(defn get-range [[p1x p1y] [p2x p2y]]
  (for [x (range p1x (inc p2x))
        y (range p1y (inc p2y))]
    [x y]))

(defn action-f [action]
  (case action
    :on turn-on
    :off turn-off
    :toggle toggle))

(defn update-state [grid {:keys [action coords]}]
  (let [[p1 p2] coords
        f (action-f action)
        coll (get-range p1 p2)]
    (reduce (fn [state x] (f state x)) grid coll)))

(defn amount-on [grid]
  (count (filter true? (flatten grid))))

(comment
  (def data (map parse-line (str/split-lines (slurp (io/resource "aoc2015/06.txt")))))
  (amount-on (reduce update-state (make-grid 1000 1000) data)))
