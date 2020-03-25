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

(defn move-forward [{:keys [direction] :as state}]
  (case direction
    :N (update state :y inc)
    :S (update state :y dec)
    :E (update state :x inc)
    :W (update state :x dec)))

(defn move-backward [{:keys [direction] :as state}]
  (case direction
    :N (update state :y dec)
    :S (update state :y inc)
    :E (update state :x dec)
    :W (update state :x inc)))

(defn update-state [state action]
  (case action
    \R (rotate-right state)
    \L (rotate-left state)
    \F (move-forward state)
    \B (move-backward state)
    state))

(def start-point {:direction :N
                  :x         0
                  :y         0})

(defn play [state [a & as]]
  (if (nil? a)
    state
    (recur (update-state state a) as)))
