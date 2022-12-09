(ns aoc2022.08
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(def sample "30373
25512
65332
33549
35390")

(defn parse [lines]
  (mapv
   #(mapv (comp parse-long str) %)
   (str/split-lines lines)))

(defn surrounding [size [x y]]
  {:left (reverse (map #(vector x %) (range y)))
   :right (map #(vector x %) (range (inc y) (inc size)))
   :up (reverse (map #(vector % y) (range x)))
   :down (map #(vector % y) (range (inc x) (inc size)))})

(defn surrounding-trees [grid location]
  (let [size (dec (count grid))
        {:keys [left right up down]}
        (surrounding size location)]
    {:left (map #(get-in grid % 0) left)
     :right (map #(get-in grid % 0) right)
     :up (map #(get-in grid % 0) up)
     :down (map #(get-in grid % 0) down)}))

(defn visible? [tree {:keys [left right up down]}]
  (let [result {:left  (every? #(> tree %) left)
                :right (every? #(> tree %) right)
                :up (every? #(> tree %) up)
                :down (every? #(> tree %) down)}]
    ((complement not-any?) true? (vals result))))

(defn locations [grid]
  (let [end (count grid)]
    (for [x (range end)
          y (range end)]
      [x y])))

(defn solve [grid]
  (->> grid
       locations
       (map
        (fn [location]
          (visible?
           (get-in grid location)
           (surrounding-trees grid location))))
       (filter true?)
       count))

(defn step
  ([height trees] (step 0 height trees))
  ([cnt height trees]
   (cond
     (empty? trees) cnt
     (< (first trees) height) (recur (inc cnt) height (rest trees))
     :else (inc cnt))))

(defn distance [height {:keys [left right up down]}]
  {:left (step height left)
   :right (step height right)
   :up (step height up)
   :down (step height down)})

(defn solve2 [grid]
  (->> grid
       locations
       (map (fn [location]
              (distance
               (get-in grid location)
               (surrounding-trees grid location))))
       (map (fn [{:keys [left right up down]}] (* up left right down)))
       (apply max)))

(comment
  (def grid (parse sample))
  (def data (parse (slurp (io/resource "aoc2022/08.txt"))))

  (solve grid)
  (solve data)

  (solve2 grid)
  (solve2 data))

