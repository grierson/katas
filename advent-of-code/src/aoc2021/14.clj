(ns aoc2021.14
  (:require [clojure.string :as str]))

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
  (let [state (first input)
        rules (drop 2 input)]
    {:state state
     :rules (into {} (map (fn [[pair element]]
                            [(str/trim pair) (str/trim element)])
                          (map #(str/split % #"->") rules)))}))

(def data (parse example))

(reduce (fn [state [left right]] (str state left (get (:rules data) (str left right) ""))) "" (partition 2 1 "NNCB"))
