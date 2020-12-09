(ns aoc2020.09
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(def sample "35\n20\n15\n25\n47\n40\n62\n55\n65\n95\n102\n117\n150\n182\n127\n219\n299\n277\n309\n576")
(def input (slurp (io/resource "aoc2020/09.txt")))

(def data (map #(Long/parseLong %) (str/split-lines input)))

(defn make-combinations [coll]
  (filter (fn [[a b]] (not= a b)) (set (for [x coll y coll] [x y]))))

(defn valid? [combinations sum]
  (some (fn [[a b]] (= sum (+ a b))) combinations))

(some
  (fn [xs]
    (let [coll (make-combinations (drop-last xs))
          sum (last xs)]
      (if (not (valid? coll sum))
        sum)))
  (partition 26 1 data))