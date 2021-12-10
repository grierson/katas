(ns aoc2021.05
  (:require [clojure.string :as str]))

(def sample "0,9 -> 5,9\n8,0 -> 0,8\n9,4 -> 3,4\n2,2 -> 2,1\n7,0 -> 7,4\n6,4 -> 2,0\n0,9 -> 2,9\n3,4 -> 1,4\n0,0 -> 8,8\n5,5 -> 8,2")


(defn data->line [data]
  (partition 2 (map parse-long (re-seq #"\d+" data))))

(defn data->lines [data]
  (map data->line data))

(defn draw-line [[[x1 y1] [x2 y2]]]
  (let [column? (= x1 x2)]
    (if column?
      (let [[low big] ((juxt min max) y1 y2)
            line (map #(vector x1 %) (range low (inc big)))]
        (if (< y1 y2)
          line
          (reverse line)))
      (let [[low big] ((juxt min max) x1 x2)
            line (map #(vector % y1) (range low (inc big)))]
        (if (< x1 x2)
          line
          (reverse line))))))

(defn log-line [log line]
  (reduce (fn [state point]
            (if (contains? state point)
              (update state point inc)
              (assoc state point 1)))
          log
          line))
