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
  (is (= first-sample-board
         (data->board '("22" "13" "17" "11" "0" "8" "2" "23" "4" "24" "21" "9" "14" "16" "7" "6" "10" "3" "18" "5" "1" "12" "20" "15" "19")))))

(deftest input->data-test
  (is (= first-sample-board
         (first (get (input->data sample) :boards)))))

(deftest mark-board-test
  (let [new-board (update-in first-sample-board [14 :marked?] not)]
    (is (= new-board
           (mark-board first-sample-board 7)))))

(deftest all-marked?-test
  (testing "all marked"
    (is (true? (all-marked? [{:marked? true, :number 22}
                             {:marked? true, :number 8}]))))
  (testing "none marked?"
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
  (let [board [{:marked? true, :number 22} {:marked? false :number 13}
               {:marked? true, :number 8} {:marked? false :number 2}]]
    (is (= 15
           (total board)))))
