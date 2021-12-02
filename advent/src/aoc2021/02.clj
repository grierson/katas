(ns aoc2021.02
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))


(def sample "forward 5\ndown 5\nforward 8\nup 3\ndown 8\nforward 2")
(def file (slurp (io/resource "aoc2021/02.txt")))

(defn str->steps [data]
  (->> (str/split-lines data)
       (map #(str/split % #" "))
       (map (fn [[d s]] [d (parse-long s)]))))

(defn move [state [direction amount]]
  (case direction
    "forward" (update state :x + amount)
    "up" (update state :z - amount)
    "down" (update state :z + amount)))

(defn solve [{:keys [x z]}]
  (* x z))

(defn move2 [{:keys [aim] :as state} [direction amount]]
  (case direction
    "forward" (-> state
                  (update :x + amount)
                  (update :z + (* amount aim)))
    "up" (update state :aim - amount)
    "down" (update state :aim + amount)))

(def apply-moves (partial reduce move))
(def apply-moves2 (partial reduce move2))

(defn make-sub [& {:keys [x z aim]
                   :or   {x   0
                          z   0
                          aim 0}}]
  {:x   x
   :z   z
   :aim aim})

(comment
  (solve (apply-moves (make-sub) (str->steps sample)))
  (solve (apply-moves (make-sub) (str->steps file)))
  (solve (apply-moves2 (make-sub) (str->steps sample)))
  (solve (apply-moves2 (make-sub) (str->steps file))))