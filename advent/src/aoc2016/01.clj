(ns aoc2016.01
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(def file (slurp (io/resource "aoc2016/01.txt")))

(defn parse-direction [[turn & steps]]
  [turn (parse-long (apply str steps))])

(defn make-state [& {:keys [x y direction]
                     :or   {x         0
                            y         0
                            direction \N}}]
  {:x x :y y :direction direction})

(def default (make-state))

(def compass {\N {\L \W
                  \R \E}
              \E {\L \N
                  \R \S}
              \S {\L \E
                  \R \W}
              \W {\L \S
                  \R \N}})

(defn turn [direction turning]
  (get-in compass [direction turning]))

(defn move [{:keys [direction] :as state} steps]
  (case direction
    \N (update state :y + steps)
    \E (update state :x + steps)
    \S (update state :y - steps)
    \W (update state :x - steps)))

(defn update-state [state [turning steps]]
  (-> state
      (update :direction turn turning)
      (move steps)))

(defn distance [{:keys [x y]}]
  (+ (Math/abs x) (Math/abs y)))

(defn solve [state guide]
  (distance (reduce update-state state guide)))

(defn trace-steps [{:keys [x y direction]} steps]
  (case direction
    \N (map (fn [s] [x (+ y s)]) (range 1 (inc steps)))
    \S (map (fn [s] [x (- y s)]) (range 1 (inc steps)))
    \E (map (fn [s] [(+ x s) y]) (range 1 (inc steps)))
    \W (map (fn [s] [(- x s) y]) (range 1 (inc steps)))))

(defn visited? [visited [h & t]]
  (if h
    (if (contains? visited h)
      h
      (recur (conj visited h) t))
    nil))


(def guide (map (comp parse-direction str/trim) (str/split file #",")))

(defn foo [state visited [gh & gt]]
  (let [[turning steps] gh
        new-state (update state :direction turn turning)
        trace (trace-steps new-state steps)]
    (if-let [out (visited? visited trace)]
      out
      (recur (move new-state steps) (apply conj visited trace) gt))))

(defn solve2 [state guide]
  (let [[x y] (foo state #{} guide)
        state {:x x :y y}]
    (distance state)))

(comment
  (solve default guide)
  (solve2 default guide))


