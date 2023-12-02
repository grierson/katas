(ns aoc2023.01
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def data (slurp (io/resource "aoc2023/01.txt")))

(defn parse
  [calibration]
  (let [numbers (filter #(Character/isDigit %) calibration)
        [first last] ((juxt first last) numbers)]
    (parse-long (str first last))))
