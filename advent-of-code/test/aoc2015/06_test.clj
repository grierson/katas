(ns aoc2015.06-test
  (:require [clojure.test :refer :all])
  (:require [aoc2015.06 :refer :all]))

(deftest get-action-test
  (is (= (get-action "turn on 0,0 through 999,999")
         :on))
  (is (= (get-action "turn off 0,0 through 999,999")
         :off))
  (is (= (get-action "toggle 0,0 through 999,999")
         :toggle)))

(deftest get-coords-test
  (is (= (get-coords "turn on 0,0 through 999,999")
         [[0 0] [999 999]])))

(deftest parse-line-test
  (is (= (parse-line "turn on 0,0 through 999,999")
         {:action :on
          :coords [[0 0] [999 999]]})))
