(ns mars-rover.data)

(def clockwise {:N :E
                :E :S
                :S :W
                :W :N})

(def counter-clockwise {:N :W
                        :E :N
                        :S :E
                        :W :S})

(def moves {\F {:N [:y inc]
                :E [:x inc]
                :W [:x dec]
                :S [:y dec]}
            \B {:N [:y dec]
                :E [:x dec]
                :S [:y inc]
                :W [:x inc]}})

(defn rotate [state turn]
  (let [rotation (get {\R clockwise
                       \L counter-clockwise} turn)]
    (update state :direction rotation)))

(defn wrap [f v]
  (mod (f v) 11))

(defn accelerate [state axis f]
  (update state axis #(wrap f %)))

(defn move [{:keys [direction] :as state} action]
  (let [[axis a] (get-in moves [action direction])]
    (accelerate state axis a)))

(defn update-state [state action]
  (cond
    (contains? #{\R \L} action) (rotate state action)
    (contains? #{\F \B} action) (move state action)
    :else state))

(defn play [state [a & as]]
  (if (nil? a)
    state
    (recur (update-state state a) as)))
