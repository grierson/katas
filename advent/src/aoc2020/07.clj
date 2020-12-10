(ns aoc2020.07
  (:require [clojure.java.io :as io]
            [advent.core :refer [count-if]]
            [clojure.string :as str]))

(def input (slurp (io/resource "aoc2020/07.txt")))

(defn parse-line [line]
  (let [bag (second (re-find #"(\w+ \w+) bags contain" line))
        deps (re-seq #"(\d+) (\w+ \w+) (bag|bags)" line)]
    [bag (set (map (fn [[_ _ color]] color) deps))]))

(defn parse-line2 [line]
  (let [bag (second (re-find #"(\w+ \w+) bags contain" line))
        deps (re-seq #"(\d+) (\w+ \w+) (bag|bags)" line)]
    [bag (map (fn [[_ amount color]] [(Integer/parseInt amount) color]) deps)]))

(defn make-graph [data]
  (into (hash-map) (map parse-line (str/split-lines data))))

(comment
  (make-graph input))

(defn bag-contains? [tree find-bag current-bag]
  (let [deps (get tree current-bag)]
    (cond
      (empty? deps) false
      (contains? deps find-bag) true
      :else (some true? (map #(bag-contains? tree find-bag %) deps)))))

(def tree (make-graph input))

(time (let [tree (make-graph input)]
        (count-if true? (map (fn [bag] (bag-contains? tree "shiny gold" bag)) (keys tree)))))

