(ns aoc2016.05
  (:require [clj-commons.digest :as digest]
            [clojure.string :as str]))

(defn valid? [hash]
  (str/starts-with? hash "00000"))

(defn append-valid-hash [state id]
  (let [hash (digest/md5 id)]
    (if (valid? hash)
      (conj state hash)
      state)))

(defn found-all-hashes
  [state id]
  (if (= 8 (count state))
    (reduced state)
    (append-valid-hash state id)))

(defn finder [secret]
  (reduce
   found-all-hashes
   []
   (lazy-seq (map #(str secret %) (range)))))

(defn solve [secret]
  (let [hashs (finder secret)]
    (apply str (map #(nth % 5) hashs))))

; ---

(defn update-password [s idx replacement]
  (if (= \_ (get s idx))
    (str (subs s 0 idx) replacement (subs s (inc idx)))
    s))

(defn valid2? [hash]
  (and
   (str/starts-with? hash "00000")))

(defn password-complete? [password]
  (not (some? (some #{\_} password))))

(defn append-valid-hash2 [password id]
  (let [hash (digest/md5 id)]
    (if (valid2? hash)
      (let [index (parse-long (str (nth hash 5)))
            value (nth hash 6)]
        (update-password password index value))
      password)))

(defn found-all-hashes2
  [password id]
  (if (password-complete? password)
    (reduced password)
    (append-valid-hash2 password id)))

(defn solve2 [secret]
  (reduce
   found-all-hashes2
   "________"
   (lazy-seq (map #(str secret %) (range)))))

(comment
  (solve "abc")
  (solve2 "abc")
  (solve "reyedfim")
  (solve2 "reyedfim"))
