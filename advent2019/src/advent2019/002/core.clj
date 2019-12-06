(ns advent2019.002.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn program->intcode [program]
  (vec (map #(Integer/parseInt %) (str/split program #","))))

(defn instruction? [intcode index]
  (>= (count (drop index intcode)) 4))

(defn get-instruction [intcode index]
  (take 4 (drop index intcode)))

(defn get-opcode [index]
  (get {1 + 2 * 99 nil} index))

(defn execute
  ([intcode] (execute intcode 0))
  ([intcode address]
   (if (instruction? intcode address)
     (let [[opid n1i n2i si] (get-instruction intcode address)
           opcode (get-opcode opid)
           num1 (get intcode n1i)
           num2 (get intcode n2i)]
       (if (some? opcode)
         (execute (assoc intcode si (opcode num1 num2))
                  (+ address 4))
         intcode))
     intcode)))

(defn computer [program]
  (if (some? program)
    (execute program)
    ""))

(defn parse-program [program]
  (->> program
       str/trim-newline
       program->intcode))

(defn update-program [intcode]
  (-> intcode
      (update-intcode 1 12)
      (update-intcode 2 2)))

(defn problem1 []
  (-> "002.txt"
      io/resource
      slurp
      parse-program
      update-program
      computer
      first))
