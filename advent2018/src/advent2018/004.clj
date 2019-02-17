(ns advent2018.004
  (:require [clojure.java.io :as io]
            [clojure.string :as s]))

(def data (-> "004.txt"
              io/resource
              slurp
              s/split-lines
              sort))

(defn parse-int [s]
  (Integer. (re-find  #"\d+" s)))

(defn get-guard [event]
  (-> event
      (s/split #"#")
      second
      (s/split #" ")
      first
      parse-int))

(defn assoc-events
  "Assign all events to guard id"
  ([coll] (assoc-events {} coll))
  ([state [record & records]]
   (if (nil? record)
     state
     (let [guard (get-guard record)
           events (take-while #(not (re-find #"Guard" %)) records)
           log (drop (count events) records)]
       (if (contains? state guard)
         (assoc-events (update state guard #(concat % events)) log)
         (assoc-events (assoc state guard events) log))))))

(defn get-sleep-time [asleep awake]
  (let [get-min #(parse-int (subs % 15 17))
        awake-min (get-min awake)
        asleep-min (get-min asleep)]
    (- awake-min asleep-min)))

(defn get-total-sleeptime
  ([coll] (get-total-sleeptime 0 coll))
  ([state [asleep awake & records]]
   (if (nil? asleep)
     state
     (get-total-sleeptime (+ (get-sleep-time asleep awake) state) records))))

(def xdata (-> data assoc-events))

(def sleepyist-guard
  (->> xdata
       (map (fn [[k v]] (hash-map k (get-total-sleeptime v))))
       (apply max-key #(val (first %)))
       keys
       first))

(def sleepyist-guard-log (get xdata sleepyist-guard))

(defn get-sleep-range [asleep awake]
  (let [get-min #(parse-int (subs % 15 17))
        awake-min (get-min awake)
        asleep-min (get-min asleep)]
    (range asleep-min awake-min)))

(defn get-total-sleep-range
  ([coll] (get-total-sleep-range [] coll))
  ([state [asleep awake & records]]
   (if (nil? asleep)
     state
     (get-total-sleep-range (into state (get-sleep-range asleep awake)) records))))

(def freq (frequencies (get-total-sleep-range sleepyist-guard-log)))

(def sleepyist-guard-min
  (->> sleepyist-guard-log
       (get-total-sleep-range)
       frequencies
       (sort-by val >)
       ffirst))

(* sleepyist-guard
   sleepyist-guard-min)
