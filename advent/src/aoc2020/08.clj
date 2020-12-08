(ns aoc2020.08
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def input (slurp (io/resource "aoc2020/08.txt")))

(def data (str/split-lines input))

(defn accf [state arg num]
  (let [num (Integer/parseInt num)]
    (if (= arg "+")
      (+ state num)
      (- state num))))

(loop [state 0
       pc 0
       called #{}]
  (if (contains? called pc)
    state
    (let [[_ op m num] (re-find #"(nop|acc|jmp) (\+|\-)(\d+)" (nth data pc))]
      (condp = op
        "nop" (recur state (inc pc) (conj called pc))
        "acc" (recur (accf state m num) (inc pc) (conj called pc))
        "jmp" (recur state (accf pc m num) (conj called pc))))))

