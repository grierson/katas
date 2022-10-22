(ns aoc2016.04
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn get-name [line]
  (apply str (drop-last (str/split line #"-"))))

(defn order-name [name]
  (->> (frequencies name)
       (group-by second)
       (mapcat (fn [[k v]] {k (sort (map first v))}))
       (sort-by first >)
       (mapcat second)
       (take 5)
       (apply str)))

(defn get-sector-id [line]
  (parse-long (re-find #"\d+" line)))

(defn get-hash [line]
  (second (re-find #"\[(.*?)\]" line)))

(defn parse-line [line]
  (let [name (get-name line)
        sector-id (get-sector-id line)
        hash (get-hash line)]
    {:name name
     :sector-id sector-id
     :hash hash}))

(defn valid? [{:keys [name hash]}]
  (= (order-name name) hash))

(defn update-state [state {:keys [sector-id] :as room}]
  (if (valid? room)
    (+ state sector-id)
    state))

(defn solve [data]
  (reduce
   update-state
   0
   (map parse-line data)))

(comment
  (def data (str/split-lines (slurp (io/resource "aoc2016/04.txt"))))
  (solve data))

