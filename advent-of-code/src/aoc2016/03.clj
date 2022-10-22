(ns aoc2016.03
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn valid? [[a b c]]
  (and (< c (+ a b))
       (< a (+ b c))
       (< b (+ c a))))

(defn parse-line [line]
  ((comp (fn [nums] (map parse-long nums)) #(re-seq #"\d+" %)) line))

(defn- parse-lines [lines]
  (map parse-line lines))

(defn- parse-lines2 [lines]
  (let [lines (map parse-line lines)]
    (->> (partition 3 lines)
         (map (fn [lines]
                [(map first lines)
                 (map second lines)
                 (map #(nth % 2) lines)]))
         (apply concat))))

(defn count-valid [lines]
  (count (filter valid? lines)))

(defn solve1 [data]
  (count-valid (parse-lines data)))

(defn solve2 [data]
  (count-valid (parse-lines2 data)))

(comment
  (def lines (str/split-lines (slurp (io/resource "aoc2016/03.txt"))))
  (solve1 lines)
  (solve2 lines)
  (parse-lines lines)
  (parse-lines2 lines))

