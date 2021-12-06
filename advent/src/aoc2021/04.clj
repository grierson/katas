(ns aoc2021.04
  (:require [clojure.string :as str]))

(def sample "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1\n\n22 13 17 11  0\n 8  2 23  4 24\n21  9 14 16  7\n 6 10  3 18  5\n 1 12 20 15 19\n\n 3 15  0  2 22\n 9 18 13 17  5\n19  8  7 25 23\n20 11 10 24  4\n14 21 16 12  6\n\n14 21 17 24  4\n10 16 15  9 19\n18  8 23 26 20\n22 11 13  6  5\n 2  0 12  3  7")

(defn line->data [line]
  (map (comp #(hash-map :value %
                        :marked false) parse-long) (re-seq #"\d+" line)))

(defn board->data [board]
  (map line->data board))

(defn input->data [input]
  (let [data (str/split-lines input)]
    {:numbers (map parse-long (str/split (first data) #","))
     :boards  (map board->data (partition 5 (filter not-empty (drop 2 data))))}))

(defn mark-line [line called-number]
  (map (fn [{:keys [value] :as number}]
         (if (= value called-number)
           (update number :marked not)
           number))
       line))

(defn mark-board [board called-number]
  (map #(mark-line % called-number) board))

(defn all-marked? [numbers]
  (every? :marked numbers))

(defn bingo-row? [board]
  (some true? (map all-marked? board)))

(defn bingo-column? [board]
  (let [column1 (map (fn [line] (nth line 0 nil)) board)
        column2 (map (fn [line] (nth line 1 nil)) board)
        column3 (map (fn [line] (nth line 2 nil)) board)
        column4 (map (fn [line] (nth line 3 nil)) board)
        column5 (map (fn [line] (nth line 4 nil)) board)
        columns (vector column1 column2 column3 column4 column5)]
    (some true? (map all-marked? columns))))

(defn bingo? [board]
  (or
    (bingo-row? board)
    (bingo-column? board)))
