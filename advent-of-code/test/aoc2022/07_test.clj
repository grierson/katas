(ns aoc2022.07-test
  (:require [clojure.test :refer [deftest is testing]]
            [aoc2022.07 :refer [run total]]))

(deftest run-test
  (testing "cd /"
    (is (= []
           (:wd (run {:wd [:a]} "$ cd /")))))
  (testing "cd .."
    (is (= []
           (:wd (run {:wd [:a]}  "$ cd ..")))))
  (testing "cd <dir>"
    (is (= [:a]
           (:wd (run {:wd []}  "$ cd a"))))
    (is (= [:a :b]
           (:wd (run {:wd [:a]}  "$ cd b")))))
  (testing "dir"
    (is (= {:a {:files []}}
           (run {} "dir a"))))
  (testing "file"
    (is (= {:files [{:size 123
                     :name "filename.txt"}]}
           (run {} "123 filename.txt")))))

(deftest total-test
  (is (= {:total 1}
         (total {:files [{:size 1}]})))
  (is (= {:total 3
          :a {:total 2}}
         (total {:files [{:size 1}]
                 :a {:files [{:size 2}]}})))
  (is (= {:total 2
          :a {:total 2}}
         (total {:files []
                 :a {:files [{:size 2}]}}))))
