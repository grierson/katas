(ns aoc2020.08
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def input (slurp (io/resource "aoc2020/08.txt")))

(def data (str/split-lines input))

(defn solve [lines state pc called]
  (if (contains? called pc)
    state
    (let [[_ op m num] (re-find #"(nop|acc|jmp) (\+|\-)(\d+)" (nth lines pc))
          f (resolve (symbol m))
          num (Integer/parseInt num)]
      (condp = op
        "nop" (recur lines state (inc pc) (conj called pc))
        "acc" (recur lines (f state num) (inc pc) (conj called pc))
        "jmp" (recur lines state (f pc num) (conj called pc))))))

(solve data 0 0 #{})
