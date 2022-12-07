(ns aoc2022.07-test
  (:require [clojure.test :refer [deftest is testing]]
            [aoc2022.07 :refer [run folder-size total]]))

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

(deftest folder-size-test
  (testing "empty files"
    (is (= 0
           (folder-size {:files []}))))
  (testing "total files"
    (is (= 3
           (folder-size {:files [{:size 1} {:size 2}]}))))
  (testing "include sub folder in total files"
    (is (= 6
           (folder-size {:files [{:size 1} {:size 2}]
                         :a {:files [{:size 3}]}})))
    (is (= 10
           (folder-size {:files [{:size 1} {:size 2}]
                         :a {:files [{:size 3}]
                             :b {:files [{:size 4}]}}})))
    (is (= 10
           (folder-size {:files [{:size 1} {:size 2}]
                         :a {:files [{:size 3}]}
                         :b {:files [{:size 4}]}})))))

(deftest total-test
  (is (= {:total 1}
         (total {:files [{:size 1}]})))
  (is (= {:total 3
          :a {:total 2}}
         (total {:files [{:size 1}]
                 :a {:files [{:size 2}]}}))))
