(ns grierson.product-array
  (:gen-class))

(defn get-others
  [numbers idx]
  (concat
   (take idx numbers)
   (drop (inc idx) numbers)))

(defn product
  [numbers]
  (apply * numbers))

(defn run
  [numbers]
  (if (<= (count numbers) 1)
    numbers
    (let [indexes (range (count numbers))]
      (->> indexes
           (map (partial get-others numbers))
           (map product)))))
