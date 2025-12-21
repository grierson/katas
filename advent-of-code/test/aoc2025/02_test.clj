(ns aoc2025.02-test
  (:require
   [aoc2025.02 :refer [duplicate find-duplicates]]
   [clojure.test :refer [are deftest testing]]))

(deftest duplicate-test
  (testing "examples"
    (are [y] (= true (duplicate y))
      "11"
      "22"
      "99"
      "1010"
      "1188511885"
      "222222"
      "446446"
      "38593859")))

(deftest find-duplicate-test
  (testing "examples"
    (are [x y] (= (find-duplicates x) y)
      [11 22] '(11 22)
      [95 115] '(99)
      [998 1012] '(1010)
      [1188511880 1188511890] '(1188511885)
      [222220 222224] '(222222)
      [1698522 1698528] '()
      [446443 446449] '(446446)
      [38593856 38593862] '(38593859))))
