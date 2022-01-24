(ns aoc2015.05
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn three-vowels? [line]
  (<= 3 (count (filter #{\a \e \i \o \u} line))))

(defn contains-pair? [line]
  (let [[a & xs] line
        [_ b] line]
    (cond
      (nil? b) false
      (= a b) true
      :else (recur xs))))

(defn contains-no-bad-pairs? [line]
  (nil? (re-find #"(ab|cd|pq|xy)" line)))

(defn nice? [line]
  (and (three-vowels? line)
       (contains-pair? line)
       (contains-no-bad-pairs? line)))

(defn solve1 [lines]
  (count (filter nice? lines)))

(defn seperated-pair? [line]
  (true? (some (fn [[a _ b]] (= a b)) (partition 3 1 line))))

(defn two-pairs? [line]
  (frequencies (partition 2 1 line)))

(two-pairs? "aaabcdefgaa")


(comment
  (def data (str/split-lines (slurp (io/resource "aoc2015/05.txt")))))

