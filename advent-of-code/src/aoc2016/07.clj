(ns aoc2016.07
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn parse-ip [ip]
  {:address (map second (re-seq #"(?:^|\[|\])(.*?)(?:$|\[|\])" ip))
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

(defn tls? [ip]
  (let [{:keys [address hypernet]} (parse-ip ip)]
    (and
     (some? (some true? (map abba? address)))
     (every? false? (map abba? hypernet)))))

(defn solve [ips]
  (count
   (filter true?
           (map tls? ips))))

(comment
  (def data (str/split-lines (slurp (io/resource "aoc2016/07.txt"))))
  (solve data))
