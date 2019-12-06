(ns advent2019.002.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn program->intcode [program]
  (map #(Integer/parseInt %) (str/split program #",")))

(defn intcode->program [intcode]
  (str/join #"," intcode))

(defn instruction? [intcode]
  (>= (count intcode) 4))

(defn get-instruction [intcode]
  (take 4 intcode))

(defn get-opcode [index]
  (get {1 + 2 *} index))

(defn get-number [intcode index]
  (get intcode index))

(defn update-intcode [intcode index value]
  (assoc intcode index value))

(defn execute [intcode]
  (if (instruction? intcode)
    (let [[oi n1 n2 si] (get-instruction intcode)
          opcode (get-opcode oi)
          num1 (get-number intcode n1)
          num2 (get-number intcode n2)]
      (if (some? opcode)
        (update-intcode intcode si (opcode num1 num2))
        intcode))
    intcode))

(defn computer [program]
  (if (nil? program)
    ""
    (-> program
        program->intcode
        execute
        intcode->program)))
