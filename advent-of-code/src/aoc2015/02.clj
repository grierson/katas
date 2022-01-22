(ns aoc2015.02
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(def data (slurp (io/resource "aoc2015/02.txt")))

(defn paper [[l w h]]
  (let [side1 (* l w)
        side2 (* w h)
        side3 (* h l)]
    (+ (min side1 side2 side3)
       (* 2 side1)
       (* 2 side2)
       (* 2 side3))))

(defn ribbon [dimensions]
  (let [[x y z] (sort dimensions)]
    (+ (* 2 x)
       (* 2 y)
       (* x y z))))

(defn parse-measurement [measurement]
  (map #(Integer/parseInt %) (str/split measurement #"x")))

(comment
  ;; First
  (reduce + (map (comp paper parse-measurement) data))
  ;; Second
  (reduce + (map (comp ribbon parse-measurement) data)))
