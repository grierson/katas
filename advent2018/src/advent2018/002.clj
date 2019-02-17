(ns advent2018.001
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(def data (-> "002.txt"
              io/resource
              slurp
              string/split-lines))

(defn foo [amount line]
  (let [group (group-by identity line)
        match (first (filter #(= (count (val %)) amount) group))]
    (if match
      1
      0)))

(* (apply + (map #(foo 2 %) data))
   (apply + (map #(foo 3 %) data)))
