(ns diamond.core
  (:gen-class)
  (:require [clojure.string :as str]
            [clojure.spec.alpha :as s]
            [clojure.spec.test.alpha :as st]
            [diamond.core-spec :as ds]
            [diamond.core-test :as dt]))


;; Generate Alphabet
(s/fdef alphabet
        :args (s/and (s/cat :start ::ds/letter :end ::ds/letter)
                     #(neg? (compare (:start %) (:end %))))
        :ret (s/coll-of ::ds/letter :distinct true)
        :fn (s/and #(not-empty (:ret %))
                   #(= (sort (:ret %)) (:ret %))))


(defn alphabet
  [start end]
  {:pre [(s/valid? (s/cat :start ::ds/letter :end ::ds/letter) [start end])]
   :post [s/valid? (s/coll-of ::ds/letter) %]}
  (map char (range (int start) (inc (int end)))))


;; Indexed letters with mirror concat
(s/fdef indexed-letters
        :args (s/cat :letter ::ds/letter)
        :ret (s/coll-of vector?))


(defn indexed-letters
  [letter]
  (let [letters (map-indexed vector (alphabet \A letter))]
    (concat (-> letters drop-last) (-> letters reverse))))


;; Apply padding and inner spacing to create line
(s/fdef make-line
        :args (s/cat :width pos-int? :indexed-letter (s/and (s/cat :index pos-int? :letter ::ds/letter)))
        :ret string?)


(defn make-line
  "Create each line within Diamond"
  [width indexed-letter]
  (let [[index letter] indexed-letter]
    (cond
      (= letter \A) (let [padding (apply str (repeat (/ width 2) \space))]
                      (str padding letter padding))
      :else (let [innerWidth (dec (* index 2))
                  inner (apply str (repeat innerWidth \space))
                  padding (apply str (repeat (/ (- width innerWidth 2) 2) \space))]
              (str padding letter inner letter padding)))))


;; Generates the Diamond
(s/fdef make
        :args (s/cat :letter ::letter)
        :ret string?
        :fn (s/and #(dt/row-not-empty (:ret %))
                   #(dt/first-row-contains-a (:ret %))
                   #(dt/last-row-contains-a (:ret %))
                   #(dt/symmetric-contour (:ret %))
                   #(dt/alphabetical-order (-> % :args :letter) (:ret %))
                   #(dt/equal-height-width (:ret %))
                   #(dt/inner-rows-have-identical-letters (:ret %))
                   #(dt/lower-left-triangle (:ret %) (-> % :args :letter))
                   #(dt/diamond-symmetric-horizontal (:ret %) (-> % :args :letter))))


(defn make
  "Creates Diamond"
  [letter]
  (let [l (s/assert ::ds/letter letter)
        letters (indexed-letters l)
        width (count letters)]
    (str/join "\n" (map #(make-line width %) letters))))
