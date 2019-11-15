(ns diamond.core-test
  (:require [midje.sweet :refer :all]
            [midje.experimental :refer [for-all]]
            [clojure.string :as str]
            [clojure.spec.alpha :as s]
            [diamond.core :as sut :refer :all]))

;; Example
(facts "Example diamond tests - Start to get out of hand"
       (make \A) => "A"
       (make \B) => " A \nB B\n A "
       (make \C) => "  A  \n B B \nC   C\n B B \n  A  ")

;; Helpers
(defn spaces [line]
  (take-while #(= % \space) line))

(defn count-spaces
  [rows letter]
  (->> rows
       (filter #(not (str/includes? % "A")))
       (drop-while #(not (str/includes? % (str letter))))
       (map (comp count spaces))
       (map-indexed vector)))

(defn is-two-identical-letters?
  [line]
  (let [has-identical-letters (= 1 (->> line distinct (filter #(not= \space %)) count))
        has-two-letters (= 2 (count (filter #(not= \space %) line)))]
    (and has-identical-letters has-two-letters)))

(defn top-rows
  [rows letter]
  (take-while #(not (str/includes? % (str letter))) rows))

(defn bottom-rows
  [rows letter]
  (->> rows
       (drop-while #(not (str/includes? % (str letter))))
       (drop 1)
       (reverse)))

;; Property
(for-all [letter (s/gen ::sut/letter)]
  (fact "inner rows have identical letters"
        (let [diamond (make letter)
              rows (str/split-lines diamond)]
          (->> rows
               (filter #(not (str/includes? % "A")))
               (map str/trim)
               (map is-two-identical-letters?)) => (has every? true?))))

(for-all [letter (s/gen ::sut/letter)]
  (fact "first row contains a"
        (-> (make letter) str/split-lines first str/trim) => "A"))

(for-all [letter (s/gen ::sut/letter)]
  (fact "last-row-contains-a"
        (-> (make letter) str/split-lines last str/trim) => "A"))

(for-all [letter (s/gen ::sut/letter)]
  (fact "symmetric contour"
        (let [diamond (make letter)
              rows (str/split-lines diamond)]
          (map #(= (spaces %) (spaces (reverse %))) rows) => (has every? true?))))

(for-all [letter (s/gen ::sut/letter)]
  (fact "alphabetical order"
        (let [diamond (make letter)
              letters (alphabet \A letter)
              expected (concat letters (-> letters reverse rest))
              rows (->> diamond
                        str/split-lines
                        (map str/trim)
                        (map first))]
          rows => expected)))

(for-all [letter (s/gen ::sut/letter)]
  (fact "equal height width"
        (let [diamond (make letter)
              rows (str/split-lines diamond)
              expected (count rows)]
          (map #(= (count %) expected) rows) => (has every? true?))))


(for-all [letter (s/gen ::sut/letter)]
  (fact "lower left triangle"
        (let [diamond (make letter)]
          (if (= \A letter)
            true
            (let [rows (str/split-lines diamond)
                  spacing (count-spaces rows letter)]
              (map (fn [[line spaces]] (= line spaces)) spacing) => (has every? true?))))))

(for-all [letter (s/gen ::sut/letter)]
  (fact "diamond symmetric horizontal"
        (let [diamond (make letter)
              rows (str/split-lines diamond)]
          (top-rows rows letter) => (bottom-rows rows letter))))