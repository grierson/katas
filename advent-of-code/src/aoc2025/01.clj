(ns aoc2025.01
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

((requiring-resolve 'hashp.install/install!))

(defn parse-line [[direction & amount]]
  {:direction (if (= direction \R) :right :left)
   :amount (parse-long (apply str amount))})

(defn parse-input [input]
  (map parse-line (str/split-lines input)))

(defn rotation [current {:keys [direction amount]}]
  (let [op (if (= direction :right) + -)]
    (mod (op current amount) 100)))

(defn solve1 [directions]
  (reduce (fn [{:keys [current zeros]} input]
            (let [new-location (rotation current input)]
              {:current new-location
               :zeros (if (= new-location 0) (inc zeros) zeros)}))
          {:current 50
           :zeros 0}
          directions))

(def sample-input "L68
L30
R48
L5
R60
L55
L1
L99
R14
L82")

(def data (slurp (io/resource "aoc2025/01.txt")))

(comment
  (solve1 (parse-input sample-input))
  (solve1 (parse-input data)))


