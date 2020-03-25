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

(defn wrap [f v]
  (mod (f v) 11))

(defn move-forward [{:keys [direction] :as state}]
  (case direction
    :N (update state :y #(wrap inc %))
    :E (update state :x #(wrap inc %))
    :S (update state :y #(wrap dec %))
    :W (update state :x #(wrap dec %))))

(defn move-backward [{:keys [direction] :as state}]
  (case direction
    :N (update state :y #(wrap dec %))
    :E (update state :x #(wrap dec %))
    :S (update state :y #(wrap inc %))
    :W (update state :x #(wrap inc %))))

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
