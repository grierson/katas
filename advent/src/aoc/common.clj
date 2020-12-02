(ns aoc.common
  (:require [clojure.java.io :as io]))

(defn read-file [file]
  (line-seq (io/reader (io/resource file))))

(defn parse-int [s] (Long/parseLong s))

(def count-if (comp count filter))
