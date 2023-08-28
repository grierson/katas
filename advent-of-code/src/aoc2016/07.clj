(ns aoc2016.07
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn parse-ip [ip]
  {:supernet (map second (re-seq #"(?:^|\[|\])(.*?)(?:$|\[|\])" ip))
   :hypernet (re-seq #"(?<=\[)[^\[\]]+(?=\])" ip)})

(defn abba? [segment]
  (some?
   (some true?
         (map (fn [[a b c d]]
                (and
                 (not= a b)
                 (= a d)
                 (= b c)))
              (partition 4 1 segment)))))

(defn tls? [{:keys [supernet hypernet]}]
  (and
   (some? (some true? (map abba? supernet)))
   (every? false? (map abba? hypernet))))

(defn solve [ips]
  (->> ips
       (map parse-ip)
       (map tls?)
       (filter true?)
       (count)))

(comment
  (def data (str/split-lines (slurp (io/resource "aoc2016/07.txt"))))
  (solve data))
