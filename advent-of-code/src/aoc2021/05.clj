(ns aoc2021.05
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn data->line [data]
  (partition 2 (map parse-long (re-seq #"\d+" data))))

(defn data->lines [data]
  (map data->line data))

(defn orientation-draw-line [orientation a b]
  (let [[small big] ((juxt min max) a b)
        line (map orientation (range small (inc big)))]
    (if (< a b)
      line
      (reverse line))))

(defn draw-line [[[x1 y1 :as p1] [x2 y2 :as p2]]]
  (let [column? (= x1 x2)
        row? (= y1 y2)]
    (cond
      (true? column?)  (orientation-draw-line #(vector x1 %) y1 y2)
      (true? row?) (orientation-draw-line #(vector % y1) x1 x2)
      :else
      (if-let [p1-smaller? (< (apply + p1) (apply + p2))]
        (let [[sx sy] (if p1-smaller? p1 p2)
              [bx by] (if p1-smaller? p2 p1)
              line (map #(vector % %) (range sy (inc by)))]
          (if p1-smaller?
            line
            (reverse line)))
        10))))

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
