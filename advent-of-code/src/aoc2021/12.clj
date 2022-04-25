(ns aoc2021.12
  (:require [clojure.string :as str]))

(defn caves->graph [caves]
  (let [edges (map (fn [line] (str/split line #"-")) caves)]
    (reduce (fn [state [a b]]
              (let [state (if (get state a)
                            (update state a conj b)
                            (assoc state a #{b}))
                    state (if (get state b)
                            (update state b conj a)
                            (assoc state b #{a}))]
                state)) {} edges)))

(defn visited?
  [visited node]
  (contains? visited node))

(defn small-cave?
  [cave]
  (every? #(Character/isLowerCase %) cave))

(defn invalid-caves?
  [visited cave]
  (and (visited? visited cave)
       (small-cave? cave)))

(defn paths
  [graph visited current]
  (if (= current "end")
    current
    (let [neighbors (get graph current)
          not-visited (remove (partial invalid-caves? visited) neighbors)]
      (conj
        (map (fn [cave] (paths graph (conj visited current) cave)) not-visited)
        current))))

(defn valid-paths
  [graph visited current]
  (if (= current "end")
    1
    (let [neighbors (get graph current)
          not-visited (remove (partial invalid-caves? visited) neighbors)]
      (apply +
             (map (fn [cave] (valid-paths graph (conj visited current) cave)) not-visited)))))
