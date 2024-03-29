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

(defn gear-icon? [c]
  (re-matches #"[*]" (str c)))

(defn parse-line-gear-icons
  [row line]
  (filter identity
          (map-indexed
           (fn [column c]
             (when (gear-icon? c) [row column]))
           line)))

(defn parse-gear-icons
  [lines]
  (reduce concat [] (map-indexed parse-line-gear-icons lines)))

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
  (reduce concat [] (map-indexed parse-line-icons lines)))

(defn parse-line-numbers
  [line]
  (:numbers
   (reduce
    (fn [state number]
      (let [start (str/index-of (:line state) number)
            state (update state :numbers conj {[start (dec (+ start (count number)))]
                                               (parse-long number)})
            state (update state
                          :line
                          #(str/replace-first % number (apply str (repeat (count number) "."))))]
        state))
    {:line line
     :numbers {}}
    (re-seq #"\d+" line))))

(defn parse-numbers
  [lines]
  (reduce conj {}
          (map-indexed
           (fn [idx line] {idx (parse-line-numbers line)})
           lines)))

(comment
  (parse-numbers (str/split-lines sample-data)))

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
  {icon
   (->> (surround-locations icon)
        (map (fn [location] (get-surrounding-number numbers location)))
        (filter not-empty)
        (reduce conj {}))})

(defn solve
  [data]
  (let [lines (str/split-lines data)
        icons (parse-icons lines)
        numbers (parse-numbers lines)
        surrounding-numbers (reduce (fn [state icon] (merge state (get-surrounding-numbers numbers icon))) {} icons)
        surrounding-numbers (flatten (map vals (vals surrounding-numbers)))]
    (reduce + 0 surrounding-numbers)))

(defn solve2
  [data]
  (let [lines (str/split-lines data)
        gear-icons (parse-gear-icons lines)
        numbers (parse-numbers lines)
        icon-with-numbers (reduce (fn [state icon] (merge state (get-surrounding-numbers numbers icon))) {} gear-icons)
        gears (into {} (filter (fn [[_ nums]] (>= (count nums) 2)) icon-with-numbers))
        gear-values (map (fn [gear] (apply * (vals gear))) (vals gears))]
    (reduce + 0 gear-values)))

(comment
  (solve sample-data)
  (solve data)
  (solve2 sample-data)
  (solve2 data))
