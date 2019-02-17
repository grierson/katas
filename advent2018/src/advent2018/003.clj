(ns advent2018.003
  (:require [clojure.java.io :as io]
            [clojure.string :as s]))

(def data (-> "003.txt"
              io/resource
              slurp
              s/split-lines))

(defn read-claim [s]
  (->> s
       (re-find #"#(\d+) @ (\d+),(\d+): (\d+)x(\d+)")
       rest
       (map read-string)
       (zipmap [:id :x :y :width :height])))

(defn draw [{:keys [x y width height]}]
  (for [col (range x (+ x width))
        row (range y (+ y height))]
    [row col]))

(->> data
     (map read-claim)
     (mapcat draw)
     frequencies
     (filter (fn [[_ v]] (>= v 2)))
     count)
