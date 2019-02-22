(ns advent2018.006
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [com.hypirion.clj-xchart :as c]))

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

;; Setup

(defn boundary [points]
  [(apply (juxt min max) (map first points))
   (apply (juxt min max) (map second points))])

(defn make-table [[[min-x max-x] [min-y max-y]]]
  (for [x (range min-x (inc max-x))
        y (range min-y (inc max-y))]
    [x y]))

(defn manhatten [[x1 y1] [x2 y2]]
  (+ (Math/abs (- x1 x2))
     (Math/abs (- y1 y2))))

(def points sample)
(def point [1 1])

(defn nearest [points point]
  (when (some #(not= % point) points) 
    (->> points
         (map (fn [p] {:coords p :distance (manhatten point p)}))
         (group-by :distance)
         (filter #(= 1 (count (val %))))
         (mapcat val)
         (sort-by :distance)
         first
         :coords)))

(defn draw [table points]
  (map #(nearest points %) table))

(def sample '([1 1]
              [1 6]
              [8 3]
              [3 4]
              [5 5]
              [8 9]))

(comment
  (nearest sample [1 1]))

(comment
  (-> sample
      boundary
      make-table
      (draw sample)))

(comment
  (-> data
      boundary
      make-table
      (draw data)
      (frequencies)
      (->> (sort-by val >)
           second)))
