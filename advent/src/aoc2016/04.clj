(ns aoc2016.04
  (:require [clojure.string :as str]))


(def sample "aaaaa-bbb-z-y-x-123[abxyz]")
(def sample2 "a-b-c-d-e-f-g-h-987[abcde]")
(def sample3 "not-a-real-room-404[oarel]")

(defn get-name [line]
  (apply str (drop-last (str/split line #"-"))))

(defn order-name [name]
  (map second (group-by second (frequencies name))))

(order-name (get-name sample))
(order-name (get-name sample2))
(order-name (get-name sample3))

(map second (take 5 (group-by second (frequencies (apply str (drop-last (str/split sample #"-")))))))