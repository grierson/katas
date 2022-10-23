(ns aoc2016.01
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(def file (slurp (io/resource "aoc2016/01.txt")))

(defn parse-direction [[turning & steps]]
  [turning (parse-long (apply str steps))])

(defn make-state [& {:keys [x y facing]
                     :or   {x         0
                            y         0
                            facing \N}}]
  {:x x :y y :facing facing})

(def default (make-state))

(def compass {\N {\L \W
                  \R \E}
              \E {\L \N
                  \R \S}
              \S {\L \E
                  \R \W}
              \W {\L \S
                  \R \N}})

(defn turn [facing turning]
  (get-in compass [facing turning]))

(defn move [{:keys [facing] :as state} steps]
  (case facing
    \N (update state :y + steps)
    \E (update state :x + steps)
    \S (update state :y - steps)
    \W (update state :x - steps)))

(defn trace-move [{:keys [x y facing]} steps]
  (case facing
    \N (map (fn [s] [x (+ y s)]) (range 1 (inc steps)))
    \S (map (fn [s] [x (- y s)]) (range 1 (inc steps)))
    \E (map (fn [s] [(+ x s) y]) (range 1 (inc steps)))
    \W (map (fn [s] [(- x s) y]) (range 1 (inc steps)))))

(defn follow [state [turning steps]]
  (-> state
      (update :facing turn turning)
      (move steps)))

(defn distance [{:keys [x y]}]
  (+ (Math/abs x) (Math/abs y)))

(defn solve [state directions]
  (distance (reduce follow state directions)))

(defn visited? [visited [location & locations]]
  (when location
    (if (contains? visited location)
      location
      (recur (conj visited location) locations))))

(defn- data->directions [data]
  (map (comp parse-direction str/trim) (str/split data #",")))

(defn find-visited-twice-location 
  [state visited [[turning steps] & directions]]
  (let [new-state (update state :facing turn turning)
        trace (trace-move new-state steps)]
    (if-let [visited-twice-location (visited? visited trace)]
      visited-twice-location
      (recur (move new-state steps) (apply conj visited trace) directions))))

(defn solve2 [state directions]
  (let [[x y] (find-visited-twice-location state #{} directions)]
    (distance {:x x :y y})))

(comment
  (def directions (data->directions file))
  (solve default directions)
  (solve2 default directions))
