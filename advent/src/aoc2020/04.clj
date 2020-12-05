(ns aoc2020.04
  (:require [advent.core :refer [count-if parse-int]]
            [clojure.set :refer [superset?]]
            [clojure.string :as str]
            [clojure.java.io :as io]))

(def input (slurp (io/resource "aoc2020/04.txt")))
(def data input)

(def req #{"byr" "iyr" "eyr" "hgt" "hcl" "ecl" "pid"})

(defn line->passport [line]
  (into {} (map (comp vec next)) (re-seq #"(\w{3}):(\S+)" line)))

(defn valid-passport? [passport]
  (superset? (set (keys passport)) req))

(defn solve [data]
  (->> (str/split data #"\R\R")
       (map line->passport)
       (count-if valid-passport?)))

(defn valid-hgt? [hgt]
  (let [[_ amount unit] (re-find #"(\d+)(cm|in)" hgt)]
    (case unit
      "cm" (<= 150 (parse-int amount) 193)
      "in" (<= 59 (parse-int amount) 76)
      false)))

(defn valid-passport2? [{:strs [byr iyr eyr hgt hcl ecl pid] :as passport}]
  (and
    (valid-passport? passport)
    (<= 1920 (parse-int byr) 2002)
    (<= 2010 (parse-int iyr) 2020)
    (<= 2020 (parse-int eyr) 2030)
    (re-find #"^(#)([a-f0-9]{6})" hcl)
    (re-find #"^(amb|blu|brn|gry|grn|hzl|oth)" ecl)
    (re-find #"^\d{9}$" pid)
    (valid-hgt? hgt)))

(defn solve2 [data]
  (->> (str/split data #"\R\R")
       (map line->passport)
       (count-if valid-passport2?)))

(comment
  (solve input)
  (solve2 input))

