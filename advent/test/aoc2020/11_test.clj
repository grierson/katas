(ns aoc2020.11-test
  (:require [aoc2020.11 :refer [apply-rules generation]]
            [midje.sweet :refer :all]
            [midje.experimental :refer [for-all gen-let]]
            [clojure.test.check.generators :as gen]
            [clojure.string :as str]))

(facts "apply-rules"
  (facts "open?"
    (fact "Given no neighbours then should take the seat"
      (apply-rules [] \L) => \#)
    (fact "Given one neighbour then seat should be left empty"
      (apply-rules [\#] \L) => \L)
    (for-all [neighbours (gen/vector (gen/elements [\# \L \.]))]
      (fact "Any amount of neighbours means the seat should be empty"
        (apply-rules (conj neighbours \#) \L) => \L)))

  (facts "crowded?"
    (fact "Given 3 neighbours then should not leave the seat"
      (apply-rules [\# \# \#] \#) => \#)
    (fact "Given 4 neighbours then should leave the seat"
      (apply-rules [\# \# \# \#] \#) => \L)))

(def init (mapv vec (str/split-lines "L.LL.LL.LL\nLLLLLLL.LL\nL.L.L..L..\nLLLL.LL.LL\nL.LL.LL.LL\nL.LLLLL.LL\n..L.L.....\nLLLLLLLLLL\nL.LLLLLL.L\nL.LLLLL.LL")))
(def first-state (mapv vec (str/split-lines "#.##.##.##\n#######.##\n#.#.#..#..\n####.##.##\n#.##.##.##\n#.#####.##\n..#.#.....\n##########\n#.######.#\n#.#####.##")))
(def second-state (mapv vec (str/split-lines "#.LL.L#.##\n#LLLLLL.L#\nL.L.L..L..\n#LLL.LL.L#\n#.LL.LL.LL\n#.LLLL#.##\n..L.L.....\n#LLLLLLLL#\n#.LLLLLL.L\n#.#LLLL.##")))
(def third-state (mapv vec (str/split-lines "#.##.L#.##\n#L###LL.L#\nL.#.#..#..\n#L##.##.L#\n#.##.LL.LL\n#.###L#.##\n..#.#.....\n#L######L#\n#.LL###L.L\n#.#L###.##")))

(def gen1 (generation init))
(def gen2 (generation gen1))
(def gen3 (generation gen2))

(facts "generation steps"
  (fact "initial state -> first state"
    gen1 => first-state)
  (fact "first state -> second state"
    gen2 => second-state)
  (fact "second -> third state"
    gen3 => third-state))
