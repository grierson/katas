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
  (if #p (contains? #p called #p pc)
    state
    (let [[op arg] #p (str/split (nth data pc) #" ")]
      (condp = op
            "nop" (recur state (inc pc) (conj called (inc pc)))
            "acc" (recur (accf arg state) (inc pc) (conj called (inc pc)))
            "jmp" (recur state (accf arg pc) (conj called (inc pc)))))))

