(ns aoc2020.13
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(def sample "939\n7,13,x,x,59,x,31,19")

(def lines (str/split-lines (slurp (io/resource "aoc2020/13.txt"))))

(def arrival (Long/parseLong (first lines)))
(def buses (map #(Long/parseLong %) (remove #{"x"} (str/split (second lines) #","))))

(defn find-next-bus [arrival bus]
  [bus (first (drop-while #(> arrival %) (iterate #(+ bus %) bus)))])

(defn solve [arrival buses]
  (let [earliest-bus (apply min-key second (map #(find-next-bus arrival %) buses))
        [id departure] earliest-bus
        wait (- departure arrival)]
    (* id wait)))

(comment
  (solve arrival buses))