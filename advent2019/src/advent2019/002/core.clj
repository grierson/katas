(ns advent2019.002.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn program->intcode [program]
  program)

(defn intcode->program [intcode]
  intcode)

(defn execute [intcode]
  intcode)

(defn computer [program]
  (if (nil? program)
    ""
    (-> program
        program->intcode
        execute
        intcode->program)))
