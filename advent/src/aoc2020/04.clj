(ns aoc2020.04
  (:require [advent.core :refer [count-if]]
            [clojure.set :refer [superset?]]
            [clojure.string :as str]
            [clojure.java.io :as io]))

(def data (slurp (io/resource "aoc2020/04.txt")))

(def req #{"byr" "iyr" "eyr" "hgt" "hcl" "ecl" "pid"})

(defn lines->passports [lines]
  (->> (str/split lines #"\R\R")
       (map #(str/replace % "\n" " "))
       (map #(str/split % #" "))))

(defn get-keys [line]
  (set (map #(subs % 0 3) line)))

(count-if #(superset? % req) (map get-keys (lines->passports data)))
