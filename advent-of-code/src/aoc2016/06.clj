(ns aoc2016.06
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn common-fn [sort-fn letters]
  (->>
   letters
   frequencies
   (sort-by val sort-fn)
   ffirst))

(def least-common (partial common-fn <))
(def most-common (partial common-fn >))

(defn get-letters [letters column]
  (apply str (map #(nth % column) letters)))

(defn solve-fn [find-fn data]
  (let [columns (count (first data))]
    (->> (range 0 columns)
         (map #(get-letters data %))
         (map #(find-fn %))
         (apply str))))

(def solve (partial solve-fn most-common))
(def solve2 (partial solve-fn least-common))

(comment
  (def data (str/split-lines (slurp (io/resource "aoc2016/06.txt"))))
  (solve data)
  (solve2 data))
