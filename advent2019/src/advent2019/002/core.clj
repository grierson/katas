(ns advent2019.002.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn program->intcode [program]
  (vec (map #(Integer/parseInt %) (str/split program #","))))

(defn intcode->program [intcode]
  (str/join #"," intcode))

(defn instruction? [intcode index]
  (>= (count (drop index intcode)) 4))

(defn get-instruction [intcode index]
  (take 4 (drop index intcode)))

(defn get-opcode [index]
  (get {1 + 2 * 99 nil} index))

(defn get-number [intcode index]
  (get intcode index))

(defn update-intcode [intcode index value]
  (assoc intcode index value))

(defn execute
  ([intcode] (execute intcode 0))
  ([intcode index]
   (if (instruction? intcode index)
     (let [[oi n1 n2 si] (get-instruction intcode index)
           opcode (get-opcode oi)
           num1 (get-number intcode n1)
           num2 (get-number intcode n2)]
       (if (some? opcode)
         (execute (update-intcode intcode si (opcode num1 num2))
                  (+ index 4))
         intcode))
     intcode)))

(defn computer [program]
  (if (nil? program)
    ""
    (-> program
        program->intcode
        execute
        intcode->program)))

(defn update-program [program]
  (let [a (->> program
               str/trim-newline
               program->intcode)
        b (update-intcode a 1 12)
        c (update-intcode b 2 2)]
    (intcode->program c)))

(-> "002.txt"
    io/resource
    slurp
    update-program
    computer
    program->intcode
    first)
