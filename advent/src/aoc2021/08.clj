(ns aoc2021.08
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(def sample (str/split-lines "be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe\nedbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc\nfgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg\nfbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb\naecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea\nfgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb\ndbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe\nbdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef\negadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb\ngcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce"))
(def file (str/split-lines (slurp (io/resource "aoc2021/08.txt"))))

(defn line->outputs [line]
  (let [[_ output] (str/split line #"\|")]
    (str/split (str/trim output) #" ")))

(defn is1478? [output]
  ({2 1
    4 4
    3 7
    7 8} (count output)))

(defn solve [data]
  (let [outputs (map line->outputs data)
        all-outputs (flatten outputs)]
    (count (filter is1478? all-outputs))))

(comment
  (solve sample)
  (solve file))
