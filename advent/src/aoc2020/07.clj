(ns aoc2020.07
  (:require [clojure.java.io :as io]
            [advent.core :refer [count-if]]
            [clojure.string :as str]))

(def input (slurp (io/resource "aoc2020/07.txt")))

(defn get-colors [line]
  (let [result (re-seq #"(\w+) (\w+) (bag|bags)" line)
        colors (map (fn [[_ c1 c2 _]] (str/join #" " [c1 c2])) result)
        deps (rest colors)]
    (if (= deps '("no other"))
      [(first colors) nil]
      [(first colors) (set deps)])))

(defn build-tree [data]
  (into (hash-map) (map get-colors (str/split-lines data))))

(defn bag-holds-color [tree color bag]
  (let [x (get tree bag)]
    (cond
      (nil? x) false
      (contains? x color) true
      :else (some true? (map #(bag-holds-color tree color %) x)))))

(let [tree (build-tree input)]
  (count-if true? (map #(bag-holds-color tree "shiny gold" %) (keys tree))))
