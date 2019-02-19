(ns advent2018.006
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [com.hypirion.clj-xchart :as c]))

(def sample-data '((1 1)
                   (1 6)
                   (8 3)
                   (3 4)
                   (5 5)
                   (8 9)))

(defn parse-int [s]
   (Integer. (re-find  #"\d+" s)))

(def data (-> "006.txt"
              io/resource
              slurp
              str/split-lines
              (->> (map #(str/split % #","))
                   (map (fn [coords] (map parse-int coords))))))

(def min-x (apply min (map first sample-data)))
(def max-x (apply max (map first sample-data)))
(def min-y (apply min (map second sample-data)))
(def max-y (apply max (map second sample-data)))


(def make-table (vec (repeat 10 (vec (repeat 10 0)))))

(def init-table
  (loop [state table
         coords sample-data]
    (if (empty? coords)
      state
      (let [[col row] (vec (first coords))]
        (recur (update-in state [row col] inc)
               (rest coords))))))
