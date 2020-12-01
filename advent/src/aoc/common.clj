(ns aoc.common
  (:require [clojure.java.io :as io]))

(defn read [file]
  (line-seq (io/reader (io/resource file))))
