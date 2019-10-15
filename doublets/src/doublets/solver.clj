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

(comment
  (def dictionary (same-length-words words (count "door")))
  (def state ["door" "boor" "book"])
  (def next-words '("boor" "look" "bonk"))
  (def possible-next-words '("look" "bonk"))
  (def goal "lock"))

;; How to handle multiple possible-next-words?

(defn runner [dictionary state goal]
  (let [next-words (within-one-letter dictionary (last state))
        possible-next-words (remove (fn [word] (some #(= word %) state)) next-words)]
    (if (some #(= goal %) possible-next-words)
      (conj state goal)
      (cond
        (= 1 (count possible-next-words)) (runner dictionary (conj state (first possible-next-words)) goal)
        :else state))))

(defn doublets [word1 word2]
 (let [dictionary (same-length-words words (count word1))
       state #{word1}]
   (runner dictionary state word2)))
