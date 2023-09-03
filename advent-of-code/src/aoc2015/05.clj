(ns aoc2015.05
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(def ^:private vowels
  #{\a \e \i \o \u})

(defn three-vowels? [line]
  (<= 3 (count (filter vowels line))))

(defn contains-pair? [line]
  (let [permutations (map
                      (fn [[a b]] (= a b))
                      (partition 2 1 line))]
    (some? (some true? permutations))))

(defn contains-no-bad-pairs? [line]
  (nil? (re-find #"(ab|cd|pq|xy)" line)))

(defn nice? [line]
  (and (three-vowels? line)
       (contains-pair? line)
       (contains-no-bad-pairs? line)))

(defn seperated-pair? [line]
  (true? (some (fn [[a _ b]] (= a b)) (partition 3 1 line))))

(defn matching-pair-not-duplicated [[p1l p1r] [p2l p2r]]
  (let [[p1li p1lv] p1l
        [p1ri p1rv] p1r
        [p2li p2lv] p2l
        [p2ri p2rv] p2r
        indexes #{p1li p1ri}]
    (if (not= [p1lv p1rv] [p2lv p2rv])
      false
      (not (or (contains? indexes p2li)
               (contains? indexes p2ri))))))

(defn two-pairs? [line]
  (let [pairs (partition 2 1 (map-indexed vector line))]
    (true? (some
            (fn [p1]
              (some (fn [p2] (matching-pair-not-duplicated p1 p2)) pairs))
            pairs))))

(defn nice2? [line]
  (and (two-pairs? line)
       (seperated-pair? line)))

(comment
  (def data (str/split-lines (slurp (io/resource "aoc2015/05.txt"))))
  (count (filter nice? data))
  (count (filter nice2? data)))

