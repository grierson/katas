(ns aoc2021.02
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))


(def sample "forward 5\ndown 5\nforward 8\nup 3\ndown 8\nforward 2")
(def file (slurp (io/resource "aoc2021/02.txt")))

(defn read-file [data]
  (->> (str/split-lines data)
       (map #(str/split % #" "))
       (map (fn [[d s]] [d (parse-long s)]))))

(defn move [state [direction amount]]
  (case direction
    "forward" (update state :x + amount)
    "up" (update state :z - amount)
    "down" (update state :z + amount)))

(defn solve [state steps]
  (let [{:keys [x z]} (reduce move state steps)]
    (* x z)))


(comment
  (solve {:x 0 :z 0} (read-file sample))
  (solve {:x 0 :z 0} (read-file file)))