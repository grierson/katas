(ns bowling.core-test
  (:require [clojure.test :refer :all]
            [bowling.core :refer :all]))

(deftest all-strikes-test
  (let [game "X X X X X X X X X X X X"]
    (is (= (score game) 300))))

(deftest all-nines-test
  (let [game "9- 9- 9- 9- 9- 9- 9- 9- 9- 9-"]
    (is (= (score game) 90))))

(deftest all-fives-test
  (let [game "5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5"]
    (is (= (score game) 150))))
