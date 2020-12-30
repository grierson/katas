(ns bowling.core)

(defn spare? [frame] (= (apply + frame) 10))
(defn strike? [[throw1]] (= throw1 10))
(defn last-frame? [frame] (= (count frame) 3))

(defn score-frame [frame
                   [next-throw1 next-throw2 :as next-frame]
                   [nnext-throw1]]
  (cond
    (last-frame? frame) (apply + frame)
    (strike? frame) (if (strike? next-frame)
                      (if (last-frame? next-frame)
                        (+ 10 10 next-throw2)
                        (+ 10 10 nnext-throw1))
                      (+ 10 (+ next-throw1 next-throw2)))
    (spare? frame) (+ 10 next-throw1)
    :else (apply + frame)))

(defn score [line]
  (->> (conj (vec line) [0 0] [0 0])
       (partition 3 1)
       (map (fn [[frame next-frame nnext-frame]] (score-frame frame next-frame nnext-frame)))
       (apply +)))
