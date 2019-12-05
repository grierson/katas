(ns advent2019.002.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn computer [program]
  (if (nil? program)
    ""
    program))
