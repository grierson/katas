(ns aoc2017.04-test
  (:require
   [aoc2017.04 :refer [duplicates? anagrams?]]
   [clojure.test :refer [deftest is]]))

(deftest no-duplicate-words-test
  (is (true? (duplicates? "aa bb cc dd ee")))
  (is (false? (duplicates? "aa bb cc dd aa")))
  (is (true? (duplicates? "aa bb cc dd aaa"))))

(deftest no-anagrams-test
  (is (true? (anagrams? "abcde fghij")))
  (is (false? (anagrams? "abcde xyz ecdab")))
  (is (true? (anagrams? "a ab abc abd abf abj")))
  (is (true? (anagrams? "iii oii ooii oooi oooo")))
  (is (false? (anagrams? "oiii ioii iioi iiio"))))
