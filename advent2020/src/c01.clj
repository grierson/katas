(ns c01
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def data (slurp (io/resource "01.txt")))

(defn parse-int [s]
  (Integer. (re-find #"\d+" s)))

(def nums (map parse-int (str/split-lines data)))

(for [x nums
      y (rest nums)
      :when (= 2020 (+ x y))]
  (* x y))
