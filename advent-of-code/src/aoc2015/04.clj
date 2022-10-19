(ns aoc2015.04
  (:require [clj-commons.digest :as digest]
            [clojure.string :as str]))

(def secret "bgvyzdsv")

(defn valid? [hash]
  (str/starts-with? hash "00000"))

(defn valid2? [hash]
  (str/starts-with? hash "000000"))

(defn finder [valid-fn]
  (loop [cnt 0]
    (let [hash (digest/md5 (str secret cnt))]
      (if (valid-fn hash)
        cnt
        (recur (inc cnt))))))

(comment
  (finder valid?)
  (finder valid2?))
