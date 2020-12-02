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

(defn valid-password2? [[[low high] c password]]
  (let [temp (cons \_ password)
        x (= c (nth temp low))
        y (= c (nth temp high))]
    (not= x y)))

(count-if true? (map (comp valid-password2? parse-line) data))

(comment
  ((comp valid-password2? parse-line) "1-3 a: abcde")       ;; valid
  ((comp valid-password2? parse-line) "1-3 b: cdefg")       ;; invalid
  ((comp valid-password2? parse-line) "2-9 c: ccccccccc"))  ;; invalid

