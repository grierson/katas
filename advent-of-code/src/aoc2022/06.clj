(ns aoc2022.06
  (:require
   [clojure.java.io :as io]))

(def data (slurp (io/resource "aoc2022/06.txt")))

(defn marker-fn [amount section]
  (= amount (count (set section))))

(def marker? (partial marker-fn 4))
(def marker2? (partial marker-fn 14))

(defn find-marker-fn [marker-fn? size datastream]
  (+ size
     (some (fn [[idx section]]
             (when (marker-fn? section)
               idx))
           (partition 2 (interleave (range)
                                    (partition size 1 datastream))))))

(def find-marker (partial find-marker-fn marker? 4))
(def find-marker2 (partial find-marker-fn marker2? 14))

(comment
  (find-marker "bvwbjplbgvbhsrlpgdmjqwftvncz")
  (find-marker "nppdvjthqldpwncqszvftbrmjlhg")
  (find-marker "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")
  (find-marker "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")
  (find-marker data)

  (find-marker2 "mjqjpqmgbljsphdztnvjfqwrcgsmlb")
  (find-marker2 "bvwbjplbgvbhsrlpgdmjqwftvncz")
  (find-marker2 "nppdvjthqldpwncqszvftbrmjlhg")
  (find-marker2 "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")
  (find-marker2 "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")
  (find-marker2 data))
