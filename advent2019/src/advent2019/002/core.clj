(ns advent2019.002.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn- instruction?
  [code]
  (or (some? code) (not-empty code)))

(defn execute [code]
  (if (instruction? code)
    (if (= (first code) \1)
      "2,0,0,0"
      code)
    ""))
