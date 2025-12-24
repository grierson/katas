(ns aoc2025.02
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(def sample-data "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124")
(def data (slurp (io/resource "aoc2025/02.txt")))

(defn parse [input]
  (let [input (str/trim-newline input)
        csv (str/split input #",")]
    (map (fn [str-range]
           (let [[start end] (str/split str-range #"-")]
             [(parse-long start) (parse-long end)])) csv)))

(defn duplicate? [number]
  (let [str-number (str number)
        half (quot (count str-number) 2)
        left (take half str-number)
        right (drop half str-number)]
    (= left right)))

(defn find-duplicates [[start end]]
  (let [spread (range start (inc end))]
    (filter duplicate? spread)))

(defn solve1 [input]
  (apply + (mapcat find-duplicates (parse input))))

(comment
  ((requiring-resolve 'hashp.install/install!))
  (solve1 data))

(defn divisors [number]
  (filter (fn [digit] (zero? (mod number digit))) (range 1 (inc number))))

(defn repeated?
  [number]
  (let [divs (divisors (count number))]
    (some
     (fn [div]
       (let [parts (partition div number)]
         (and
          (>= (count parts) 2)
          (apply = parts))))
     divs)))

(defn find-repeated [[start end]]
  (let [ids-range (range start (inc end))]
    (filter (comp repeated? str) ids-range)))

(defn solve2 [input]
  (apply + (mapcat find-repeated (parse input))))

(comment
  (solve2 sample-data)
  (solve2 data))
