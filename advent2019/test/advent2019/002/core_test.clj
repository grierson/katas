(ns advent2019.002.core_test
  (:require [midje.sweet :refer :all]
            [advent2019.002.core :refer :all]))

(facts "Examples"
       (computer [1 0 0 0 99]) => [2 0 0 0 99]
       (computer [2 3 0 3 99]) => [2 3 0 6 99]
       (computer [2 4 4 5 99 0]) => [2 4 4 5 99 9801]
       (computer [1 1 1 4 99 5 6 0 99]) => [30 1 1 4 2 5 6 0 99])

(fact "1,0,0,0,9 => 2,0,0,0,99"
      (computer [1 0 0 0 99]) => [2 0 0 0 99]
      (provided
       (execute [1 0 0 0 99]) => [2 0 0 0 99]))

(fact "programe->intcode"
      (program->intcode "1,0,0,0,99") => [1 0 0 0 99]
      (program->intcode "1,1,1,1,99") => [1 1 1 1 99])

;; recursion broke test (Checkmate Mockist)
#_(future-fact "exectute"
      (execute [1 0 0 0 99] 0) => [2 0 0 0 99]
      (provided
       (instruction? [1 0 0 0 99] 0) => true
       (get-instruction [1 0 0 0 99] 0) => [1 0 0 0]
       (get-opcode 1) => +
       (update-intcode [1 0 0 0 99] 0 2) => [2 0 0 0 99]))

(facts "instruction"
       (fact "valid"
             (instruction? [1 0 0 0] 0) => true
             (instruction? [1 0 0 0 99] 0) => true)

       (fact "invalid"
             (instruction? [1 0 0] 0) => false))

(facts "get-instruction"
       (fact "[1 0 0 0 99] => [1 0 0 0]"
             (get-instruction [1 0 0 0 99] 0) => [1 0 0 0]))

(facts "get-opcode"
       (fact "(get-opcode 1) => +"
             (= (get-opcode 1) +) => true)
       (fact "(get-opcode 2) => *"
             (= (get-opcode 2) *) => true)
       (fact "(get-opcode 99) => nil"
             (get-opcode 99) => nil)
       (fact "(get-opcode 0) => nil"
             (get-opcode 0) => nil)
       (fact "(get-opcode 3) => nil"
             (get-opcode 3) => nil))
