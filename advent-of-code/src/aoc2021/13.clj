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
  (let [lines (group-by (fn [[a _]] (keyword? a)) (map parse-line paper))]
    (-> lines
        (set/rename-keys {true  :folds
                          false :dots})
        (update :dots set))))

(defn get-y-halfs [line dots]
  (update-vals
    (set/rename-keys
      (group-by (fn [[_ y]] (>= y line)) dots)
      {true  :bottom
       false :top})
    set))

(defn get-x-halfs [column dots]
  (update-vals
    (set/rename-keys
      (group-by (fn [[x _]] (>= x column)) dots)
      {true  :right
       false :left})
    set))

(defn relocate-y-dot [line [x y]]
  (let [a (- y line)
        b (- line a)]
    [x b]))

(defn relocate-x-dot [column [x y]]
  (let [a (- x column)
        b (- column a)]
    [b y]))

(defn apply-fold [[direction amount] dots]
  (if (= direction :y)
    (let [{:keys [top bottom]} (get-y-halfs amount dots)]
      (into top
            (map (partial relocate-y-dot amount) bottom)))
    (let [{:keys [left right]} (get-x-halfs amount dots)]
      (into left
            (map (partial relocate-x-dot amount) right)))))


(defn execute [{:keys [dots folds]}]
  (reduce (fn [state fold] (apply-fold fold state)) dots folds))
