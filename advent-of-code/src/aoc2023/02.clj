(ns aoc2023.02
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(require 'hashp.core)

(def data (slurp (io/resource "aoc2023/02.txt")))

(def sample-data
  "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green")

(def sample-rules {:red 12
                   :green 13
                   :blue 14})

(defn- find-colors
  [color game]
  (let [color-regex (str "(\\d+) " color)]
    (->> (re-seq (re-pattern color-regex) game)
         (map second)
         (map parse-long))))

(defn parse-game
  [game]
  (let [game-id (parse-long (re-find #"\d+" game))
        reds (find-colors "red" game)
        blues (find-colors "blue" game)
        greens (find-colors "green" game)]
    [game-id {:red reds
              :blue blues
              :green greens}]))

(defn valid? [rules [_ colors]]
  (let [{:keys [red green blue]} colors]
    (and
     (every? #(<= % (:red rules)) red)
     (every? #(<= % (:blue rules)) blue)
     (every? #(<= % (:green rules)) green))))

(defn solve [rules data]
  (let [valid-games
        (->> data
             str/split-lines
             (map #(parse-game %))
             (filter #(valid? rules %)))
        possible-game-ids (map first valid-games)]
    (reduce + 0 possible-game-ids)))

; (solve sample-rules sample-data)
; (solve sample-rules data)
