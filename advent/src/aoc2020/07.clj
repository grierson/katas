(ns aoc2020.07
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def input (slurp (io/resource "aoc2020/07sample.txt")))

(def temp (-> input
              str/split-lines))

(defn get-colors [line]
  (let [result (re-seq #"(\w+) (\w+) (bag|bags)" line)
        colors (map (fn [[_ c1 c2 _]] (str/join #" " [c1 c2])) result)
        deps (rest colors)]
    (if (= deps '("no other"))
      [(first colors) nil]
      [(first colors) (set deps)])))

(into (hash-map) (map get-colors temp))

