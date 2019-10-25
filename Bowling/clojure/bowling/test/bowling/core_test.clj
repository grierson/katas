(ns bowling.core-test
  (:require [clojure.test :refer :all]
            [bowling.core :refer :all]))

(deftest all-strikes-test
  (let [game (repeat 12 \X)]
    (is (= (score game) 300))))

(deftest all-nines-test
  (let [game (repeat 10 [9 \-])]
    (is (= (score game) 90))))

(deftest all-fives-test
  (let [game (concat (repeat 9 [5 \/]) [[5 5]])]
    (is (= (score game) 150))))
