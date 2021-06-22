(ns xmas-lights.core)


(defn make-grid
  ([] [])
  ([columns rows]
   (for [_ (range 0 columns)]
     (for [_ (range 0 rows)]
       false))))
