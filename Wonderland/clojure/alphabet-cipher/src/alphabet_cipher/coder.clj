(ns alphabet-cipher.coder
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.test.alpha :as st]))


(def atoz "abcdefghijklmnopqrstuvwxyz")

(defn value [alphabet letter]
  "Get index of letter in a-z"
  (.indexOf alphabet (str letter)))

(defn gen-alpha
  "Generate alphabet starting from letter"
  [letter]
  (let [index (value atoz letter)
        start (range (+ index 26))
        cyclealpha #(char (+ 97 (rem % 26)))]
    (->> start
        (map cyclealpha)
        (drop index))))


(defn convert
  "Keyword alphabet column + Message index in a-z = encoded char"
  [keyword message]
  (let [col (gen-alpha keyword)
        row (value atoz message)]
    (nth col row)))


(defn encode
  "Apply conversion to entire message"
  [keyword message]
  (let [cipher (take (count message) (cycle keyword))
        encodedmessage (map convert cipher message)]
    (apply str encodedmessage)))

;; -- Decode
(defn revert
  "Decode message given keyword and ciphered text"
  [keyword cipher]
  (let [coll (gen-alpha keyword)
        row (.indexOf coll cipher)]
    (nth atoz row)))

(defn decode
  "Apply revertion to entire message"
  [keyword cipher]
  (let [msgcipher (take (count cipher) (cycle keyword))
        decodedmessage (map revert msgcipher cipher)]
    (apply str decodedmessage)))


(defn decipher
  [cipher message]
  cipher)
