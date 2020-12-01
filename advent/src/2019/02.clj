(ns 2019.02
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn program->intcode [program]
  (vec (map #(Integer/parseInt %) (str/split program #","))))

(defn get-instruction [intcode address]
  (take 4 (drop address intcode)))

(defn get-opcode [opcode]
  (get {1 + 2 * 99 nil} opcode))

(defn computer
  ([intcode] (computer intcode 0))
  ([intcode address]
   (let [[opid n1i n2i si] (get-instruction intcode address)
         opcode (get-opcode opid)
         num1 (get intcode n1i)
         num2 (get intcode n2i)]
     (if (some? opcode)
       (computer (assoc intcode si (opcode num1 num2))
                 (+ address 4))
       intcode))))

(defn parse-program [program]
  (->> program
       str/trim-newline
       program->intcode))

(defn update-program [intcode]
  (-> intcode
      (update-program 1 12)
      (update-program 2 2)))

(defn problem1 []
  (-> "002.txt"
      io/resource
      slurp
      parse-program
      update-program
      computer
      first))
