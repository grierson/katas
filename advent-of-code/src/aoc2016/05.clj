(ns aoc2016.05
  (:require [clj-commons.digest :as digest]
            [clojure.string :as str]))

(defn valid? [hash]
  (str/starts-with? hash "00000"))

(defn password-complete? [password]
  (not (some? (some #{\_} password))))

(defn update-password [s idx replacement]
  (if (= \_ (get s idx))
    (str (subs s 0 idx) replacement (subs s (inc idx)))
    s))

(defn append-valid-fn
  [append-fn password id]
  (let [hash (digest/md5 id)]
    (if (valid? hash)
      (append-fn password hash)
      password)))

(def append-valid-hash
  (partial append-valid-fn (fn [password hash]
                             (str password (nth hash 5)))))

(def append-valid-hash2
  (partial append-valid-fn
           (fn [password hash]
             (let [index (parse-long (str (nth hash 5)))
                   value (nth hash 6)]
               (update-password password index value)))))

(defn find-hashes-fn
  [valid-fn append-fn state id]
  (if (valid-fn state)
    (reduced state)
    (append-fn state id)))

(def find-all-hashes 
  (partial find-hashes-fn #(= 8 (count %)) append-valid-hash))

(def find-all-hashes2 
  (partial find-hashes-fn password-complete? append-valid-hash2))

(defn solve-fn [f state secret]
  (reduce
   f
   state
   (lazy-seq (map #(str secret %) (range)))))

(def solve (partial solve-fn find-all-hashes ""))
(def solve2 (partial solve-fn find-all-hashes2 "________"))

(comment
  (solve "abc")
  (solve2 "abc")
  (solve "reyedfim")
  (solve2 "reyedfim"))
