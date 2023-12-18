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
  (map numbers vs))

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

(defn get-id [[destination source steps] value]
  (when  (and (>= value source)
              (<= value (+ source steps)))
    (let [difference (- value source)]
      (+ destination difference))))

(defn try-get-id [mapping value]
  (if-let [result (some #(when-let [id (get-id % value)] id) mapping)]
    result
    value))

(defn get-location [state seed]
  (let [soil  (try-get-id (:seed->soil state) seed)
        fertilizer  (try-get-id (:soil->fertilizer state) soil)
        water  (try-get-id (:fertilizer->water state) fertilizer)
        light  (try-get-id (:water->light state) water)
        temperature  (try-get-id (:light->temperature state) light)
        humidity  (try-get-id (:temperature->humidity state) temperature)
        location  (try-get-id (:humidity->location state) humidity)]
    location))

(defn solve [data]
  (let [state (parse data)
        locations (pmap (partial get-location state) (:seeds state))]
    (apply min locations)))

(defn solve2 [data]
  (let [state (parse data)
        seed-ranges (partition 2 2 (:seeds state))
        seeds (map (fn [[start til]] (range start (+ start til))) seed-ranges)
        seeds (flatten seeds)
        seeds (distinct seeds)
        locations (pmap (partial get-location state) seeds)]
    (apply min locations)))

(comment
  (parse sample)
  (solve sample)
  (solve2 sample)
  (solve data)
  (solve2 data))

