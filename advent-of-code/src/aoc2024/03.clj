(ns aoc2024.03
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(def sample "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))")
(def sample2 "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))")

(def input (slurp (io/resource "aoc2024/03.txt")))

(defn parse [data]
  (let [multis (re-seq #"mul\((\d+),(\d+)\)" data)
        pairs (map (fn [[_ multiplier multiplicand]] [(parse-long multiplier) (parse-long multiplicand)]) multis)]
    pairs))

(defn parse2 [data]
  (let [result (re-seq #"mul\((\d+),(\d+)\)|(do\(\))|(don\'t\(\))" data)
        enabled-multiplications (reduce (fn [state [command multiplier multiplicand]]
                                          (cond
                                            (str/starts-with? command "mul")
                                            (if (get state :do)
                                              (update state :multis conj [(parse-long multiplier) (parse-long multiplicand)])
                                              state)
                                            (= command "don't()") (assoc state :do false)
                                            (= command "do()") (assoc state :do true)))
                                        {:do true
                                         :multis []}
                                        result)]
    (:multis enabled-multiplications)))

(defn solve [multis]
  (reduce + (map (fn [[multiplier multiplicand]] (* multiplier multiplicand)) multis)))

(comment
  (solve (parse sample))
  (solve (parse input))
  (solve (parse2 sample2))
  (solve (parse2 input)))
