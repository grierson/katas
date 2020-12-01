(ns aoc2020.01
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def data (line-seq (io/reader (io/resource "aoc2020/01.txt"))))
(def nums (map #(Long/parseLong %) data))

(first (for [x nums
             y nums
             :when (= 2020 (+ x y))]
         (* x y)))

(first (for [x nums
             y nums
             z nums
             :when (= 2020 (+ x y z))]
         (* x y z)))
