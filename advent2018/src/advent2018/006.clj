(ns advent2018.006
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

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

(def sample '([1 1]
              [1 6]
              [8 3]
              [3 4]
              [5 5]
              [8 9]))
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

(defn nearest [points point]
  (when-not (some #{point} points)
    (let [closest (->> points
                       (map (fn [p] {:coords p :distance (manhatten point p)}))
                       (group-by :distance)
                       (sort-by key)
                       first)]
      (when (= 1 (-> closest val count))
        (-> closest val first :coords)))))

(defn draw [table points]
  (map #(nearest points %) table))

(defn areas [points]
  (let [table (-> points
                  boundary
                  make-table
                  (draw points))]
    (->> table
      (remove nil?)
      frequencies
      (sort-by val >))))

;; File data
(comment
  (-> (areas sample) first second inc)
  (-> (areas data) second second inc)
  (+ 1 1))
