(ns advent2019.004.core)

(defn six? [code]
  (= 6 (count (str code))))

(defn pair? [code]
  (some (fn [[x y]] (= x y)) (partition 2 1 (str code)))) 

(defn increase? [code])

(defn valid? [code]
  ((every-pred six? pair? increase?) code))

(pair? 12) 
