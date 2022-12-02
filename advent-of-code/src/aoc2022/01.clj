(ns aoc2022.01
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def sample
"1000
2000
3000

4000

5000
6000

7000
8000
9000

10000")

(def data (str/split-lines (slurp (io/resource "aoc2022/01.txt"))))
(def nums (map #(Long/parseLong %) data))

(str/split-lines sample)
