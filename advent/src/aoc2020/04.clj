(ns aoc2020.04
  (:require [advent.core :refer [read-file count-if]]
            [clojure.set :refer [superset?]]
            [clojure.string :as str]))

(def data (read-file "aoc2020/04.txt"))

(def sample
  (str/split-lines
    "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\nbyr:1937 iyr:2017 cid:147 hgt:183cm\n\niyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\nhcl:#cfa07d byr:1929\n\nhcl:#ae17e1 iyr:2013\neyr:2024\necl:brn pid:760753108 byr:1931\nhgt:179cm\n\nhcl:#cfa07d eyr:2025 pid:166559648\niyr:2011 ecl:brn hgt:59in"))

(def req #{"byr" "iyr" "eyr" "hgt" "hcl" "ecl" "pid"})

(def passports (loop [state []
                      coll data]
                 (if (empty? coll)
                   state
                   (recur (conj state (take-while not-empty coll)) (drop 1 (drop-while not-empty coll))))))

(defn foo [lines]
  (set (map #(subs % 0 3) (str/split (str/join #" " (take-while not-empty lines)) #" "))))

(map foo passports)

(count-if #(superset? % req) (map foo passports))

(comment
  (foo (drop-while not-empty sample))
  (superset? x req))
