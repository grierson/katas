(ns aoc2021.09
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn foo [s]
  (mapv #(parse-long (str %)) s))

(def sample (mapv foo ["2199943210"
                       "3987894921"
                       "9856789892"
                       "8767896789"
                       "9899965678"]))


(defn neighbours [board [row column]]
  (let [up [(dec row) column]
        left [row (dec column)]
        right [row (inc column)]
        down [(inc row) column]]
    (remove nil? [(get-in board up)
                  (get-in board right)
                  (get-in board down)
                  (get-in board left)])))


(defn isLowest? [neighbours value]
  (< value (apply min neighbours)))

(def file (mapv foo (str/split-lines (slurp (io/resource "aoc2021/09.txt")))))

(def state (atom []))

(for [rows (range (count file))
      columns (range (count (first file)))
      :let [point (get-in file [rows columns])]
      :when (isLowest? (neighbours file [rows columns]) point)]
  (swap! state conj point))

(apply + (map inc @state))

