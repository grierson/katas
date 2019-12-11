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

(defn mapping [parsed])

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
