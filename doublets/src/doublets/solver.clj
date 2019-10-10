(ns doublets.solver
  (:require [clojure.java.io :as io]))

;; Filter dictionary
;; Find all words within one letter

(def words (-> "words.edn"
               (io/resource)
               (slurp)
               (read-string)))

(defn same-length-words [dictionary length]
  (filter (fn [word] (= length (count word))) dictionary))

(defn compare-words [word1 word2]
  (= 1 (count (filter false? (map (fn [a b] (= a b)) word1 word2)))))

(defn within-one-letter [dictionary word]
  (filter #(compare-words word %) dictionary))

(defn doublets [word1 word2]
 (let [dictionary (same-length-words words (count word1))
       state #{word1}
       next-words (within-one-letter dictionary (last state))]
   next-words))

(comment
  (def cheat (same-length-words words (count "door")))
  (within-one-letter cheat "lock"))
