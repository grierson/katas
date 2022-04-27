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
  (update
    (set/rename-keys
      (group-by (fn [[a _]] (keyword? a)) (map parse-line paper))
      {true  :folds
       false :dots})
    :dots
    set))

(defn get-y-halfs [line dots]
  (update-vals
    (set/rename-keys
      (group-by (fn [[_ y]] (>= y line)) dots)
      {true  :bottom
       false :top})
    set))

(defn relocate-y-dot [fold [x y]]
  (let [a (- y fold)
        b (- fold a)]
    [x b]))

(defn relocate-dots [[direction line :as fold] dots]
  (if (= direction :y)
    (let [{:keys [top bottom]} (get-y-halfs line dots)]
      (into top
            (map (partial relocate-y-dot line) bottom)))))

(defn apply-fold [{:keys [dots folds]}]
  (let [[fold & folds] folds]
    {:dots  (relocate-dots fold dots)
     :folds folds}))
