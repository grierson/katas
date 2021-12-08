(ns aoc2021.04-test
  (:require [clojure.test :refer :all]
            [aoc2021.04 :refer :all]))

(def first-sample-board
  [{:marked? false, :number 22} {:marked? false, :number 13} {:marked? false, :number 17} {:marked? false, :number 11} {:marked? false, :number 0}
   {:marked? false, :number 8} {:marked? false, :number 2} {:marked? false, :number 23} {:marked? false, :number 4} {:marked? false, :number 24}
   {:marked? false, :number 21} {:marked? false, :number 9} {:marked? false, :number 14} {:marked? false, :number 16} {:marked? false, :number 7}
   {:marked? false, :number 6} {:marked? false, :number 10} {:marked? false, :number 3} {:marked? false, :number 18} {:marked? false, :number 5}
   {:marked? false, :number 1} {:marked? false, :number 12} {:marked? false, :number 20} {:marked? false, :number 15} {:marked? false, :number 19}])

(deftest board->data-test
  (testing "convert data to board"
    (is (= first-sample-board
           (data->board '("22" "13" "17" "11" "0" "8" "2" "23" "4" "24" "21" "9" "14" "16" "7" "6" "10" "3" "18" "5" "1" "12" "20" "15" "19"))))))

(deftest input->data-test
  (testing "convert data to numbers"
    (is (= first-sample-board
           (first (get (data->game sample) :boards)))))
  (testing "convert data to numbers"
    (is (= '(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24, 10, 16, 13, 6, 15, 25, 12, 22, 18, 20, 8, 19, 3, 26, 1)
           (get (data->game sample) :numbers)))))

(deftest mark-board-test
  (testing "mark a number on the board"
    (let [new-board (update-in first-sample-board [14 :marked?] not)]
      (is (= new-board
             (mark-board first-sample-board 7))))))

(deftest all-marked?-test
  (testing "all marked? is true"
    (is (true? (all-marked? [{:marked? true, :number 22}
                             {:marked? true, :number 8}]))))
  (testing "none marked? is false"
    (is (false? (all-marked? [{:marked? false, :number 21}
                              {:marked? false, :number 9}]))))
  (testing "some marked? is still false"
    (is (false? (all-marked? [{:marked? false, :number 21}
                              {:marked? true, :number 9}])))))

(deftest bingo?-test
  (testing "row complete"
    (let [board [{:marked? true, :number 22} {:marked? true :number 13}
                 {:marked? false, :number 8} {:marked? false :number 2}]]
      (is (true? (bingo? board)))))

  (testing "column complete"
    (let [board [{:marked? true, :number 22} {:marked? false :number 13}
                 {:marked? true, :number 8} {:marked? false :number 2}]]
      (is (true? (bingo? board))))))


(deftest total-bingo-board-test
  (testing "total all the none marked numbers on a winning bingo board"
    (let [board [{:marked? true, :number 22} {:marked? false :number 13}
                 {:marked? true, :number 8} {:marked? false :number 2}]]
      (is (= 15
             (total board))))))

(deftest solve-test
  (let [game (data->game sample)]
    (testing "calling numbers"
      (is (= 4512
             (solve (call-numbers game)))))))