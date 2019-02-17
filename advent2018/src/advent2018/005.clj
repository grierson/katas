(ns advent2018.005
  (:require [clojure.java.io :as io]))

(def data (-> "005.txt"
              io/resource
              slurp))

(defn match [a b]
  (and
   (not= a b)
   (= (Character/toLowerCase a) (Character/toLowerCase b))))

(defn passover [state x]
  (if (some-> (peek state) (match x))
    (pop state)
    (conj state x)))

(count (reduce passover [] data))
