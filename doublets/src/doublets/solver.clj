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

(defn runner [dictionary state goal]
  (let [next-words (within-one-letter dictionary (last state))
        possible-next-words (set (filter #(contains? state %) next-words))]
    (if (contains? possible-next-words goal)
      (conj state goal)
      (cond
        (= 1 (count possible-next-words)) (runner dictionary (conj state (first possible-next-words)) goal)
        :else state))))

(defn doublets [word1 word2]
 (let [dictionary (same-length-words words (count word1))
       state #{word1}]
   (runner dictionary state word2))

(comment
  (def cheat (same-length-words words (count "door")))
  (within-one-letter cheat "door")
  (runner cheat #{"door"} "lock")
  (within-one-letter cheat "boor")
  (within-one-letter cheat "lock")
  (within-one-letter cheat "look"))
