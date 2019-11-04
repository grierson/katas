(ns bowling.core
  (:require [clojure.string :as str]))

; Game - 10 Frames
; Frame - 10 lines

(defn strike? [[throw _]] (= throw \X))
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

(defn get-next-ball [line index]
  (let [next-frame (nth line (+ index 1) [0 0])]
    (if (strike? next-frame)
      10
      (first next-frame))))

(defn get-second-ball [line index]
  (let [next-frame (nth line (+ index 1) [0 0])]
    (if (strike? next-frame)
      10
      (second next-frame))))

(defn score-frame [line frame-index]
  (let [[throw1 throw2 throw3 :as frame] (nth line frame-index)]
    (cond
      (strike? frame) (+ 10 (get-next-ball line frame-index) (get-second-ball line frame-index))
      (spare? frame) (+ 10 (get-next-ball line frame-index) (or throw3 0))
      :else (+ throw1 throw2 (or throw3 0)))))

(defn score [line]
  (let [game (line->data line)]
    (reduce + (map-indexed (fn [i _] (score-frame game i)) game))))

(comment
  (score "X X X X X X X X X X X X"))
