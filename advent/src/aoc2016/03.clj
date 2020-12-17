(ns aoc2016.03
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn valid? [[a b c]]
  (and (< c (+ a b))
       (< a (+ b c))
       (< b (+ c a))))


(defn parse-line [line]
  ((comp (fn [nums] (map #(Integer/parseInt %) nums)) #(re-seq #"\d+" %)) line))

(comment
  (def lines (str/split-lines (slurp (io/resource "aoc2016/03.txt"))))
  (count (filter valid? (map parse-line lines))))

