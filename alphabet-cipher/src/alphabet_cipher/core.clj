(ns alphabet-cipher.core)

(def alphabet "abcdefghijklmnopqrstuvwxyz")
(def alpha-index (into {} (map-indexed (fn [i v] [v i]) alphabet)))

(defn lookup [c r]
  (let [ci (get alpha-index c)
        ri (get alpha-index r)
        new-alphabet (concat (drop ci alphabet) (take ci alphabet))]
    (nth new-alphabet ri)))

(defn encode [kw msg]
  (apply str (map lookup (cycle kw) msg)))
