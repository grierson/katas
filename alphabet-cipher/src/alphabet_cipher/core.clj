(ns alphabet-cipher.core)

(def alphabet "abcdefghijklmnopqrstuvwxyz")
(def index-alpha (into {} (map-indexed (fn [i l] [l i]) alphabet)))
(def alpha-index (into {} (map-indexed vector alphabet)))

(defn find-char [c m]
  (let [ci (get index-alpha c)
        ri (get index-alpha m)
        result (+ ci ri)]
    (get alpha-index result)))

(defn repeat-keyword [kword message]
  (apply str (take (count message) (cycle kword))))

(defn encode [kword message]
  (if (some? kword)
    (apply str (map find-char (repeat-keyword kword message) message))
    ""))


(comment
  "Keyword: sconessconessco"
  "Message: meetmebythetree"
  (encode "s" "m"))
