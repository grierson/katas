(ns 2019.03
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn str->move [[direction & amount]]
  (let [distance (apply str amount)]
    [direction (Integer/parseInt distance)]))

(defn parse [wire]
  (let [moves (str/split wire #",")]
    (map str->move moves)))

(defn update-location [direction [x y]]
  (case direction
    \R [(inc x) y]
    \L [(dec x) y]
    \U [x (dec y)]
    \D [x (inc y)]))

(defn apply-move [location [direction amount]]
  (->> (iterate #(update-location direction %) location)
       (drop 1)
       (take amount)
       (vec)))

(defn mapping
  ([moves]
   (mapping [[0 0]] moves))
  ([path [m & ms]]
   (if (some? m)
     (mapping (concat path (apply-move (last path) m)) ms)
     path)))

(defn trace [wire]
  (-> wire
      parse
      mapping))

(defn overlaps [wire1 wire2]
  (clojure.set/intersection (set wire1) (set wire2)))

(defn distance [[x y]]
  (+ (Math/abs x)
     (Math/abs y)))

(defn nearest [crossover]
  (->> crossover
       (map distance)
       sort
       second))

(defn solve [wire1 wire2]
  (let [w1 (trace wire1)
        w2 (trace wire2)
        crossovers (overlaps w1 w2)]
    (nearest crossovers)))


(defn problem1 []
  (let [[wire1 wire2] (-> "003.txt"
                          io/resource
                          slurp
                          str/split-lines)]
    (solve wire1 wire2)))

(problem1) 
