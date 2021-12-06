(ns aoc2021.04-test
  (:require [clojure.test :refer :all]
            [aoc2021.04 :refer :all]))

(def first-sample-board
  [[{:marked false, :value 22} {:marked false, :value 13} {:marked false, :value 17} {:marked false, :value 11} {:marked false, :value 0}]
   [{:marked false, :value 8} {:marked false, :value 2} {:marked false, :value 23} {:marked false, :value 4} {:marked false, :value 24}]
   [{:marked false, :value 21} {:marked false, :value 9} {:marked false, :value 14} {:marked false, :value 16} {:marked false, :value 7}]
   [{:marked false, :value 6} {:marked false, :value 10} {:marked false, :value 3} {:marked false, :value 18} {:marked false, :value 5}]
   [{:marked false, :value 1} {:marked false, :value 12} {:marked false, :value 20} {:marked false, :value 15} {:marked false, :value 19}]])


(defn get-board-line [board line]
  (nth board line))

(deftest line->data-test
  (is (= (get-board-line first-sample-board 0)
         (line->data "22 13 17 11  0"))))

(deftest board->data-test
  (is (= first-sample-board
         (board->data '("22 13 17 11  0"
                         " 8  2 23  4 24"
                         "21  9 14 16  7"
                         " 6 10  3 18  5"
                         " 1 12 20 15 19")))))

(deftest input->data-test
  (is (= first-sample-board
         (first (get (input->data sample) :boards)))))

(deftest mark-line-test
  (is (= [{:marked false, :value 21}
          {:marked false, :value 9}
          {:marked false, :value 14}
          {:marked false, :value 16}
          {:marked true, :value 7}]
         (mark-line (get-board-line first-sample-board 2) 7))))

(deftest mark-board-test
  (let [line1 (get-board-line first-sample-board 0)
        line2 (get-board-line first-sample-board 1)
        line3 [{:marked false, :value 21}
               {:marked false, :value 9}
               {:marked false, :value 14}
               {:marked false, :value 16}
               {:marked true, :value 7}]
        line4 (get-board-line first-sample-board 3)
        line5 (get-board-line first-sample-board 4)]
    (is (= (vector line1 line2 line3 line4 line5)
           (mark-board first-sample-board 7)))))

(deftest all-marked?-test
  (testing "all marked"
    (is (true? (all-marked? [{:marked true, :value 21}
                             {:marked true, :value 9}
                             {:marked true, :value 14}
                             {:marked true, :value 16}
                             {:marked true, :value 7}]))))
  (testing "none marked"
    (is (false? (all-marked? [{:marked false, :value 21}
                              {:marked false, :value 9}
                              {:marked false, :value 14}
                              {:marked false, :value 16}
                              {:marked false, :value 7}])))))

(deftest bingo?-test
  (testing "line complete"
    (let [line1 [{:marked true, :value 22}
                 {:marked true, :value 13}
                 {:marked true, :value 17}
                 {:marked true, :value 11}
                 {:marked true, :value 0}]
          line2 (get-board-line first-sample-board 1)
          line3 (get-board-line first-sample-board 2)
          line4 (get-board-line first-sample-board 3)
          line5 (get-board-line first-sample-board 4)]
      (is (true? (bingo? (vector line1 line2 line3 line4 line5))))))

  (testing "column complete"
    (let [board [[{:marked true, :value 22} {:marked false :value 13}]
                 [{:marked true, :value 8} {:marked false :value 2}]
                 [{:marked true, :value 21} {:marked false :value 9}]
                 [{:marked true, :value 6} {:marked false :value 10}]
                 [{:marked true, :value 1} {:marked false :value 12}]]]
      (is (true? (bingo? board))))))
