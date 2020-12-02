(ns aoc2020.02
  (:require [advent.core :refer [read-file
                                 count-if
                                 parse-int]]))

(def data (read-file "aoc2020/02.txt"))

(defn parse-line [line]
  (let [[_ low high c password] (re-find #"(\d+)-(\d+) (.): (.*)" line)]
    [(parse-int low) (parse-int high) (first c) password]))

(defn valid-password? [[low high c password]]
  (let [cnt (count-if #{c} password)]
    (<= low cnt high)))

(count-if true? (map (comp valid-password? parse-line) data))

(defn valid-password2? [[low high c password]]
  (let [x (= c (nth password (dec low)))
        y (= c (nth password (dec high)))]
    (not= x y)))

(count-if true? (map (comp valid-password2? parse-line) data))
