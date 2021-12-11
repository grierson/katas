(ns aoc2021.05
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

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

(defn count-overlaps [grid]
  (count (filter #(> (second %) 1) grid)))

(defn diagonal? [[[x1 y1] [x2 y2]]]
  (and (not= x1 x2) (not= y1 y2)))

(def sample (data->lines (str/split-lines "0,9 -> 5,9\n8,0 -> 0,8\n9,4 -> 3,4\n2,2 -> 2,1\n7,0 -> 7,4\n6,4 -> 2,0\n0,9 -> 2,9\n3,4 -> 1,4\n0,0 -> 8,8\n5,5 -> 8,2")))
(def file (data->lines (str/split-lines (slurp (io/resource "aoc2021/05.txt")))))

(comment
  (count-overlaps
    (reduce (fn [state x]
              (let [line (draw-line x)]
                (log-line state line))) {} (remove diagonal? sample)))
  (count-overlaps
    (reduce (fn [state x]
              (log-line state (draw-line x))) {} (remove diagonal? file)))
  (count-overlaps
    (reduce (fn [state x]
              (log-line state (draw-line x))) {} file)))
