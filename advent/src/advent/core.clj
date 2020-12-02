(ns advent.core
  (:require [clojure.java.io :as io]))

(defn read-file [file]
  (line-seq (io/reader (io/resource file))))

(defn parse-int [s]
  (Integer/parseInt s))

(def count-if (comp count filter))