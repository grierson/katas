(ns aoc2021.01
  (:require [advent.core :refer [count-if]]
            [clojure.java.io :as io]
            [clojure.string :as str]))

(def data (str/split-lines (slurp (io/resource "aoc2021/01.txt"))))
(def nums (map #(Long/parseLong %) data))

(defn scan [numbers]
  (->> numbers
       (partition 2 1)
       (map (fn [[x y]] (< x y)))
       (count-if true?)))

(defn scan2 [numbers]
  (scan (map #(apply + %) (partition 3 1 numbers))))

(comment
  (scan nums)
  (scan2 nums))