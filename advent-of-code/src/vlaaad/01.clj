(ns vlaaad.01)

(def amt {\a 1 \b 1 \d 1 \e 1 \g 1 \o 1 \p 1 \q 1 \A 1 \D 1 \O 1 \P 1 \Q 1 \R 1 \4 1 \6 1 \9 1 \0 1 \B 2 \8 2})

(defn eggs [s] (reduce (fn [cnt c] (+ cnt (amt c 0))) 0 s))
