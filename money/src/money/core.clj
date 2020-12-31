(ns money.core)

(defrecord Currency [divisor sym desc])
(defrecord Money [amount currency])

(defn *$ [{:keys [amount currency]} n]
  (->Money (* n amount) currency))

(defn +$ [m1 m2]
  (->Money (+ (:amount m1) (:amount m2)) (:currency m1)))

(defn make-money
  ([] (make-money 0))
  ([amount] (make-money amount :usd))
  ([amount currency] (->Money amount currency)))

