(ns diamond.core
  (:require [clojure.string :as str]))

(defn alphabet
  [start end]
  (map char (range (int start) (inc (int end)))))

(defn indexed-letters
  [letter]
  (let [letters (map-indexed list (alphabet \A letter))]
    (concat letters
            (-> letters
                reverse
                rest))))

(defn make-line
  "Create each line within Diamond"
  [width indexed-letter]
  (let [[index letter] indexed-letter]
    (if (= letter \A)
      (let [padding (str/join (repeat (/ width 2) \space))]
        (str padding letter padding))
      (let [innerwidth (dec (* index 2))
            innerpadding (str/join (repeat innerwidth \space))
            padding (str/join (repeat (/ (- width innerwidth 2) 2) \space))]
        (str padding letter innerpadding letter padding)))))

(defn make
  "Creates Diamond"
  [letter]
  (let [letters (indexed-letters letter)
        width (count letters)]
    (str/join "\n" (map (partial make-line width) letters))))
