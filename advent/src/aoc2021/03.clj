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

(defn number->list [numbers]
  (map (comp parse-long str) numbers))

(defn report->data [report]
  (map number->list report))

(defn read-column [column report]
  (map #(nth % column) report))

(defn get-rate-bit [f column]
  (let [freq (frequencies column)]
    (key (apply f val freq))))

(def get-gamma-bit (partial get-rate-bit max-key))
(def get-epsilon-bit (partial get-rate-bit min-key))

(defn get-rate [f report]
  (let [amt (count (first report))]
    (->> (range amt)
         (map #(read-column % report))
         (map f))))

(def gamma (partial get-rate get-gamma-bit))
(def epsilon (partial get-rate get-epsilon-bit))

(defn binary->decimal [binary]
  (Integer/parseInt binary 2))

(defn solve [report]
  (let [report (report->data report)
        g (apply str (gamma report))
        e (apply str (epsilon report))]
    (* (binary->decimal g) (binary->decimal e))))

(comment
  (solve sample)
  (solve file))