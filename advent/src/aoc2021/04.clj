(ns aoc2021.04
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(def sample "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1\n\n22 13 17 11  0\n 8  2 23  4 24\n21  9 14 16  7\n 6 10  3 18  5\n 1 12 20 15 19\n\n 3 15  0  2 22\n 9 18 13 17  5\n19  8  7 25 23\n20 11 10 24  4\n14 21 16 12  6\n\n14 21 17 24  4\n10 16 15  9 19\n18  8 23 26 20\n22 11 13  6  5\n 2  0 12  3  7")
(def file (slurp (io/resource "aoc2021/04.txt")))

(defn data->board [data]
  (map (comp #(hash-map :number %
                        :marked? false)
             parse-long) data))

(defn data->boards [board]
  (map data->board (map #(re-seq #"\d+" %) board)))

(defn data->game [input]
  (let [data (str/split-lines input)
        numbers (str/split (first data) #",")
        boards (map #(str/join " " %) (partition 5 (filter not-empty (drop 2 data))))]
    {:numbers (map parse-long numbers)
     :boards  (data->boards boards)}))

(defn mark-number [{:keys [number] :as item} called-number]
  (if (= number called-number)
    (update item :marked? not)
    item))

(defn mark-board [board called-number]
  (map #(mark-number % called-number) board))

(defn all-marked? [items]
  (every? :marked? items))

(defn get-rows [board]
  (partition 5 board))

(defn get-columns [board]
  (let [columns (map #(drop % board) (range 5))]
    (map #(take-nth 5 %) columns)))

(defn bingo? [board]
  (let [rows (get-rows board)
        columns (get-columns board)
        permutations (concat rows columns)]
    (some all-marked? permutations)))

(defn total [board]
  (->> board
       (remove :marked?)
       (map :number)
       (apply +)))

(defn update-game [{:keys [numbers boards]}]
  (let [[x & xs] numbers]
    {:numbers xs
     :boards (map (fn [board] (mark-board board x)) boards)}))

(defn call-numbers [{:keys [numbers] :as game}]
  (let [[x & _] numbers
        {:keys [boards] :as next-game} (update-game game)]
    (if-let [bingo (first (filter bingo? boards))]
      {:number x
       :board  bingo}
      (recur next-game))))

(defn remove-bingos [game]
  (update game :boards #(remove bingo? %)))

(defn find-losing-board [game]
  (let [next-game (remove-bingos (update-game game))]
    (if (= 1 (count (:boards next-game)))
      (call-numbers next-game)
      (recur next-game))))

(defn solve [{:keys [board number]}]
  (let [value (total board)]
    (* value number)))

(comment
  (solve (call-numbers (data->game sample)))
  (solve (call-numbers (data->game file)))
  (solve (find-losing-board (data->game sample)))
  (solve (find-losing-board (data->game file))))
