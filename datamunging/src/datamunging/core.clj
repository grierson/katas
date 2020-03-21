(ns datamunging.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def weather (slurp (io/resource "weather.dat")))
(def football (slurp (io/resource "football.dat")))

;; Helpers

(defn strip-star [s]
  (apply str (filter (fn [c] (Character/isDigit c)) s)))

(defn str->int [s]
  (Integer/parseInt (strip-star s)))

(defn keys->int [m [k & ks]]
  (if (some? k)
    (recur (assoc m k (str->int (get m k))) ks)
    m))

(defn find-biggest [records]
  (:id (->> records
            (map #(assoc % :diff (- (:max %) (:min %))))
            (apply max-key :diff))))

(defn find-gap [records]
  (:id (->> records
            (map #(assoc % :diff (- (:for %) (:against %))))
            (apply max-key :diff))))

(def weather-records (->> (str/split-lines weather)
                          (drop 2)
                          (drop-last 1)
                          (map #(str/split % #" "))
                          (map #(remove empty? %))
                          (map #(take 3 %))
                          (map #(zipmap [:id :max :min] %))
                          (map #(keys->int % [:id :max :min]))))


(defn ->football-record [data]
  (let [team (nth data 1)
        for (nth data 6)
        against (nth data 8)]
    {:id      team
     :for     for
     :against against}))

(def football-records (->> (str/split-lines football)
                           (drop 1)
                           (map #(str/split % #" "))
                           (map #(remove empty? %))
                           (remove #(= 1 (count %)))
                           (map ->football-record)
                           (map #(keys->int % [:for :against]))))

(comment
  (find-biggest weather-records)
  (find-gap football-records))