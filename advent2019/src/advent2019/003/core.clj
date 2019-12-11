(ns advent2019.003.core)


(defn draw [wire])
(defn overlaps [wire1 wire2])
(defn nearest [overlaps])

(defn intersection [wire1 wire2]
  (let [dw1 (draw wire1)
        dw2 (draw wire2)
        crossovers (overlaps dw1 dw2)
        closest (nearest crossovers)]
    closest))
