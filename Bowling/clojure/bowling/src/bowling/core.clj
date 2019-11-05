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
    (strike? frame) [\X]
    (some? throw3) [(char->value throw1) (char->value throw2) (char->value throw3)]
    :else [(char->value throw1) (char->value throw2)]))

(defn line->data [line]
  (let [frames (str/split line #" ")]
    (if (= (count frames) 12)
      (seq (conj (vec (map str->value (drop 3 frames))) (vec (mapcat str->value (take-last 3 frames)))))
      (map str->value frames))))

(defn sum-frame [[throw1 throw2]]
  (+ throw1 throw2))

(defn score-ball [ball]
  (if (= ball \X)
    10
    ball))

(defn score-last-frame [[_ throw2 throw3 :as frame]]
  (cond
    (strike? frame) (+ 10 (score-ball throw2) (score-ball throw3))
    (spare? frame) (+ 10 (score-ball throw3))
    :else (sum-frame frame)))

(defn score-strike [line index]
  (let [frame (nth line index [0 0])
        next-frame (nth line (+ index 1) [0 0])]
    (cond
      (= (count frame) 3) (score-last-frame frame)
      (and (strike? frame) (strike? next-frame)) 30
      (and (strike? frame) (not (strike? next-frame))) (+ 20 (first next-frame))
      :else (+ 10 (sum-frame frame)))))

(defn score-spare [line index]
  (let [frame (nth line index [0 0])]
    (cond
      (= (count frame) 3) (score-last-frame frame)
      (strike? frame) 20
      :else (+ 10 (first frame)))))

(defn score-frame [line [frame-index frame]]
  (cond
    (= (count frame) 3) (score-last-frame frame)
    (strike? frame) (score-strike line (inc frame-index))
    (spare? frame) (score-spare line (inc frame-index))
    :else (sum-frame frame)))

(defn score [line]
  (let [game (line->data line)]
    (->> game
      (map-indexed (fn [i v] (score-frame game [i v])))
      (reduce +))))
