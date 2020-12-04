(ns aoc2020.04
  (:require [advent.core :refer [count-if]]
            [clojure.set :refer [superset?]]
            [clojure.string :as str]
            [clojure.java.io :as io]))

(def input (slurp (io/resource "aoc2020/04.txt")))

(def req #{"byr" "iyr" "eyr" "hgt" "hcl" "ecl" "pid"})

(defn input->lines [lines]
  (->> (str/split lines #"\R\R")
       (map #(str/replace % "\n" " "))
       (map #(str/split % #" "))))

(defn get-keys [line]
  (set (map #(subs % 0 3) line)))

(defn solve1 [data]
  (count-if #(superset? % req) (map get-keys (input->lines data))))

(defn valid-byr? [byr] (if byr (<= 1920 (Integer/parseInt byr) 2002) false))
(defn valid-iyr? [iyr] (if iyr (<= 2010 (Integer/parseInt iyr) 2020) false))
(defn valid-eyr? [eyr] (if eyr (<= 2020 (Integer/parseInt eyr) 2030) false))
(defn valid-hcl? [hcl] (when hcl (some? (re-find #"^(#)([a-f0-9]{6})" hcl))))
(defn valid-ecl? [ecl] (when ecl (some? (re-find #"(amb|blu|brn|gry|grn|hzl|oth)" ecl))))
(defn valid-pid? [pid] (when pid (some? (re-find #"([0-9]{9})" pid))))

(defn parse-hgt [hgt]
  (if hgt
    (let [[_ amount unit :as result] (re-find #"(\d+)(cm|in)" hgt)]
      (when result
        [(Integer/parseInt amount) unit]))))

(defn valid-hgt? [hgt]
  (if-let [[amount unit] (parse-hgt hgt)]
    (condp = unit
      "cm" (<= 150 amount 193)
      "in" (<= 59 amount 76))
    false))

(defn parse-field [field]
  (when-let [[_ k v] (re-find #"([a-z]+):(\S+)" field)]
    [k v]))

(defn line->passport [passport]
  (into (sorted-map) (map parse-field passport)))

(defn valid-passport? [{:strs [byr iyr eyr hgt hcl ecl pid]}]
  (and (valid-byr? byr)
       (valid-iyr? iyr)
       (valid-eyr? eyr)
       (valid-hgt? hgt)
       (valid-hcl? hcl)
       (valid-ecl? ecl)
       (valid-pid? pid)))

(count-if valid-passport? (map line->passport (input->lines input)))
(map line->passport (input->lines input))

(comment
  (valid-passport? {"eyr" "1972" "cid" "100" "hcl" "#18171d" "ecl" "amb" "hgt" "170" "pid" "186cm" "byr" "1926"})
  (valid-passport? {"iyr" "2019" "hcl" "#602927" "eyr" "1967" "hgt" "170cm" "ecl" "grn" "pid" "012533040" "byr" "1946"})
  (valid-passport? {"hcl" "dab227" "iyr" "2012" "ecl" "brn" "hgt" "182cm" "pid" "021572410" "eyr" "2020" "byr" "1992" "cid" "277"})
  (valid-passport? {"hgt" "59cm" "ecl" "zzz" "eyr" "2038" "hcl" "74454a" "iyr" "2023" "pid" "3556412378" "byr" "2007"}))

(comment
  (solve1 input))