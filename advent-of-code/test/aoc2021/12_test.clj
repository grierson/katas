(ns aoc2021.12-test
  (:require [clojure.test :refer :all]
            [aoc2021.12 :refer :all]))

;    start
;    /   \
;c--A-----b--d
;    \   /
;    end

#_(deftest sample-test
    (is (= 5 (run caves))))

(deftest caves->graph-test
  (is (= {"start" #{"A" "b"}
          "end"   #{"A" "b"}
          "A"     #{"start" "end" "b" "c"}
          "b"     #{"start" "end" "A" "d"}
          "c"     #{"A"}
          "d"     #{"b"}}
         (caves->graph caves))))

(deftest find-path
  (let [simple (caves->graph simple)]
    (is (= [["start" "A" "end"]
            ["start" "b" "end"]]
           (paths simple "start" "end" #{})))))
