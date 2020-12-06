(ns aoc2020.06
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def input  (slurp (io/resource "aoc2020/06.txt")))

(reduce + (map (comp count set #(str/replace % "\n" "")) (str/split input #"\R\R")))

