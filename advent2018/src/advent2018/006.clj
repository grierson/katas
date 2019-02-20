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
   (Integer. (re-find  #"\d+" s)))

(def data (-> "006.txt"
              io/resource
              slurp
              str/split-lines
              (->> (map #(str/split % #","))
                   (map (fn [coords] (map parse-int coords)))
                   (map vec))))

(def min-x (apply min (map first sample-data)))
(def max-x (apply max (map first sample-data)))
(def min-y (apply min (map second sample-data)))
(def max-y (apply max (map second sample-data)))

(def make-table (vec (repeat 10 (vec (repeat 10 \.)))))

(defn setup [table points]
  (loop [state table
         [p & ps] points
         [a & as] "ABCDEF"]
    (if (nil? p)
      state
      (let [[col row] p]
        (recur (assoc-in state [row col] a)
               ps
               as)))))

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
  (manhatten [1 1] [1 6]) # A to B
  (manhatten [1 1] [3 4]) # A to D
  (nearest sample-data [1 1])
  (nearest sample-data [1 6]))
