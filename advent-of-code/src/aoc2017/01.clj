(ns aoc2017.01
  (:require [clojure.java.io :as io]))

(defn make-next-pair [number]
  (partition 2 1
             (map #(Integer/parseInt (str %))
                  (str number (first number)))))

(defn make-middle-pair [number]
  (let [middle (/ (count number) 2)]
    (map-indexed
     (fn [i v]
       [(parse-long (str v))
        (parse-long (str (nth (cycle number) (+ i middle))))])
     number)))

(defn- total-matching [pairs]
  (reduce
   (fn [state [a b]]
     (if (= a b)
       (+ state a)
       state))
   0
   pairs))

(defn solve [number]
  (total-matching (make-next-pair number)))

(defn solve2 [number]
  (total-matching (make-middle-pair number)))

(comment
  (def input (slurp (io/resource "aoc2017/01.txt")))
  (solve input)
  (solve2 input))
