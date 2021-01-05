(ns alphabet-cipher.core)

(def alphabet "abcdefghijklmnopqrstuvwxyz")
(def index-alpha (into {} (map-indexed (fn [i l] [l i]) alphabet)))
(def alpha-index (into {} (map-indexed vector alphabet)))

(defn find-char [c m]
  (let [ci (get index-alpha c)
        ri (get index-alpha m)
        result (+ ci ri)]
    (get alpha-index result)))

(find-char \b \a)

(defn encode [keyword message]
  (if (some? keyword)
    (apply str (map find-char keyword message))
    ""))

(comment
  (encode "b" "a") 
  (encode "a" "c"))
