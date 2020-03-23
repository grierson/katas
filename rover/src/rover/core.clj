(ns rover.core)

(defn rotate-right [state]
  (update state :direction {:N :E
                            :E :S
                            :S :W
                            :W :N}))

(defn rotate-left [state]
  (update state :direction {:N :W
                            :E :N
                            :S :E
                            :W :S}))

(defn update-state [state action]
  (case action
    \R (rotate-right state)
    \L (rotate-left state)
    state))

(def start-point {:direction :N
                  :x             0
                  :y             0})

(defn move [state [a & as]]
  (if (nil? a)
    state
    (recur (update-state state a) as)))
