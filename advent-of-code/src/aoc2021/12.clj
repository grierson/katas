(ns aoc2021.12
  (:require [clojure.string :as str]))

(def simple ["start-A"
             "start-b"
             "A-end"
             "b-end"])

(def caves ["start-A"
            "start-b"
            "A-c"
            "A-b"
            "b-d"
            "A-end"
            "b-end"])

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

(def sample (caves->graph simple))

(defn visited?
  [visited node]
  (contains? visited node))

(defn graph-dfs
  [graph start]
  (loop [[current & tail] (vector start)
         visited #{}]
    (if (nil? current)
      visited
      (let [neighbors (get graph current)
            not-visited (remove (partial visited? visited) neighbors)
            new-stack (into tail not-visited)]
        (if (visited? visited current)
          (recur new-stack visited)
          (recur new-stack (conj visited current)))))))

(graph-dfs sample "start")




