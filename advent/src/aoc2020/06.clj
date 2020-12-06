(ns aoc2020.06
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.set :as set]))

(def input  (slurp (io/resource "aoc2020/06.txt")))

(defn solve [line]
  ((comp count set #(str/replace % "\n" "")) line))

(comment
  (reduce + (map solve (str/split input #"\R\R"))))

(defn solve2 [line]
  "My bad implementation of intersection before I knew about it :)"
  (let  [y (frequencies line)
         groups (inc (get y \newline 0))]
    (count (filter (fn [[_ cnt]] (= groups cnt)) y))))

(defn solve22 [line]
  (->> line
       str/split-lines
       (map set)
       (apply set/intersection)
       count))

(comment
  (reduce + (map solve22 (str/split input #"\R\R"))))
