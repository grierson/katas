(ns diamond.core-test
  (:require
   [clojure.test.check.clojure-test :refer [defspec]]
   [clojure.test.check.properties :as prop]
   [clojure.spec.alpha :as s]
   [clojure.string :as string]
   [diamond.core :refer [diamond]]))

(def alphabet "ABCDEFGHIJKLMNOPQRSTUVWXYZ")
(def letter? (set alphabet))

(defspec first-element-is-min-after-sorting
  (prop/for-all
   [letter (s/gen letter?)]
   (false? (string/blank? (diamond letter)))))
