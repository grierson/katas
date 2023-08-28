(ns aoc2015.04
  (:require [clj-commons.digest :as digest]
            [clojure.string :as str]))

(def secret "bgvyzdsv")

(defn valid? [hash]
  (str/starts-with? hash "00000"))

(defn valid2? [hash]
  (str/starts-with? hash "000000"))

(defn finder
  ([valid-fn] (finder valid-fn 0))
  ([valid-fn cnt]
   (let [hash (digest/md5 (str secret cnt))]
     (if (valid-fn hash)
       cnt
       (recur valid-fn (inc cnt))))))

(comment
  (finder valid?)
  (finder valid2?))
