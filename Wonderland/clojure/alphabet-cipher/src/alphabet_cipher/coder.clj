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



;; Domain

(def alphabet (\a \b \c \d \e \f \g \h \i \j \k \l \m \n \o \p \q \r \s \t \u \v \w \x \y \z))


(defn pos [x]
  (- (int x) 97))


(defn offset [letter]
  (mod (pos letter) (count alphabet)))

(offset \a)

;; (letter from keyword) + (letter from message) = (encoded letter)

;; Logic
;; (offset "s" "m")
;; e


(defn encode [keyword message]
<<<<<<< HEAD
  "egsgqwtahuiljgs")
=======
  (let [cipher (take (count message) (cycle keyword))]
    (apply str (map substitution cipher message))))
>>>>>>> a336314c7dd22021fa395465da5778cf31f64ccf


(defn decode [keyword message]
  "meetmeontuesdayeveningatseven")



(defn decipher [cipher message]
  "vigilance")

