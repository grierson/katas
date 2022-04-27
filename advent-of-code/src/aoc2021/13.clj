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

(defn get-bottom-half [dots line]
  (filter (fn [[_ y]] (>= y line)) dots))

(defn relocate-y-dot [fold [x y]]
  (let [a (- y fold)
        b (- fold a)]
    [x b]))

(defn relocate-dot [[direction amount] dots]
  (if (= direction :y)
    (relocate-y-dot amount dots)))

(defn apply-fold [{:keys [dots folds]}]
  (let [[fold & folds] folds]
    {:dots  (map #(relocate-dot fold %) dots)
     :folds folds}))

