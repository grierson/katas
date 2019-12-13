(ns advent2019.004.core)

(defn six? [code]
  (= 6 (count (str code))))

(defn pair? [code]
  true)

(defn increase? [code])

(defn valid? [code]
  ((every-pred six? pair? increase?) code))
