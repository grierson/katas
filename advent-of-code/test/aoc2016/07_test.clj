(ns aoc2016.07-test
  (:require
   [aoc2016.07 :refer [tls?
                       parse-ip
                       abba?]]
   [clojure.test :refer [deftest is]]))

(deftest parse-ip-test
  (is (= {:supernet ["abba" "qrst"]
          :hypernet ["mnop"]}
         (parse-ip "abba[mnop]qrst")))

  (is (= {:supernet ["abcd" "xyyx"]
          :hypernet ["bddb"]}
         (parse-ip "abcd[bddb]xyyx")))

  (is (= {:supernet ["aaaa" "tyui"]
          :hypernet ["qwer"]}
         (parse-ip "aaaa[qwer]tyui")))

  (is (= {:supernet ["ioxxoj" "zxcvbn"]
          :hypernet ["asdfgh"]}
         (parse-ip "ioxxoj[asdfgh]zxcvbn"))))

(deftest abba?-test
  (is (false? (abba? "mnop")))
  (is (false? (abba? "aaaa")))
  (is (true? (abba? "abba")))
  (is (true? (abba? "ioxxoj"))))

(deftest tls?-test
  (is (true? (tls? "abba[mnop]qrst")))
  (is (false? (tls? "abcd[bddb]xyyx")))
  (is (false? (tls? "aaaa[qwer]tyui")))
  (is (true? (tls? "ioxxoj[asdfgh]zxcvbn"))))
