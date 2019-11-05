(ns bowling.core-test
  (:require [clojure.test :refer :all]
            [bowling.core :refer :all]))

(deftest all-strikes-test
  (are [line total] (= (score line) total)
                    "X X X X X X X X X X X X" 300
                    "9- 9- 9- 9- 9- 9- 9- 9- 9- 9-" 90
                    "5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5" 150))
