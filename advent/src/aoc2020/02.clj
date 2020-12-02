(ns aoc2020.02
  (:require [aoc.common :refer [read-file
                                count-if
                                parse-int]]
            [clojure.string :as str]))

(def data (read-file "aoc2020/02.txt"))

(defn parse-line [line]
  (let [parts (str/split line #" ")
        limits (map parse-int (str/split (first parts) #"-"))
        c (first (second parts))
        password (last parts)]
    [limits c password]))

(defn valid-password? [[[low high] c password]]
  (let [cnt (count-if #{c} password)]
    (and (>= cnt low)
         (<= cnt high))))

(count-if true? (map (comp valid-password? parse-line) data))