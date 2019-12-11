(ns advent2019.003.core
  (:require [clojure.string :as str]))

(defn char->direction [c]
  (case c
    \R :right
    \L :left
    \U :up
    \D :down))

(defn str->move [[direction & amount]]
  (let [distance (apply str amount)]
    [(char->direction direction) (Integer/parseInt distance)]))

(defn parse [wire]
  (let [moves (str/split wire #",")]
    (map str->move moves)))

(defn update-location [direction [x y]]
  (case direction
    :right [(inc x) y]
    :left [(dec x) y]
    :up [x (dec y )]
    :down [x (inc y)])) 

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

(defn draw [wire]
  (-> wire
      parse
      mapping))

(defn overlaps [wire1 wire2])
(defn nearest [overlaps])

(defn intersection [wire1 wire2]
  (let [dw1 (draw wire1)
        dw2 (draw wire2)
        crossovers (overlaps dw1 dw2)
        closest (nearest crossovers)]
    closest))
