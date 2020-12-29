(ns romannumerals.core)

(defn number->roman [number]
  (cond
    (= number 0) ""
    (<= number 3) (apply str (take number (repeat "I")))
    (>= number 1000) (str "M" (number->roman (- number 1000)))
    (>= number 500) (str "D" (number->roman (- number 500)))
    (>= number 100) (str "C" (number->roman (- number 100)))
    (>= number 90) (str "XC" (number->roman (- number 90)))
    (>= number 50) (str "L" (number->roman (- number 50)))
    (>= number 40) (str "XL" (number->roman (- number 40)))
    (>= number 10) (str "X" (number->roman (- number 10)))
    (>= number 9) (str "IX" (number->roman (- number 9)))
    (>= number 5) (str "V" (number->roman (- number 5)))
    (>= number 4) (str "IV" (number->roman (- number 4)))))
