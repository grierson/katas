(ns aoc2017.01
  (:require [clojure.java.io :as io]))

(defn solve [number]
  (let [num (str number)
        num1 (first num)
        num2 (str num num1)]
    (reduce
      (fn [state [a b]]
        (if (= a b)
          (+ state a)
          state))
      0
      (partition 2 1 (map #(Integer/parseInt (str %)) num2)))))

(def input (slurp (io/resource "aoc2017/01.txt")))
(solve input)

(comment
  (solve 1122)
  (solve 1111)
  (solve 1234)
  (solve 91212129))
