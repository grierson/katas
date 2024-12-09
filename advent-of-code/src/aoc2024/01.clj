(ns aoc2024.01
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(def data (slurp (io/resource "aoc2024/01.txt")))

(def sample
  "3   4
4   3
2   5
1   3
3   9
3   3")

(defn numbers [s]
  (map parse-long (re-seq #"\d+" s)))

(defn parse [data]
  (->> (str/split-lines data)
       (map numbers)))

(defn distance [left right] (abs (- left right)))

(defn solve1 [data]
  (let [parsed-data (parse data)
        left (sort (map first parsed-data))
        right (sort (map second parsed-data))
        distances (map distance left right)]
    (reduce + distances)))

(defn solve2 [data]
  (let [parsed-data (parse data)
        left (sort (map first parsed-data))
        right (frequencies (map second parsed-data))
        distances (map (fn [number] (* number (get right number 0))) left)]
    (reduce + distances)))

(comment
  (solve1 sample)
  (solve1 data)
  (solve2 sample)
  (solve2 data))

;; # Plan
;;
;; 3   4
;; 4   3
;; 2   5
;; 1   3
;; 3   9
;; 3   3
;;
;; -> structure
;;
;; [
;;    [3 4 2 1 3 3]
;;    [4 3 5 3 9 3]
;; ]
;;
;; -> sort left right
;;
;; [
;;    [1 2 3 3 3 4]
;;    [3 3 3 4 5 9]
;; ]
;;
;; -> map distance lefts rights
;;
;; [2 1 0 1 2 5]
;;
;; -> total
;;
;; reduce +
