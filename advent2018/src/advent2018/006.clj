(ns advent2018.006
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [com.hypirion.clj-xchart :as c]))

(def sample-data '[[1 1]
                   [1 6]
                   [8 3]
                   [3 4]
                   [5 5]
                   [8 9]])

(defn parse-int [s]
  (Integer/parseInt s))

(defn parse-coordinate [s]
  (->> s
    (re-find #"(\d+), (\d+)")
    rest
    (mapv parse-int)))

(def data (map parse-coordinate (-> "006.txt"
                                    io/resource
                                    slurp
                                    str/split-lines)))

(defn boundary [points]
  [(apply (juxt min max) (map first points))
   (apply (juxt min max) (map second points))])

(defn middle [points]
  (let [[min-x max-x] (apply (juxt min max) (map first points))
        [min-y max-y] (apply (juxt min max) (map second points))]
    [(quot (+ min-x max-x) 2)
     (quot (+ min-y max-y) 2)]))

(comment
  (middle sample-data)
  (middle data))

(defn make-table [[[min-x max-x] [min-y max-y]]]
  (for [x (range min-x (inc max-x))
        y (range min-y (inc max-y))]
    [x y]))

(comment
  (boundary sample-data)
  (make-table (boundary sample-data)))

(defn manhatten [[x1 y1] [x2 y2]]
  (+ (Math/abs (- x1 x2))
     (Math/abs (- y1 y2))))

(defn nearest [points point]
  (->> points
       (filter #(not= % point))
       (map (fn [p] {:coords p :distance (manhatten point p)}))
       (group-by :distance)
       (filter #(= 1 (count (val %))))
       (mapcat val)
       (sort-by :distance)
       first
       :coords))

(comment
  (setup make-table sample-data)
  (manhatten [1 1] [1 6])
  (manhatten [1 1] [3 4])
  (nearest sample-data [1 1])
  (nearest sample-data [1 6]))
