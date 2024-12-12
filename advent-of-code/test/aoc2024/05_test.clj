(ns aoc2024.05-test
  (:require
   [aoc2024.05 :refer [parse-rules
                       parse-updates
                       parse
                       sample
                       valid?]]
   [clojure.string :as str]
   [clojure.test :refer [deftest is testing are]]))

(def rules (first (str/split sample #"\n\n")))
(def updates (second (str/split sample #"\n\n")))
(def parsed-rules (parse-rules rules))
(def parsed-updates (parse-updates updates))

(deftest parse-rules-test
  (testing "parse rules from sample"
    (is (= {47 #{13 61 29 53},
            97 #{75 13 61 29 47 53},
            75 #{13 61 29 47 53},
            61 #{13 29 53},
            29 #{13},
            53 #{13 29}}
           (parse-rules rules)))))

(deftest parse-updates-test
  (testing "parse updates from sample"
    (is (= [[75 47 61 53 29]
            [97 61 53 29 13]
            [75 29 13]
            [75 97 47 61 53]
            [61 13 29]
            [97 13 75 29 47]]
           (parse-updates updates)))))

(deftest parse-test
  (testing "parse sample"
    (is (= {:rules {47 #{13 61 29 53},
                    97 #{75 13 61 29 47 53},
                    75 #{13 61 29 47 53},
                    61 #{13 29 53},
                    29 #{13},
                    53 #{13 29}},
            :updates [[75 47 61 53 29]
                      [97 61 53 29 13]
                      [75 29 13]
                      [75 97 47 61 53]
                      [61 13 29]
                      [97 13 75 29 47]]}
           (parse sample)))))

(deftest valid?-test
  (testing "check valid for each update"
    (are [expected input] (= expected (valid? parsed-rules #{} input))
      true [75 47 61 53 29]
      true [97 61 53 29 13]
      true [75 29 13]
      false [75 97 47 61 53]
      false [61 13 29]
      false [97 13 75 29 47])))
