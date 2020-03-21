(ns datamunging.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def data (slurp (io/resource "weather.dat")))

(defn strip-star [s]
  (apply str (filter (fn [c] (Character/isDigit c)) s)))

(defn parse-record [record]
  (->> record
       (map strip-star)
       (map #(Integer/parseInt %))))

(defn difference [{:keys [max min]}]
  (- max min))

(def body (->> (str/split-lines data)
               (drop 2)
               (drop-last 1)
               (map #(str/split % #" "))
               (map #(remove empty? %))
               (map #(take 3 %))
               (map parse-record)
               (map #(zipmap [:day :max :min] %))))

(defn find-biggest [records]
  (:day (->> records
             (map #(assoc % :diff (difference %)))
             (apply max-key :diff))))

(find-biggest body)

