(ns aoc2021.03
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def file (str/split-lines (slurp (io/resource "aoc2021/03.txt"))))
(def sample ["00100"
             "11110"
             "10110"
             "10111"
             "10101"
             "01111"
             "00111"
             "11100"
             "10000"
             "11001"
             "00010"
             "01010"])

(defn read-column [column rows]
  (map (comp parse-long str #(nth % column)) rows))

(defn get-gamma-bit [column]
  (let [freq (frequencies column)
        zeros (get freq 0)
        ones (get freq 1)]
    (if (< zeros ones)
      1
      0)))

(defn get-epsilon-bit [column]
  (let [freq (frequencies column)
        zeros (get freq 0)
        ones (get freq 1)]
    (if (> zeros ones)
      1
      0)))

(defn gamma [bits]
  (let [amt (count (first bits))]
    (->> (range amt)
         (map #(read-column % bits))
         (map get-gamma-bit)
         (apply str))))

(defn epsilon [bits]
  (let [amt (count (first bits))]
    (->> (range amt)
         (map #(read-column % bits))
         (map get-epsilon-bit)
         (apply str))))


(defn binary->decimal [binary]
  (Integer/parseInt binary 2))

(defn solve [bits]
  (let [g (gamma bits)
        e (epsilon bits)]
    (* (binary->decimal g) (binary->decimal e))))

(comment
  (solve sample)
  (solve file))