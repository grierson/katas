(ns c01
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn parse-int [s]
  (Integer. (re-find #"\d+" s)))

(def data (-> "2020/01.txt"
              io/resource
              slurp
              str/split-lines))

(def nums (map parse-int data))

(first (for [x nums
             y (rest nums)
             :when (= 2020 (+ x y))]
         (* x y)))
