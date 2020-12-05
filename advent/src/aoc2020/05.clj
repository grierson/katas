(ns aoc2020.05
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def input (slurp (io/resource "aoc2020/05.txt")))

(defn line->boardingpass [line]
  (let [[_ row column] (re-find #"([F|B]{7})([L|R]{3})" line)]
    [row column]))

(map line->boardingpass (str/split-lines input))

(defn get-row [half [start stop]]
  (if (= (inc start) stop)
    (case half
      \F start
      \B stop)
    (let [range (- stop start)
          middle (quot range 2)]
      (case half
        \F [start (dec (- stop middle))]
        \B [(inc (+ start middle)) stop]))))

(defn get-column [half [start stop]]
  (if (= (inc start) stop)
    (case half
      \L start
      \R stop)
    (let [range (- stop start)
          middle (quot range 2)]
      (case half
        \L [start (dec (- stop middle))]
        \R [(inc (+ start middle)) stop]))))

(defn get-row-total [state [h & t]]
  (if h
    (recur (get-row h state) t)
    state))

(defn get-column-total [state [h & t]]
  (if h
    (recur (get-column h state) t)
    state))

(defn get-seatid [[rows columns]]
  (let [row-total (get-row-total [0 127] rows)
        column-total (get-column-total [0 7] columns)]
    (+ (* row-total 8) column-total)))

(comment
  (apply max (map (comp get-seatid line->boardingpass) (str/split-lines input))))

(->> (str/split-lines input)
     (map (comp get-seatid line->boardingpass))
     sort
     (partition-all 2 1)
     (some (fn [[low high]]
             (let [nxt (inc low)]
               (when (not= nxt high)
                 nxt)))))

