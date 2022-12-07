(ns aoc2022.07-test
  (:require [clojure.test :refer [deftest is testing]]
            [aoc2022.07 :refer [run]]))

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
    (is (= {:a {}}
           (:fs (run {:fs {}} "dir a")))))
  (testing "file"
    (is (= {:files [{:size 123
                     :name "filename.txt"}]}
           (:fs (run {:fs {}} "123 filename.txt"))))))
