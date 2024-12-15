(ns aoc2024.05
  (:require
   [clojure.java.io :as io]
   [clojure.set :as set]
   [clojure.string :as str]))

(def sample "47|53
97|13
97|61
97|47
75|29
61|13
75|53
29|13
97|29
53|29
61|53
97|53
61|29
47|13
75|47
97|75
47|61
75|61
47|29
75|13
53|13

75,47,61,53,29
97,61,53,29,13
75,29,13
75,97,47,61,53
61,13,29
97,13,75,29,47")

(def input (slurp (io/resource "aoc2024/05.txt")))

(defn parse-rule
  "'23|42' -> [23 42]"
  [rule]
  (let [[left right] (str/split rule #"\|")]
    [(parse-long left) (parse-long right)]))

(defn parse-rules
  [rules]
  (let [rules (str/split-lines rules)
        rule-pairs (map parse-rule rules)]
    (reduce
     (fn [state [left right]]
       (update state left (fnil conj #{}) right))
     {}
     rule-pairs)))

(defn parse-update
  [update-line]
  (map parse-long (str/split update-line #",")))

(defn parse-updates [updates]
  (map parse-update (str/split-lines updates)))

(defn parse [data]
  (let [[rules updates] (str/split data #"\n\n")
        rules (parse-rules rules)
        updates (parse-updates updates)]
    {:rules rules
     :updates updates}))

(defn middle [coll] (nth coll (quot (count coll) 2)))

(defn valid?
  "Check no rules already seen before adding to seen"
  [rules seen update-line]
  (if (empty? update-line)
    true
    (let [page (first update-line) page-dependencies (get rules page)]
      (if (empty? (set/intersection seen page-dependencies))
        (recur rules (conj seen page) (rest update-line))
        false))))

(defn solve [data]
  (let [{:keys [rules updates]} (parse data)
        valid-updates (filter (partial valid? rules #{}) updates)
        middles (map middle valid-updates)]
    (reduce + middles)))

;; TODO: inspect how sort works here
;; used bhauman solution
;; https://github.com/bhauman/adv2024/blob/5fd8bbe54d771e52e439ba974b1216992b905a13/src/adv2024/day05/sol.clj#L15
(defn after? [rules b a] (boolean (get-in rules [b a])))
(defn correct [rules pages] (sort (partial after? rules) pages))

(defn solve2 [data]
  (let [{:keys [rules updates]} (parse data)
        invalid-updates (remove (partial valid? rules #{}) updates)
        corrected-updates (map (partial correct rules) invalid-updates)
        middles (map middle corrected-updates)]
    (reduce + middles)))

(comment
  (solve sample)
  (solve input)
  (solve2 sample)
  (solve2 input))
