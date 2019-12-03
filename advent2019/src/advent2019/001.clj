(ns advent2019.001
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn parse-int [s]
  (Integer. (re-find  #"\d+" s)))

(def data (-> "001.txt"
              io/resource
              slurp
              str/split-lines))

(defn fuel [mass]
  (let [quotient (int (Math/floor (/ mass 3)))]
    (max 0 (- quotient 2))))

(defn part-one []
  (->> data
       (map parse-int)
       (map fuel)
       (reduce +)))
