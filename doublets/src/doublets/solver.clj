(ns doublets.solver
  (:require [clojure.java.io :as io]))

;; Filter dictionary
;; Find all words within one letter
;; Search each possibility
;; Get shortest

(def words (-> "words.edn"
               (io/resource)
               (slurp)
               (read-string)))


(defn same-length-words [dictionary length]
  (filter #(= (count %) length) dictionary))


(defn compare-words [word1 word2]
  (= 1 (count (filter false? (map = word1 word2)))))


(defn within-one-letter [dictionary word]
  (filter #(compare-words word %) dictionary))


(defn contains-word [words word]
  (some #(= word %) words))


(defn runner [dictionary state goal]
  (let [next-words (within-one-letter dictionary (last state))
        possible-next-words (remove #(contains-word state %) next-words)]
    (if (contains-word possible-next-words goal)
      (conj state goal)
      (map #(flatten (runner dictionary (conj state %) goal)) possible-next-words))))


(defn doublets [word1 word2]
  (if (not= (count word1) (count word2))
    []
    (let [dictionary (same-length-words words (count word1))
          state [word1]]
      (first (runner dictionary state word2)))))

(comment
  (def cheat (same-length-words words (count "wheat")))
  (runner cheat ["head"] "tail")
  (runner cheat ["wheat"] "bread"))
