(ns aoc2020.11
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn neighbour-locations
  "Returns all valid surrounding spaces"
  [max-x max-y [y x]]
  (for [*x (range (max 0 (dec x)) (inc (min max-x (inc x))))
        *y (range (max 0 (dec y)) (inc (min max-y (inc y))))
        :when (not (and (= x *x) (= y *y)))]
    [*y *x]))

(defn neighbours->values
  [lines neighbours]
  (map #(get-in lines %) neighbours))

(defn get-neighbours [lines max-x max-y position]
  (neighbours->values lines (neighbour-locations max-x max-y position)))

(defn crowded?
  "4 or more occupied (#) seats"
  [neighbours]
  (let [occupied (count (filter #{\#} neighbours))]
    (>= occupied 4)))

(defn open?
  "No occupied (#) seats"
  [neighbours]
  (not-any? #{\#} neighbours))

(defn apply-rules
  [neighbours value]
  (condp = value
    \# (if (crowded? neighbours) \L value)
    \L (if (open? neighbours) \# value)
    \. value))

(defn generation [lines]
  (mapv vec (let [max-x (count (first lines))
                  max-y (count lines)]
              (for [row (range max-y)]
                (for [column (range max-x)]
                  (let [location [row column]]
                    (apply-rules (get-neighbours lines max-x max-y location) (get-in lines location))))))))

(comment
  (def init (mapv vec (str/split-lines "L.LL.LL.LL\nLLLLLLL.LL\nL.L.L..L..\nLLLL.LL.LL\nL.LL.LL.LL\nL.LLLLL.LL\n..L.L.....\nLLLLLLLLLL\nL.LLLLLL.L\nL.LLLLL.LL")))
  (def first-state (mapv vec (str/split-lines "#.##.##.##\n#######.##\n#.#.#..#..\n####.##.##\n#.##.##.##\n#.#####.##\n..#.#.....\n##########\n#.######.#\n#.#####.##")))
  (def second-state (mapv vec (str/split-lines "#.LL.L#.##\n#LLLLLL.L#\nL.L.L..L..\n#LLL.LL.L#\n#.LL.LL.LL\n#.LLLL#.##\n..L.L.....\n#LLLLLLLL#\n#.LLLLLL.L\n#.#LLLL.##")))
  (def third-state (mapv vec (str/split-lines "#.##.L#.##\n#L###LL.L#\nL.#.#..#..\n#L##.##.L#\n#.##.LL.LL\n#.###L#.##\n..#.#.....\n#L######L#\n#.LL###L.L\n#.#L###.##")))
  (= (generation init) first-state)
  (= (generation (generation init)) second-state)
  (= (generation (generation (generation init))) third-state))

(def input (mapv vec (str/split-lines (slurp (io/resource "aoc2020/11.txt")))))

(defn stable [state]
  (let [state* (generation state)]
    (if (= state state*)
      state*
      (recur state*))))

; Solve 1
;(count (filter #{\#} (flatten (stable input))))