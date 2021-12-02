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
    "up" (-> state
             (update :z - amount))
    "down" (update state :z + amount)))

(defn apply-moves [state steps]
  (reduce move state steps))

(defn solve [{:keys [x z]}]
  (* x z))

(defn move2 [{:keys [aim] :as state} [direction amount]]
  (case direction
    "forward" (-> state
                  (update :x + amount)
                  (update :z + (* amount aim)))
    "up" (update state :aim - amount)
    "down" (update state :aim + amount)))

(defn apply-moves2 [state steps]
  (reduce move2 state steps))

(comment
  (solve {:x 0 :z 0} (read-file sample))
  (solve {:x 0 :z 0} (read-file file)))