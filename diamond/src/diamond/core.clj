(ns diamond.core
  (:require
   [clojure.spec.alpha :as s]
   [clojure.string :as string]))

(def alphabet "ABCDEFGHIJKLMNOPQRSTUVWXYZ")
(def letter? (set alphabet))

(defn range-letters [letter]
  (let [index (.indexOf (vec letter?) letter)]
    (take (inc index) letter?)))

(defn make-line [width letter]
  (if (= letter \A)
    (let [half (/ (dec width) 2)
          padding (apply str (repeat half " "))]
      (str padding letter padding))
    (apply str (repeat width letter))))

(defn make [letter]
  (let [letters (range-letters letter)
        reverse-letters (rest (reverse letters))
        letters (concat letters reverse-letters)
        width (count letters)]
    (string/join "\n" (map (partial make-line width) letters))))
