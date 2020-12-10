(ns aoc2020.10
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(def sample "16\n10\n15\n5\n1\n11\n7\n19\n6\n12\n4\n")
(def sample2 "28\n33\n18\n42\n31\n14\n46\n20\n48\n47\n24\n23\n49\n45\n19\n38\n39\n11\n1\n32\n25\n35\n8\n17\n7\n9\n4\n2\n34\n10\n3")
(def input (slurp (io/resource "aoc2020/10.txt")))

(def data (sort (map #(Long/parseLong %) (str/split-lines input))))

(defn solve [adapters]
  (let [result (map (fn [[a b]] (- b a)) (partition 2 1 (conj adapters 0)))
        ones (count (filter #(= 1 %) result))
        threes (inc (count (filter #(= 3 %) result)))]
    (* ones threes)))

(comment
  (solve (sort (map #(Long/parseLong %) (str/split-lines sample))))
  (solve (sort (map #(Long/parseLong %) (str/split-lines sample2))))
  (solve (sort (map #(Long/parseLong %) (str/split-lines input)))))
