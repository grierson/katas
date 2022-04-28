(ns aoc2021.14
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(require 'hashp.core)

(def example
  ["NNCB"
   ""
   "CH -> B"
   "HH -> N"
   "CB -> H"
   "NH -> C"
   "HB -> C"
   "HC -> B"
   "HN -> C"
   "NN -> C"
   "BH -> H"
   "NC -> B"
   "NB -> B"
   "BN -> B"
   "BB -> N"
   "BC -> B"
   "CC -> N"
   "CN -> C"])

(defn parse [input]
  (let [polymer (first input)
        rules (drop 2 input)]
    {:polymer polymer
     :rules   (into {} (map (fn [[pair element]]
                              [(str/trim pair) (str/trim element)])
                            (map #(str/split % #"->") rules)))}))

(defn step [rules polymer]
  (let [pairs (partition 2 1 (str polymer "?"))]
    (reduce (fn [state [left right]] (str state left (get rules (str left right) ""))) "" pairs)))

(defn steps [rules n polymer]
  (loop [i 0
         polymer polymer]
    (if (= i n)
      polymer
      (recur (inc i) (step rules polymer)))))

(defn get-diff [n {:keys [rules polymer]}]
  (let [freq (frequencies (steps rules n polymer))
        maximum (val (apply max-key val freq))
        minimum (val (apply min-key val freq))]
    (- maximum minimum)))

(comment
  (get-diff 10 (parse example))
  (def data (str/split-lines (slurp (io/resource "aoc2021/14.txt"))))
  (get-diff 10 (parse data))
  (get-diff 40 (parse data))) ;; takes too long :(

