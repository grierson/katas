(ns aoc2015.08
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(defn total [c]
  (if (= \" c)
    2
    1))

(defn size [s]
  {:memory (- (count s) 2)
   :literal (reduce + (map total s))})

(comment
  (def data (str/split-lines (slurp (io/resource "aoc2015/08-2.txt")))))
