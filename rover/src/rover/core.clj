(ns rover.core)

(def actions {\F {:N [:y inc]
                  :E [:x inc]
                  :W [:x dec]
                  :S [:y dec]}
              \B {:N [:y dec]
                  :E [:x dec]
                  :S [:y inc]
                  :W [:x inc]}
              \R {:N :E
                  :E :S
                  :S :W
                  :W :N}
              \L {:N :W
                  :E :N
                  :S :E
                  :W :S}})

(defn wrap [f v]
  (mod (f v) 11))

(defn move [{:keys [direction] :as state} action]
  (let [[axis velocity] (get-in actions [action direction])]
    (update state axis #(wrap velocity %))))

(defn rotate [{:keys [direction] :as state} action]
  (let [new-direction (get-in actions [action direction])]
    (assoc state :direction new-direction)))

(defn update-state [state action]
  (cond
    (contains? #{\R \L} action) (rotate state action)
    (contains? #{\F \B} action) (move state action)
    :else state))

(defn play [state [a & as]]
  (if (nil? a)
    state
    (recur (update-state state a) as)))
