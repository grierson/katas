(ns alphabet-cipher.core)

(def alphabet "abcdefghijklmnopqrstuvwxyz")
(def alpha-index (into {} (map-indexed (fn [i v] [v i]) alphabet)))

(defn lookup [c r]
  (let [ci (get alpha-index c)
        ri (get alpha-index r)
        new-alphabet (concat (drop ci alphabet) (take ri alphabet))]
    (nth new-alphabet ri)))

(defn repeat-keyword [kword message]
  (apply str (take (count message) (cycle kword))))

(defn encode [kw msg]
  (apply str (map lookup (repeat-keyword kw msg) msg)))
