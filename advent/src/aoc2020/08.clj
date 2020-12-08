(ns aoc2020.08
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def input (slurp (io/resource "aoc2020/08.txt")))

(def data (str/split-lines input))

(defn accf [arg state]
  (let [[_ m num] (re-find #"(\+|\-)(\d+)" arg)
        num (Integer/parseInt num)]
    (if (= m "+")
      (+ state num)
      (- state num))))

(loop [state 0
       pc 0
       called #{}]
  (if (contains? called pc)
    state
    (let [[op arg] (str/split (nth data pc) #" ")]
      (condp = op
            "nop" (recur state (inc pc) (conj called pc))
            "acc" (recur (accf arg state) (inc pc) (conj called pc))
            "jmp" (recur state (accf arg pc) (conj called pc))))))

