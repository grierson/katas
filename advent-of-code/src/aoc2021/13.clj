(ns aoc2021.13
  (:require [clojure.string :as str]
            [clojure.set :as set]
            [clojure.java.io :as io]))

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
  (let [lines (group-by (fn [[a _]] (keyword? a)) (map parse-line (remove empty? paper)))]
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

(defn replace-at [s idx replacement]
  (str (subs s 0 idx) replacement (subs s (inc idx))))

(defn draw [dots]
  (let [rows (apply max (map first dots))
        columns (apply max (map second dots))
        board (map (fn [_] (apply str (repeat columns \.))) (range rows))]
    (reduce (fn [state [row column]] (update state row #(replace-at % column "#"))) board dots)))

(comment
  (def data (parse (str/split-lines (slurp (io/resource "aoc2021/13.txt")))))
  (count (execute (update data :folds #(-> % first vector))))
  (def part2 (execute data))
  (draw part2))
