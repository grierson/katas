(ns aoc2020.07
  (:require [clojure.java.io :as io]
            [advent.core :refer [count-if]]
            [clojure.string :as str]))

(def input (slurp (io/resource "aoc2020/07sample.txt")))

(defn get-colors [line]
  (let [bag (first (re-seq #"(\w+) (\w+) (bag|bags)" line))
        bag-color ((fn [[_ c1 c2 _]] (str/join #" " [c1 c2])) bag)
        deps (re-seq #"(\d+) (\w+) (\w+) (bag|bags)" line)
        deps-colors (map (fn [[_ cnt c1 c2 _]] [(Integer/parseInt cnt) (str/join #" " [c1 c2])]) deps)]
    [bag-color (set deps-colors)]))

(defn build-tree [data]
  (into (hash-map) (map get-colors (str/split-lines data))))

(comment
  (build-tree input))

(defn bag-holds-color? [tree color clr]
  (let [deps (set (map second (get tree clr)))]
    (cond
      (empty? deps) false
      (contains? deps color) true
      :else (some true? (map #(bag-holds-color? tree color %) deps)))))

(def tree (build-tree input))
(let [tree (build-tree input)]
  (count-if true? (map (fn [bag] (bag-holds-color? tree "shiny gold" bag)) (keys tree))))
