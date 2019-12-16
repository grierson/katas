(ns advent2019.004.core)

(defn six? [code]
  (= 6 (count (str code))))

(defn pair? [code]
  (true? (some (fn [[x y]] (= x y)) (partition 2 1 (str code)))))

(defn increase? [code]
  (->> (partition 2 1 (str code))
       (map (fn [[x y]] [(Integer/parseInt (str x)) (Integer/parseInt (str y))]))
       (every? (fn [[x y]] (<= x y)))))

(defn valid? [code]
  ((every-pred six? pair? increase?) code))

(defn solve []
  (->> (range 234208 765869)
       (map valid?)
       (filter true?)
       count))
