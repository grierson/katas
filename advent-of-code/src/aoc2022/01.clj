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

(def data (slurp (io/resource "aoc2022/01.txt")))

(defn parse [data]
  (map (fn [elv] (map parse-long elv)) (map str/split-lines (str/split data #"\n\n"))))

(defn most-calories [data]
  (apply max (map #(reduce + %) (parse data))))

(defn three-most-calories [data]
  (reduce + (take 3 (reverse (sort (map #(reduce + %) (parse data)))))))

(comment
  (most-calories sample)
  (most-calories data)
  (three-most-calories sample)
  (three-most-calories data))
