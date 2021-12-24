(ns aoc2021.06
  (:require [clojure.java.io :as io]))

(def sample [3 4 3 1 2])
(def day1 [2 3 2 0 1])
(def day2 [1 2 1 6 0 8])
(def day3 [0 1 0 5 6 7 8])


(defn update-state [state index]
  (let [value (get state index)]
    (if (= value 0)
      (-> state
          (assoc index 6)
          (conj 8))
      (update state index dec))))

(defn calculate [state days]
  (loop [state state
         days days]
    (if (= days 0)
      state
      (recur
        (reduce update-state state (range (count state)))
        (dec days)))))


(comment
  (count (calculate sample 18))
  (def file (mapv parse-long (re-seq #"\d+" (slurp (io/resource "aoc2021/06.txt")))))
  (count (calculate file 80)))