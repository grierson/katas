(ns aoc2016.05
  (:require [clj-commons.digest :as digest]
            [clojure.string :as str]))

(defn valid? [hash]
  (str/starts-with? hash "00000"))

(defn append-valid-hash [state id]
  (let [hash (digest/md5 id)]
    (if (valid? hash)
      (conj state hash)
      state)))

(defn found-all-hashes
  [state id]
  (if (= 8 (count state))
    (reduced state)
    (append-valid-hash state id)))

(defn finder [secret]
  (reduce
   found-all-hashes
   []
   (lazy-seq (map #(str secret %) (range)))))

(defn solve [secret]
  (let [hashs (finder secret)]
    (apply str (map #(nth % 5) hashs))))

(comment
  (solve "abc")
  (finder "reyedfim")
  (solve "reyedfim"))
