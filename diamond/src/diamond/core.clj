(ns diamond.core
  (:require
   [clojure.spec.alpha :as s]
   [clojure.string :as string]))

(def alphabet "ABCDEFGHIJKLMNOPQRSTUVWXYZ")
(def letter? (set alphabet))

(defn range-letters [letter]
  (let [index (.indexOf (vec letter?) letter)]
    (take (inc index) letter?)))

(defn make-line [width [letter letter-idx]]
  (if (= letter \A)
    (let [half (/ (dec width) 2)
          padding (apply str (repeat half " "))]
      (str padding letter padding))
    (let [inner-space-width (dec (* letter-idx 2))
          padding (apply str (repeat (/ (- width 2 inner-space-width) 2) " "))
          inner-space (apply str (repeat inner-space-width " "))]
      (str padding letter inner-space letter padding))))

(defn make [letter]
  (let [letters
        (map-indexed
         (fn [index letter] [letter index])
         (range-letters letter))

        letters (concat letters (rest (reverse letters)))
        width (count letters)]
    (string/join "\n" (map (partial make-line width) letters))))

(make \D)
