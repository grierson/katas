(ns aoc2015.02
  (:require [aoc.common :refer [read-file parse-int]]
            [clojure.string :as str]))

(def data (read-file "aoc2015/02.txt"))

(defn paper [[l w h]]
  (let [side1 (* l w)
        side2 (* w h)
        side3 (* h l)]
    (+ (min side1 side2 side3)
       (* 2 side1)
       (* 2 side2)
       (* 2 side3))))

(defn ribbon [[l w h :as dimensions]]
  (let [[s1 s2] (take 2 (sort dimensions))]
    (+ (+ s1 s1 s2 s2)
       (* l w h))))

(defn parse-measurement [measurement]
  (map parse-int (str/split measurement #"x")))

(reduce + (map (comp paper parse-measurement) data))
(reduce + (map (comp ribbon parse-measurement) data))
