(ns aoc2016.04
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn get-name [line]
  (apply str (drop-last (str/split line #"-"))))

(defn order-name [name]
  (->> (frequencies name)
       (group-by second)
       (sort-by first >)
       (map second)
       (map #(sort-by first %))
       flatten
       (take-nth 2)
       (take 5)))

(defn get-hash [line]
  (drop-last (take-last 6 line)))

(defn parse-line [line]
  (let [name (get-name line)
        [_ id] (re-find #"-(\d+)" line)
        hash (get-hash line)]
    {:name name
     :id (Integer/parseInt id)
     :hash hash}))

(defn valid? [{:keys [name hash]}]
  (let [result (order-name name)]
    (= result hash)))

(comment
  (reduce
    (fn [state {:keys [id] :as record}]
      (if (valid? record)
        (+ state id)
        state))
    0
    (map parse-line (str/split-lines (slurp (io/resource "aoc2016/04.txt"))))))

