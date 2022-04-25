(ns aoc2021.12-test
  (:require [clojure.test :refer :all]
            [aoc2021.12 :refer :all]))

;    start
;    /   \
;c--A-----b--d
;    \   /
;    end

(def example ["start-A"
              "start-b"
              "A-c"
              "A-b"
              "b-d"
              "A-end"
              "b-end"])

(def larger-example ["dc-end"
                     "HN-start"
                     "start-kj"
                     "dc-start"
                     "dc-HN"
                     "LN-dc"
                     "HN-end"
                     "kj-sa"
                     "kj-HN"
                     "kj-dc"])

(def even-larger-example ["fs-end"
                          "he-DX"
                          "fs-he"
                          "start-DX"
                          "pj-DX"
                          "end-zg"
                          "zg-sl"
                          "zg-pj"
                          "pj-he"
                          "RW-he"
                          "fs-DX"
                          "pj-RW"
                          "zg-RW"
                          "start-pj"
                          "he-WI"
                          "zg-he"
                          "pj-fs"
                          "start-RW"])

(def input ["zs-WO"
            "zs-QJ"
            "WO-zt"
            "zs-DP"
            "WO-end"
            "gv-zt"
            "iu-SK"
            "HW-zs"
            "iu-WO"
            "gv-WO"
            "gv-start"
            "gv-DP"
            "start-WO"
            "HW-zt"
            "iu-HW"
            "gv-HW"
            "zs-SK"
            "HW-end"
            "zs-end"
            "DP-by"
            "DP-iu"
            "zt-start"])

(deftest caves->graph-test
  (is (= {"start" #{"A" "b"}
          "end"   #{"A" "b"}
          "A"     #{"start" "end" "b" "c"}
          "b"     #{"start" "end" "A" "d"}
          "c"     #{"A"}
          "d"     #{"b"}}
         (caves->graph example))))

(deftest ^:kaocha/pending paths-test
  (let [caves (caves->graph example)]
    (is (= [["start" "A" "b" "A" "c" "A" "end"]
            ["start" "A" "b" "A" "end"]
            ["start" "A" "b" "end"]
            ["start" "A" "c" "A" "b" "A" "end"]
            ["start" "A" "c" "A" "b" "end"]
            ["start" "A" "c" "A" "end"]
            ["start" "A" "end"]
            ["start" "b" "A" "c" "A" "end"]
            ["start" "b" "A" "end"]
            ["start" "b" "end"]]
           (paths caves #{} "start")))))

(deftest valid-paths-test
  (is (= 10
         (valid-paths (caves->graph example) #{} "start")))
  (is (= 19
         (valid-paths (caves->graph larger-example) #{} "start")))
  (is (= 226
         (valid-paths (caves->graph even-larger-example) #{} "start"))))

(comment
  (valid-paths (caves->graph input) #{} "start"))
