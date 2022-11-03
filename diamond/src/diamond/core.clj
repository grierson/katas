(ns diamond.core
  (:require [clojure.spec.alpha :as s]))

(def alphabet "ABCDEFGHIJKLMNOPQRSTUVWXYZ")
(def letter? (set alphabet))

(defn range-letters [letter]
  (let [index (.indexOf (vec letter?) letter)]
    (take (inc index) letter?)))

(defn make [letter]
  (let [letters (range-letters letter)
        reverse-letters (rest (reverse letters))
        letters (concat letters reverse-letters)]
    (reduce
     (fn [state c] (str state "\n" c))
     (map str letters))))
