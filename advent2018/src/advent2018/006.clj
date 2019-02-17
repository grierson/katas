(ns advent2018.006
  (:require [clojure.java.io :as io]))

(def data (-> "006.txt"
              io/resource
              slurp))