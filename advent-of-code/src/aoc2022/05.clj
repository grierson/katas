(ns aoc2022.05
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(def sample
  "move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2")

(def sample-crates {1 [\Z \N]
                    2 [\M \C \D]
                    3 [\P]})

(def data-crates {1 [\N \R \G \P]
                  2 [\J \T \B \L \F \G \D \C]
                  3 [\M \S \V]
                  4 [\L \S \R \C \Z \P]
                  5 [\P \S \L \V \C \W \D \Q]
                  6 [\C \T \N \W \D \M \S]
                  7 [\H \D \G \W \P]
                  8 [\Z \L \P \H \S \C \M \V]
                  9 [\R \P \F \L \W \G \Z]})

(def data (slurp (io/resource "aoc2022/05.txt")))

(defn parse-rearrangement
  "[amount current destintation]"
  [rearrangement]
  (map parse-long (re-seq #"\d+" rearrangement)))

(defn parse [rearrangements]
  (map parse-rearrangement (str/split-lines rearrangements)))

(defn transfer [crates current destintation]
  (let [value (last (crates current))]
    (-> crates
        (update current pop)
        (update destintation #(conj % value)))))

(defn procedure [crates [amount current destintation]]
  (if (zero? amount)
    crates
    (procedure
     (transfer crates current destintation)
     [(dec amount) current destintation])))

(defn ends [crates]
  (apply str (map last (vals (into (sorted-map) crates)))))

(comment
  (ends (reduce procedure sample-crates (parse sample)))
  (ends (reduce procedure data-crates (parse data))))
