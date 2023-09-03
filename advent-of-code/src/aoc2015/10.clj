(ns aoc2015.10)

(defn inc-last [coll]
  (let [[value count] (peek coll)]
    (conj (pop coll) [value (inc count)])))

(defn- process
  [state [previous current]]
  (cond
    (= previous \X)
    (conj state [current 1])

    (= previous current)
    (inc-last state)

    :else
    (conj state [current 1])))

(defn turn [number]
  (let [prefix "X"
        number (str prefix number)
        segments (partition 2 1 number)
        state (reduce process [] segments)
        state (map (fn [[character count]] (str count character)) state)]
    (apply str state)))

(comment
  (count (last (take 41 (iterate turn "1321131112"))))
  (count (last (take 51 (iterate turn "1321131112")))))
