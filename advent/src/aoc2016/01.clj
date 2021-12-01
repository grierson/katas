(ns aoc2016.01
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn parse-direction [direction]
  (let [[_ turn steps] (re-matches #"(.)(\d+)" direction)]
    [(first turn) (Integer/parseInt steps)]))

(def data (slurp (io/resource "aoc2016/01.txt")))
(def guide (map (comp parse-direction str/trim) (str/split data #",")))

(def compass {\N {\L \W
                  \R \E}
              \E {\L \N
                  \R \S}
              \S {\L \E
                  \R \W}
              \W {\L \S
                  \R \N}})

(defn move [{:keys [direction] :as state} steps]
  (case direction
    \N (update state :y + steps)
    \E (update state :x + steps)
    \S (update state :y - steps)
    \W (update state :x - steps)))


(defn turn [direction turning]
  (get-in compass [direction turning]))

(defn update-state [state [turning steps]]
  (-> state
      (update :direction #(turn % turning))
      (move steps)))

(update-state {:direction \N :x 0 :y 0} [\R 3])

(defn distance [{:keys [x y]}]
  (+ (Math/abs x) (Math/abs y)))

(defn solve [state guide]
  (distance (reduce update-state state guide)))

(defn move-log [{:keys [x y direction]} steps]
  (case direction
    \N #{[x y]
         [x (+ 1 y)]
         [x (+ 2 y)]}))


(comment
  ;first
  (solve {:direction \N
          :x         0
          :y         0} guide)
  (solve2 [\N [0 0]] guide))


