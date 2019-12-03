(ns advent2019.002
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(comment
  (def data (-> "002.txt"
                io/resource
                slurp
                str/split-lines)))

(def op {1 +
         2 *
         99 nil})

(defn str->nums [s]
  (->> (str/split s #",")
       (map #(Integer/parseInt %))))

(defn foo [numbers]
  (let [[h f s & t] (str->nums numbers)]
    ((get op h) f s)))
