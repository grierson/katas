(ns bowling.core
  (:require [clojure.string :as str]))

; Game - 10 Frames
; Frame - 10 lines

(defn strike? [[throw]] (= throw \X))
(defn spare? [[_ throw]] (= throw \/))

(defn char->value [char]
  (let [value (Character/digit char 10)]
    (cond
      (pos? value) value
      (= char \-) 0
      :else char)))

(defn str->value [[throw1 throw2 throw3 :as frame]]
  (cond
    (some? throw3) [(char->value throw1) (char->value throw2) (char->value throw3)]
    (strike? frame) [\X]
    :else [(char->value throw1) (char->value throw2)]))

(defn line->data [line]
  (let [frames (str/split line #" ")]
    (map str->value frames)))

(defn sum-frame [[throw1 throw2]]
  (+ throw1 throw2))

(defn score-ball [ball]
  (if (= ball \X) 10 ball))

(defn score-last-frame [[_ throw2 throw3 :as frame]]
  (cond
    (strike? frame) (+ 10 (score-ball throw2) (score-ball throw3))
    (spare? frame) (+ 10 (score-ball throw3))
    :else (sum-frame frame)))

(defn score-strike [frame next-frame]
  (cond
    (= (count frame) 3) (score-last-frame frame)
    (and (strike? frame) (strike? next-frame)) 30
    (and (strike? frame) (not (strike? next-frame))) (+ 20 (first next-frame))
    :else (+ 10 (sum-frame frame))))

(defn score-spare [frame]
  (cond
    (= (count frame) 3) (score-last-frame frame)
    (strike? frame) 20
    :else (+ 10 (first frame))))

(defn score-frame [line [frame-index current]]
  (let [next-frame (nth line (+ frame-index 1) [0 0])
        nnext-frame (nth line (+ frame-index 2) [0 0])]
    (cond
      (= (count current) 3) (score-last-frame current)
      (strike? current) (score-strike next-frame nnext-frame)
      (spare? current) (score-spare next-frame)
      :else (sum-frame current))))

(defn score [line]
  (let [game (line->data line)]
    (->> game
      (map-indexed (fn [i v] (score-frame game [i v])))
      (reduce +))))
