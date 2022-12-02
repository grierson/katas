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
  (->> (str/split data #"\n\n")
       (map str/split-lines)
       (map (fn [elv] (map parse-long elv)))
       (map #(reduce + %))))

(defn most-calories [elvs]
  (apply max elvs))

(defn three-most-calories [elvs]
  (reduce + (take 3 (sort > elvs))))

(comment
  (most-calories (parse sample))
  (most-calories (parse data))
  (three-most-calories (parse sample))
  (three-most-calories (parse data)))
