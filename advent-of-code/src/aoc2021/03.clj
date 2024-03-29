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

(defn binary->decimal [binary]
  (Integer/parseInt binary 2))

(defn number->list [numbers]
  (map (comp parse-long str) numbers))

(defn report->data [report]
  (map number->list report))

(defn read-column [column report]
  (map #(nth % column) report))

(defn get-frequencies [f keeping column]
  (let [freq (frequencies column)
        zeros (get freq 0 nil)
        ones (get freq 1 nil)]
    (cond
      (nil? zeros) 1
      (nil? ones) 0
      (= zeros ones) keeping
      :else (key (apply f val freq)))))

(def get-most-recurring (partial get-frequencies max-key 1))
(def get-least-recurring (partial get-frequencies min-key 0))

(defn get-recurring [f report]
  (let [columns (count (first report))]
    (->> (range columns)
         (map #(read-column % report))
         (map f))))

(def gamma (partial get-recurring get-most-recurring))
(def epsilon (partial get-recurring get-least-recurring))

(defn solve [report]
  (let [report (report->data report)
        g (apply str (gamma report))
        e (apply str (epsilon report))]
    (* (binary->decimal g) (binary->decimal e))))

(defn filter-occurring
  ([f report]
   (filter-occurring f report 0))
  ([f report column]
   (let [column-data (read-column column report)
         most-common (f column-data)]
     (filter (fn [coll] (= (nth coll column) most-common)) report))))

(def oxygen (partial filter-occurring get-most-recurring))
(def co2 (partial filter-occurring get-least-recurring))

(defn get-final
  ([f report] (get-final f report 0))
  ([f report column]
   (if (= 1 (count report))
     (first report)
     (recur f (f report column) (inc column)))))

(def get-oxygen (partial get-final oxygen))
(def get-co2 (partial get-final co2))

(defn solve2 [report]
  (let [report (report->data report)
        o (apply str (get-oxygen report))
        c (apply str (get-co2 report))]
    (* (binary->decimal o) (binary->decimal c))))

(comment
  (solve sample)
  (solve file)
  (solve2 sample)
  (solve2 file))
