(ns aoc2015.01
  (:require [clojure.java.io :as io]))

(def count-if (comp count filter))

(defn count-floors [input]
  (let [ups (count-if #{\(} input)
        downs (count-if #{\)} input)]
    (- ups downs)))

(defn update-state [{:keys [floor pc]} instruction]
  (let [new-pc (inc pc)
        new-floor (if (= \( instruction) (inc floor) (dec floor))]
    (if (= new-floor -1)
      (reduced new-pc)
      {:floor new-floor
       :pc    new-pc})))

(defn basement [input]
  (reduce update-state {:floor 0 :pc 0} input))

(comment
  (def data (slurp (io/resource "aoc2015/01.txt")))
  ;; first
  (count-floors data)
  ;; second
  (basement data))
