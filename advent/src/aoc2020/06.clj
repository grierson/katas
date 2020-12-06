(ns aoc2020.06
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def input  (slurp (io/resource "aoc2020/06.txt")))

(comment
  (reduce + (map (comp count set #(str/replace % "\n" "")) (str/split input #"\R\R"))))

(defn solve2 [line]
  (let  [y (frequencies line)
         groups (inc (get y \newline 0))]
    (count (filter (fn [[_ cnt]] (= groups cnt)) y))))


(reduce + (map solve2 (str/split input #"\R\R")))
