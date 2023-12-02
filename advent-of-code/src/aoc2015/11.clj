(ns aoc2015.11
  (:require
   [aoc2015.10 :as pairs]))

(def alphabet "abcdefghijklmnopqrstuvwxyz")

(def alphabet-sequences (set (map set (partition 3 1 alphabet))))

(def next-letter (zipmap alphabet (drop 1 (cycle alphabet))))

(defn- alphabet-sequential?
  [three]
  (some (fn [expected]
          (= three expected)) alphabet-sequences))

(defn contains-3-sequential?
  [password]
  (let [threes (map set (partition 3 1 password))
        maybe-seqentials? (map alphabet-sequential? threes)
        any-seqential? (some true? maybe-seqentials?)]
    (if any-seqential? true false)))

(defn contains-pairs?
  [password]
  (let [permutations (pairs/process-turn password)]
    (>= (count (filter (fn [[_ cnt]] (>= cnt 2)) permutations)) 2)))

(defn inc-last [coll value] (conj (pop coll) value))

(defn increment-fn
  [password]
  (if (empty? password)
    password
    (let [last-letter (peek password)
          next-letter (next-letter last-letter)]
      (if (= next-letter \a)
        (conj (increment-fn (pop password)) next-letter)
        (inc-last password next-letter)))))

(defn increment [password] (apply str (increment-fn (vec password))))

(defn valid?
  [password]
  (and (contains-3-sequential? password)
       (contains-pairs? password)))

(defn solve [password]
  (let [new-password (increment password)]
    (if (valid? new-password)
      new-password
      (solve new-password))))

; (solve "hxbxwxba")
; abcdefghijklmnopqrstuvwxyz
; hxbxxvww - Mine
; hxbxxyzz - Correct
