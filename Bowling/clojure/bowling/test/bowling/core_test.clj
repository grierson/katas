(ns bowling.core-test
  (:require [clojure.test :refer :all]
            [bowling.core :refer :all]))

(deftest get-next-test
  (testing "all strikes"
    (let [line '([\X] [\X] [\X])]
      (is (= (get-next line 0) [\X \X]))))

  (testing "two spares spare"
    (let [line '([\X] [5 \/] [5 \/])]
      (is (= (get-next line 0) [5 \/]))))

  (testing "open"
    (let [line '([\X] [5 4])]
      (is (= (get-next line 0) [5 4])))))

#_(deftest all-strikes-test
    (are [line total] (= (score line) total)
                      "X X X X X X X X X X X X" 300
                      "9- 9- 9- 9- 9- 9- 9- 9- 9- 9-" 90
                      "5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5" 150))
