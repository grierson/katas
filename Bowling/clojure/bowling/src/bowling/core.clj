(ns bowling.core
  (:require [clojure.string :as str]))

; Game - 10 Frames
; Frame - 10 lines

(defn strike? [frame] (= frame "X"))
(defn spare? [[_ throw2 throw3]]
  (if (some? throw3)
    (= throw3 \/)
    (= throw2 \/)))


(defn c->int [c]
  (try
    (Integer/parseInt (str c))
    (catch NumberFormatException _ 0)))
(defn sum [[throw1 throw2 throw3]]
  (+ (c->int throw1) (c->int throw2) (c->int throw3)))

(defn indices [pred coll]
  (keep-indexed #(when (pred %2) %1) coll))

(defn game->data [game]
  (str/split game #" "))

(defn numberify [frame]
  (cond
    (strike? frame) 10
    (spare? frame) 10
    :else (sum frame)))

(defn get-spare-extra [game spare-indices]
  (reduce + (map #(nth game (+ % 1) 0) spare-indices)))

(defn get-strike-extra [game strike-indices]
  (reduce + (map #(+ (nth game (+ % 1) 0)
                     (nth game (+ % 2) 0)) strike-indices)))


(defn score [game]
  (let [dataify-game (game->data game)
        numberify-game (map numberify dataify-game)
        score-game (reduce + numberify-game)
        spares (indices spare? dataify-game)
        spare-extra (get-spare-extra numberify-game spares)
        strikes (indices strike? dataify-game)
        strikes-extra (get-strike-extra numberify-game strikes)]
    (+ score-game spare-extra strikes-extra)))
