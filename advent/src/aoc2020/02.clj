(ns aoc2020.02
  (:require [advent.core :refer [count-if]]
            [clojure.java.io :as io]
            [clojure.string :as str]))

(def data (str/split-lines (slurp (io/resource "aoc2020/02.txt"))))

(defn parse-line [line]
  (let [[_ low high c password] (re-find #"(\d+)-(\d+) (.): (.*)" line)]
    [(Integer/parseInt low) (Integer/parseInt high) (first c) password]))

(defn valid-password? [[low high c password]]
  (let [cnt (count-if #{c} password)]
    (<= low cnt high)))

(comment
  (count-if true? (map (comp valid-password? parse-line) data)))

(defn valid-password2? [[low high c password]]
  (let [x (= c (nth password (dec low)))
        y (= c (nth password (dec high)))]
    (not= x y)))

(comment
  (count-if true? (map (comp valid-password2? parse-line) data)))
