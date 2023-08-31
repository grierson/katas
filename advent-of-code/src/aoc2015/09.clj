(ns aoc2015.09
  (:require
   [clojure.string :as str]
   [clojure.math.combinatorics :as combo]
   [clojure.set :as set]))

(require 'hashp.core)

(def sample "London to Dublin = 464
London to Belfast = 518
Dublin to Belfast = 141")

(def real "AlphaCentauri to Snowdin = 66
AlphaCentauri to Tambi = 28
AlphaCentauri to Faerun = 60
AlphaCentauri to Norrath = 34
AlphaCentauri to Straylight = 34
AlphaCentauri to Tristram = 3
AlphaCentauri to Arbre = 108
Snowdin to Tambi = 22
Snowdin to Faerun = 12
Snowdin to Norrath = 91
Snowdin to Straylight = 121
Snowdin to Tristram = 111
Snowdin to Arbre = 71
Tambi to Faerun = 39
Tambi to Norrath = 113
Tambi to Straylight = 130
Tambi to Tristram = 35
Tambi to Arbre = 40
Faerun to Norrath = 63
Faerun to Straylight = 21
Faerun to Tristram = 57
Faerun to Arbre = 83
Norrath to Straylight = 9
Norrath to Tristram = 50
Norrath to Arbre = 60
Straylight to Tristram = 27
Straylight to Arbre = 81
Tristram to Arbre = 90")

(defn parse
  [line]
  (let [[current _ destination _ distance] (str/split line #" ")]
    [#{(keyword current) (keyword destination)} (parse-long distance)]))

(defn make-routes [data]
  (into {} (map parse data)))

(defn make-destinations [routes]
  (apply set/union (map first routes)))

(defn length
  [routes route]
  (->> route
       (partition 2 1)
       (map (fn [[from to]] (routes #{from to})))
       (reduce +)))

(defn lengths
  [data]
  (let [lines (str/split-lines data)
        routes (make-routes lines)
        destinations (make-destinations routes)
        permutations (combo/permutations destinations)]
    (map (partial length routes) permutations)))

(apply min (lengths real))
(apply max (lengths real))
