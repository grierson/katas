(ns diamond.core-test
  (:require
   [clojure.test.check.clojure-test :refer [defspec]]
   [clojure.test.check.properties :as prop]
   [clojure.spec.alpha :as s]
   [clojure.string :as string]
   [diamond.core :refer [diamond]]))

(def alphabet "ABCDEFGHIJKLMNOPQRSTUVWXYZ")
(def letter? (set alphabet))

(defspec string-always-returned
  (prop/for-all
   [letter (s/gen letter?)]
   (false? (string/blank? (diamond letter)))))


(defspec first-row-contains-A
  (prop/for-all
   [letter (s/gen letter?)]
   (= "A" 
      (-> (diamond letter)
          (string/split-lines)
          (first)
          (string/trim)))))

(defspec last-row-contains-A
  (prop/for-all
   [letter (s/gen letter?)]
   (= "A" 
      (-> (diamond letter)
          (string/split-lines)
          (last)
          (string/trim)))))
