(ns advent2019.002.core_test
  (:require [midje.sweet :refer :all]
            [advent2019.002.core :refer :all]))

(future-facts "Examples"
              (execute "1,0,0,0,99" => "2,0,0,0,99")
              (execute "2,3,0,3,99" => "2,3,0,6,99")
              (execute "2,4,4,5,99,0" => "2,4,4,5,99,9801")
              (execute "1,1,1,4,99,5,6,0,99" => "30,1,1,4,2,5,6,0,99"))

(facts "Silly"
       (fact "empty"
             (execute "") => ""
             (execute nil) => "")
       (fact "not valid instruction"
             (execute "0") => "0"
             (execute "0,1") => "0,1"
             (execute "0,1,2") => "0,1,2")
       (fact "opcode out of range"
             (execute "3,0,0,0") => "3,0,0,0"))

(facts "Simple"
       (fact "halt instruction returns intcode"
             (execute "99,0,0,0") => "99,0,0,0")
       (fact "basic add [1 0 0 0]"
             (execute "1,0,0,0") => "2,0,0,0")
       (fact "basic add [1 1 1 0]"
             (execute "1,1,1,0") => "1,2,1,1"))

