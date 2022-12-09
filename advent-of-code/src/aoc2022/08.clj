(ns aoc2022.08
  (:require
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
  {:left (map #(vector x %) (range y))
   :right (map #(vector x %) (range (inc y) (inc size)))
   :top (map #(vector % y) (range x))
   :bottom (map #(vector % y) (range (inc x) (inc size)))})

(defn surrounding-trees [grid location]
  (let [size (dec (count grid))
        {:keys [left right top bottom]}
        (surrounding size location)]
    {:left (map #(get-in grid % 0) left)
     :right (map #(get-in grid % 0) right)
     :top (map #(get-in grid % 0) top)
     :bottom (map #(get-in grid % 0) bottom)}))

(require '[hashp.core])

(defn visible? [tree {:keys [left right top bottom]}]
  (let [result {:left  (every? #(> tree %) left)
                :right (every? #(> tree %) right)
                :top (every? #(> tree %) top)
                :bottom (every? #(> tree %) bottom)}]
    ((complement not-any?) true? (vals result))))

(defn locations [grid]
  (let [end (count grid)]
    (for [x (range end)
          y (range end)]
      [x y])))

(defn solve [grid]
  (map
   (fn [location]
     [location (visible? (get-in grid location) (surrounding-trees grid location))])
   (locations grid)))
