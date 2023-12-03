(ns aoc2023.03
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(require 'hashp.core)

(def data (slurp (io/resource "aoc2023/03.txt")))

(def sample-data
  "467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598..")

(defn parse-line-numbers
  [line]
  (let [numbers (re-seq #"\d+" line)]
    (reduce (fn [state number]
              (let [start (str/index-of line number)]
                (assoc state
                       [start
                        (dec (+ start (count number)))]
                       (parse-long number))))
            {}
            numbers)))

(defn parse-numbers
  [lines]
  (reduce conj {}
          (map-indexed
           (fn [idx line] {idx (parse-line-numbers line)})
           lines)))

(comment
  (parse-numbers (str/split-lines sample-data)))

(defn icon? [c]
  (re-matches #"[^a-zA-Z0-9.]" (str c)))

(defn parse-line-icons
  [row line]
  (filter identity
          (map-indexed
           (fn [column c]
             (when (icon? c) [row column]))
           line)))

(defn parse-icons
  [lines]
  (reduce concat []
          (map-indexed parse-line-icons lines)))

(defn surround-locations
  [[x y]]
  [[(dec x) (dec y)] [(dec x) y] [(dec x) (inc y)]
   [x (dec y)] [x (inc y)]
   [(inc x) (dec y)] [(inc x) y] [(inc x) (inc y)]])

(defn within-range? [number min-value max-value]
  (and (<= min-value number)
       (<= number max-value)))

(defn get-surrounding-number
  [number-locations [row column]]
  (let [columns (get number-locations row)]
    (reduce
     (fn [state [[start end] value]]
       (if (and (within-range? column start end)
                (nil? (get state [row [start end]])))
         (assoc state [row [start end]] value)
         state))
     {}
     columns)))

(defn get-surrounding-numbers
  [numbers icon]
  (->> (surround-locations icon)
       (map (fn [location] (get-surrounding-number numbers location)))
       (filter not-empty)
       (reduce conj {})))

(defn solve
  [data]
  (let [lines (str/split-lines data)
        icons (parse-icons lines)
        numbers (parse-numbers lines)
        surrounding-numbers (map (fn [icon] (get-surrounding-numbers numbers icon)) icons)
        surrounding-numbers (reduce conj {} surrounding-numbers)
        surrounding-numbers (vals surrounding-numbers)]
    (reduce + 0 surrounding-numbers)))

(comment
  (solve sample-data)
  (solve data))
