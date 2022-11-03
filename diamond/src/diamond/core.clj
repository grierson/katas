(ns diamond.core
  (:require [clojure.string :as string]))

(def alphabet "ABCDEFGHIJKLMNOPQRSTUVWXYZ")
(def letter? (set alphabet))

(defn range-letters [letter]
  (let [index (.indexOf (vec letter?) letter)]
    (take (inc index) letter?)))

(defn spaces [n]
  (apply str (repeat n " ")))

(defn a-line-spacing [width]
  (let [half (/ (dec width) 2)
        padding (spaces half)]
    (str padding \A padding)))

(defn letter-line-spacing [width [letter-idx letter]]
  (let [inner-space-width (dec (* letter-idx 2))
        padding-width (/ (- width 2 inner-space-width) 2)
        padding (spaces padding-width)
        inner-space (spaces inner-space-width)]
    (str padding letter inner-space letter padding)))

(defn make-line [width [_ letter :as indexed-letter]]
  (if (= letter \A)
    (a-line-spacing width)
    (letter-line-spacing width indexed-letter)))

(defn make [letter]
  (let [letters (map-indexed vector (range-letters letter))
        letters (concat letters (rest (reverse letters)))
        width (count letters)]
    (string/join "\n" (map (partial make-line width) letters))))
