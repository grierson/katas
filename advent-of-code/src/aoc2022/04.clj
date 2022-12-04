(ns aoc2022.04
  (:require
   [clojure.java.io :as io]
   [clojure.set :as set]
   [clojure.string :as str]))

(def sample
  "2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8")

(def data (slurp (io/resource "aoc2022/04.txt")))

(defn parse [assignment]
  (->> (str/split assignment #",")
       (map (fn [section] (str/split section #"-")))
       (map (fn [section] (map parse-long section)))
       (map (fn [[start end]] (range start (inc end))))
       (map (fn [section] (set section)))))

(defn fully-contains [section1 section2]
  (or (set/superset? section1 section2)
      (set/superset? section2 section1)))

(defn any-overlap [section1 section2]
  (not (empty? (set/intersection section1 section2))))

(defn solve [assignments]
  (->> assignments
       (str/split-lines)
       (map parse)
       (map (fn [[s1 s2]] (fully-contains s1 s2)))
       (filter true?)
       (count)))

(comment
  (solve data))
