(ns advent2019.004.core)

(defn six? [code])
(defn pair? [code])
(defn increase? [code])

(defn valid? [code]
  ((every-pred six? pair? increase?) code))
