(ns rover.core)

(defn rotate-right [current]
  (get {:N :E
        :E :W} current))

(defn move [{:keys [direction]} moves]
  {:direction (rotate-right direction)})
