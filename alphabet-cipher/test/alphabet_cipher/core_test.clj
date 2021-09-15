(ns alphabet-cipher.core-test
  (:require
   [clojure.test :refer [deftest testing is are]]
   [alphabet-cipher.core :refer [lookup encode]]))

(deftest lookup-test
  (testing "basic case"
    (are [kw msg expected] (is (= expected (lookup kw msg)))
      \a \a \a
      \a \b \b
      \a \c \c
      \a \z \z
      \b \a \b
      \b \b \c))
  (testing "example case"
    (are [kw msg expected] (is (= expected (lookup kw msg)))
      \s \m \e
      \c \e \g
      \o \e \s
      \n \t \g
      \e \m \q
      \s \e \w)))

(deftest encode-test
  (testing "example case"
    (is (= "egsgqwtahuiljgs"
           (encode "sconessconessconessco" "meetmebythetree")))))
