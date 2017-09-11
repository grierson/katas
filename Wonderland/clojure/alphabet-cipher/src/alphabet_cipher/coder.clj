(ns alphabet-cipher.coder
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.test.alpha :as st]))


(def alphabet "abcdefghijklmnopqrstuvwxyz")


(defn value [letter]
  (.indexOf alphabet letter))


(defn substitution
  "Keyword char + Message char = encoded char"
  [keyword message]
  (nth alphabet (rem (+ (value (str keyword)) (value (str message))) 26)))


(defn encode [keyword message]
  (let [cipher (take (count message) (cycle keyword))]
    (apply str (map substitution cipher message))))


(defn decode [keyword message]
  "decodeme")


(defn decipher [cipher message]
  "decypherme")

