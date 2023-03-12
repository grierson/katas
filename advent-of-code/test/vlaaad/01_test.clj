(ns vlaaad.01-test
  (:require [clojure.test :refer [deftest is testing]]
            [vlaaad.01 :as vlad]))

(deftest value-test
  (is (= 0 (vlad/value nil)))
  (is (= 1 (vlad/value \a)))
  (is (= 2 (vlad/value \B)))
  (is (= 2 (vlad/value \8))))

(deftest eggs-test
  (is (= 0 (vlad/eggs "c")))
  (is (= 1 (vlad/eggs "a")))
  (is (= 2 (vlad/eggs "8"))))
