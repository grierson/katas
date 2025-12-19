(ns aoc2023.01
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def data (slurp (io/resource "aoc2023/01.txt")))

(def ones {"one" 1
           "two" 2
           "three" 3
           "four" 4
           "five" 5
           "six" 6
           "seven" 7
           "eight" 8
           "nine" 9})

(def rev-ones {"eno" 1
               "owt" 2
               "eerht" 3
               "ruof" 4
               "evif" 5
               "xis" 6
               "neves" 7
               "thgie" 8
               "enin" 9})

(defn parse
  [calibration]
  (let [numbers (filter #(Character/isDigit %) calibration)
        [first last] ((juxt first last) numbers)]
    (parse-long (str first last))))

(defn find-first-digit [s]
  (loop [index 0]
    (if (< index (count s))
      (let [char (get s index)]
        (if (Character/isDigit char)
          [index (parse-long (str char))]
          (recur (inc index))))
      nil)))

(defn find-first-one [s]
  (loop [index 0]
    (if (<= index (count s))
      (let [remaining (subs s index)]
        (if-let [one-key (some #(when (.startsWith remaining %) %) (keys ones))]
          [index (get ones one-key)]
          (recur (inc index))))
      nil)))

(defn find-first
  [calibration]
  (let [[index-digit value-digit] (find-first-digit calibration)
        [index-one value-one] (find-first-one calibration)]
    (cond
      (nil? index-digit) value-one
      (nil? index-one) value-digit
      :else (if (> index-digit index-one) value-one value-digit))))

(defn find-last-one [s]
  (loop [index 0]
    (if (<= index (count s))
      (let [remaining (subs s index)]
        (if-let [one-key (some #(when (.startsWith remaining %) %) (keys rev-ones))]
          [index (get rev-ones one-key)]
          (recur (inc index))))
      nil)))

(defn find-last
  [calibration]
  (let [rev-str (apply str (reverse calibration))
        [index-digit value-digit] (find-first-digit rev-str)
        [index-one value-one] (find-last-one rev-str)]
    (cond
      (nil? index-digit) value-one
      (nil? index-one) value-digit
      :else (if (> index-digit index-one) value-one value-digit))))

(defn parse2
  [calibration]
  (let [left (find-first calibration)
        right (find-last calibration)]
    (parse-long (str left right))))

(defn solve [input]
  (reduce + (map parse (str/split-lines input))))

(defn solve2 [input]
  (reduce + (map parse2 (str/split-lines input))))

(comment
  (solve data)
  (solve2 data))
