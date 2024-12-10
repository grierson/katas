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

(defn parse-rules [rules]
  (let [rules (str/split-lines rules)
        rule-pairs (map
                    (fn [rule]
                      (let [[left right] (str/split rule #"\|")]
                        [(parse-long left) (parse-long right)]))
                    rules)]
    (reduce (fn [state [left right]]
              (if (contains? state left)
                (update state left conj right)
                (assoc state left #{right})))
            {}
            rule-pairs)))

(defn parse-updates [updates]
  (map (fn [u] (map parse-long (str/split u #","))) (str/split-lines updates)))

(defn parse [data]
  (let [[rules updates] (str/split data #"\n\n")
        rules (parse-rules rules)
        updates (parse-updates updates)]
    {:rules rules
     :updates updates}))

(defn valid? [rules u]
  (reduce (fn [state page]
            (let [rule (get rules page)]
              (if (empty? (set/intersection rule state))
                (conj state page)
                (reduced false))))
          #{}
          u))

(defn middle [coll] (nth coll (quot (count coll) 2)))

(defn solve [data]
  (let [{:keys [rules updates]} (parse data)
        valid-updates (filter (partial valid? rules) updates)
        middles (map middle valid-updates)]
    (reduce + middles)))

(comment
  (solve sample)
  (solve input))
