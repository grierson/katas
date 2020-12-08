(ns aoc2020.07
  (:require [clojure.java.io :as io]
            [advent.core :refer [count-if]]
            [clojure.string :as str]))

(def input (slurp (io/resource "aoc2020/07sample.txt")))

(defn parse-line [line]
  (let [bag (second (re-find #"(\w+ \w+) bags contain" line))
        deps (re-seq #"(\d+) (\w+ \w+) (bag|bags)" line)]
    [bag (map (fn [[_ amount color]] [(Integer/parseInt amount) color]) deps)]))

(comment
  (map parse-line (str/split-lines input)))

(defn make-graph [data]
  (into (hash-map) (map parse-line (str/split-lines data))))

(comment
  (make-graph input))

(defn bag-holds-color? [tree find-bag current-bag]
  (let [deps (set (map second (get tree current-bag)))]
    (cond
      (empty? deps) false
      (contains? deps find-bag) true
      :else (some true? (map #(bag-holds-color? tree find-bag %) deps)))))

(time (let [tree (make-graph input)]
        (count-if true? (map (fn [bag] (bag-holds-color? tree "shiny gold" bag)) (keys tree)))))

