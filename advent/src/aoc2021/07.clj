(ns aoc2021.07
  (:require [clojure.java.io :as io]))

(def sample [16 1 2 0 4 2 7 1 2 14])

(defn fuel [current destination]
  (Math/abs (- current destination)))

(defn plan [crabs position]
  (apply + (map #(fuel % position) crabs)))

(defn execute [crabs]
  (let [low (apply min crabs)
        high (apply max crabs)]
    (apply min (map #(plan crabs %) (range low (inc high))))))

(comment
  (def file (map parse-long (re-seq #"\d+" (slurp (io/resource "aoc2021/07.txt")))))
  (execute file))
