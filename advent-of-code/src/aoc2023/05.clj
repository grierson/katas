(ns aoc2023.05
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(def data (slurp (io/resource "aoc2023/05.txt")))

(def sample "seeds: 79 14 55 13

seed-to-soil map:
50 98 2
52 50 48

soil-to-fertilizer map:
0 15 37
37 52 2
39 0 15

fertilizer-to-water map:
49 53 8
0 11 42
42 0 7
57 7 4

water-to-light map:
88 18 7
18 25 70

light-to-temperature map:
45 77 23
81 45 19
68 64 13

temperature-to-humidity map:
0 69 1
1 0 69

humidity-to-location map:
60 56 37
56 93 4")

(require '[hashp.core])

(defn numbers [s]
  (map parse-long (re-seq #"\d+" s)))

(defn- title->keyword [k]
  (-> k
      (str/replace #" map:" "")
      (str/replace #"-to-" "->")
      keyword))

(defn- values->ranges [vs]
  (let [str->numbers (map numbers vs)
        ranges (map (fn [[destination source length]]
                      (take length (map vector
                                        (range source Integer/MAX_VALUE)
                                        (range destination Integer/MAX_VALUE)))) str->numbers)]
    (into {} (apply concat ranges))))

(defn- parse-mapping [[k & vs]] [(title->keyword k) (values->ranges vs)])

(defn parse-mappings [lines]
  (let [lines (partition-by empty? lines)
        mappings (filter (comp not empty? first) lines)
        mappings (map parse-mapping mappings)]
    (into {} mappings)))

(defn parse [data]
  (let [lines (str/split-lines data)
        seeds (map parse-long (re-seq #"\d+" (first lines)))
        lines (drop 2 lines)
        lines (parse-mappings lines)]
    (merge {:seeds seeds} lines)))

(defn get-location [state seed]
  (let [soil (get-in state [:seed->soil seed] seed)
        fertilizer (get-in state [:soil->fertilizer soil] soil)
        water (get-in state [:fertilizer->water fertilizer] fertilizer)
        light (get-in state [:water->light water] water)
        temperature (get-in state [:light->temperature light] light)
        humidity (get-in state [:temperature->humidity temperature] temperature)
        location (get-in state [:humidity->location humidity] humidity)]
    location))

(defn solve [data]
  (let [state (parse data)
        locations (pmap (partial get-location state) (:seeds state))]
    (apply min locations)))

(comment
  (solve sample)
  (solve data))

