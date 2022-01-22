(ns aoc2020.11-test
  (:require [aoc2020.11 :refer [apply-rules generation]]
            [clojure.test :refer :all]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.generators :as gen]
            [clojure.string :as str]))

(deftest open?-test
  (testing "Given no neighbours then should take the seat"
    (is (= (apply-rules [] \L) \#)))
  (testing "Given one neighbour then seat should be left empty"
    (is (= (apply-rules [\#] \L) \L))
    (prop/for-all [neighbours (gen/vector (gen/elements [\# \L \.]))]
                  (is (= (apply-rules (conj neighbours \#) \L) \L)))))

(deftest crowded?-test
  (testing "Given 3 neighbours then should not leave the seat"
    (is (= (apply-rules [\# \# \#] \#) \#))
    (testing "Given 4 neighbours then should leave the seat"
      (is (= (apply-rules [\# \# \# \#] \#) \L)))))

(def init (mapv vec (str/split-lines "L.LL.LL.LL\nLLLLLLL.LL\nL.L.L..L..\nLLLL.LL.LL\nL.LL.LL.LL\nL.LLLLL.LL\n..L.L.....\nLLLLLLLLLL\nL.LLLLLL.L\nL.LLLLL.LL")))
(def first-state (mapv vec (str/split-lines "#.##.##.##\n#######.##\n#.#.#..#..\n####.##.##\n#.##.##.##\n#.#####.##\n..#.#.....\n##########\n#.######.#\n#.#####.##")))
(def second-state (mapv vec (str/split-lines "#.LL.L#.##\n#LLLLLL.L#\nL.L.L..L..\n#LLL.LL.L#\n#.LL.LL.LL\n#.LLLL#.##\n..L.L.....\n#LLLLLLLL#\n#.LLLLLL.L\n#.#LLLL.##")))
(def third-state (mapv vec (str/split-lines "#.##.L#.##\n#L###LL.L#\nL.#.#..#..\n#L##.##.L#\n#.##.LL.LL\n#.###L#.##\n..#.#.....\n#L######L#\n#.LL###L.L\n#.#L###.##")))

(def gen1 (generation init))
(def gen2 (generation gen1))
(def gen3 (generation gen2))

(deftest generation-steps-test
  (is (= gen1 first-state))
  (is (= gen2 second-state))
  (is (= gen3 third-state)))
