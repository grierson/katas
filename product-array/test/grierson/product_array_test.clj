(ns grierson.product-array-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [grierson.product-array :refer [run get-others product]]))

(deftest example-test
  (testing "example case"
    (is (= [24 12 8 6]
           (run [1 2 3 4])))))

(deftest edge-cases
  (is (= [1]
         (run [1])))
  (is (= [0 0]
         (run [0 0])))
  (is (= []
         (run []))))

(deftest negative-cases
  (is (= [-24 -12 -8 -6]
         (run [-1 -2 -3 -4]))))

(deftest get-others-test
  (testing "get all other numbers in array"
    (is (= [2 3 4]
           (get-others [1 2 3 4] 0)))
    (is (= [1 3 4]
           (get-others [1 2 3 4] 1)))
    (is (= [1 2 4]
           (get-others [1 2 3 4] 2)))
    (is (= [1 2 3]
           (get-others [1 2 3 4] 3)))))

(deftest product-test
  (is (= 24
         (product [2 3 4])))
  (is (= 12
         (product [1 3 4]))))
