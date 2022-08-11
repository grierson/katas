(ns aoc2017.04
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(defn duplicates? [passphrase]
  (let [coll (frequencies (str/split passphrase #" "))]
    (every? (fn [[_ v]] (= v 1)) coll)))

(defn anagrams? [passphrase]
  (let [coll (map frequencies (str/split passphrase #" "))]
    (= (count coll)
       (count (set coll)))))

(defn solve [valid-fn data]
  (count (filter valid-fn (str/split-lines data))))

(comment
  (def data (slurp (io/resource "aoc2017/04.txt")))
  (solve duplicates? data)
  (solve anagrams? data))
