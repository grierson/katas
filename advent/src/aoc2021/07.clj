(ns aoc2021.07
  (:require [clojure.java.io :as io]))

(def sample [16 1 2 0 4 2 7 1 2 14])

(defn fuel [current destination]
  (Math/abs (- current destination)))

(defn fuel2 [current destination]
  (let [low (apply min [current destination])
        high (apply max [current destination])]
    (apply + (range (inc (fuel low high))))))

(defn make-plan [f crabs position]
  (apply + (map #(f % position) crabs)))

(def plan (partial make-plan fuel))
(def plan2 (partial make-plan fuel2))

(defn make-execute [f crabs]
  (let [low (apply min crabs)
        high (apply max crabs)]
    (apply min (map #(f crabs %) (range low (inc high))))))

(def execute (partial make-execute plan))
(def execute2 (partial make-execute plan2))

(comment
  (def file (map parse-long (re-seq #"\d+" (slurp (io/resource "aoc2021/07.txt")))))
  (execute file)
  (execute2 file))
