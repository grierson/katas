(ns aoc2021.13
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(defn parse-fold [[fold line]]
  [(if (= fold "x") :x :y) (parse-long line)])

(defn parse-dots [[x y]]
  [(parse-long x) (parse-long y)])

(defn parse-line [line]
  (if (str/starts-with? line "fold")
    (-> line
        (str/split #" ")
        (last)
        (str/split #"=")
        (parse-fold))
    (-> line
        (str/split #",")
        (parse-dots))))

(defn parse [paper]
  (set/rename-keys
    (group-by (fn [[a _]] (keyword? a)) (map parse-line paper))
    {true :folds
     false :dots}))

