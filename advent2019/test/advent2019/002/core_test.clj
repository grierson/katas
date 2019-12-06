(ns advent2019.002.core_test
  (:require [midje.sweet :refer :all]
            [advent2019.002.core :refer :all]))

(future-facts "Examples"
              (computer "1,0,0,0,99") => "2,0,0,0,99"
              (computer "2,3,0,3,99") => "2,3,0,6,99"
              (computer "2,4,4,5,99,0") => "2,4,4,5,99,9801"
              (computer "1,1,1,4,99,5,6,0,99") => "30,1,1,4,2,5,6,0,99")

(facts "Silly"
       (fact "given empty string should return empty string"
             (computer "") => "")
       (fact "given nil should return empty string"
             (computer nil) => "")
       (fact "intcode not instruction size should return intcode"
             (computer "0") => "0"
             (computer "0,1") => "0,1"
             (computer "0,1,2") => "0,1,2")
       (fact "opcode not 1,2, or 99 should return intcode"
             (computer "0,0,0,0") => "0,0,0,0"
             (computer "3,0,0,0") => "3,0,0,0"))

(fact "halt instruction returns intcode"
      (computer "99,0,0,0") => "99,0,0,0")

(fact "program->intcode - intcode->program"
      (computer "1,0,0,0,99") => "2,0,0,0,99"
      (provided
       (program->intcode "1,0,0,0,99") => '(1 0 0 0 99)
       (execute '(1 0 0 0 99)) => '(2 0 0 0 99)
       (intcode->program '(2 0 0 0 99)) => "2,0,0,0,99"))

