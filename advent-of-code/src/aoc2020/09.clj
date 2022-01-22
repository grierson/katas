(ns aoc2020.09
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(def sample "35\n20\n15\n25\n47\n40\n62\n55\n65\n95\n102\n117\n150\n182\n127\n219\n299\n277\n309\n576")
(def real (slurp (io/resource "aoc2020/09.txt")))

(defn input->xmas
  "file -> [int]"
  [input]
  (map #(Long/parseLong %) (str/split-lines input)))

(defn generate-pair-sums
  "Generate every sum of every pair"
  [window]
  (for [x window y window :when (< x y)] (+ x y)))

(defn valid?
  "Check if window pair sums contains target"
  [pair-sums target]
  (some #(= target %) pair-sums))

(defn part1
  "preamble + 1 to include target value, xmas is the entire code"
  [preamble xmas]
  (some
    (fn [window]
      (let [pair-sums (generate-pair-sums (drop-last window))
            target (last window)]
        (if (not (valid? pair-sums target))
          target)))
    (partition preamble 1 xmas)))

(defn solve1
  "preamble - size of preamble, input - raw file input"
  [preamble input]
  (let [xmas (input->xmas input)]
    (part1 (inc preamble) xmas)))

(comment
  (solve1 5 sample)
  (solve1 25 real))

;; === Part 2

(defn solve2
  "input - raw input file"
  [input]
  (let [result (solve1 25 input)
        xmas (input->xmas input)]
    (reduce +
            (some
              (fn [n]
                (some (fn [coll]
                        (let [xs (sort coll)]
                          (when (= result (reduce + xs))
                            [(first xs) (last xs)])))
                      (partition n 1 xmas)))
              (iterate inc 2)))))

(comment
  (solve2 real))
