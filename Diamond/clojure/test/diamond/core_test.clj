(ns diamond.core-test
  (:require [clojure.test :refer [deftest testing is]]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.string :as str]))

;; Helpers
(defn spaces [line]
  (take-while #(= % \space) line))

(defn alphabet [start end]
  (map char (range (int start) (inc (int end)))))

;; Property
(defn row-not-empty
  [diamond]
  (not (str/blank? diamond)))

(defn first-row-contains-a
  [diamond]
  (= (-> diamond str/split-lines first str/trim) "A"))

(defn last-row-contains-a
  [diamond]
  (= (-> diamond str/split-lines last str/trim) "A"))

(defn symmetric-contour
  [diamond]
  (let [rows (str/split-lines diamond)]
    (every? true? (map #(= (spaces %) (spaces (reverse %))) rows))))

(defn alphabetical-order
  [letter diamond]
  (let [letters (alphabet \A letter)
        expected (concat letters (-> letters reverse rest))
        rows (->> diamond
                  str/split-lines
                  (map str/trim)
                  (map first))]
    (= rows expected)))

(defn equal-height-width
  [diamond]
  (let [rows (str/split-lines diamond)
        expected (count rows)]
    (every? true? (map #(= (count %) expected) rows))))

;; Inner Row Spacing
(defn isTwoIdenticalLetters
  [line]
  (let [hasIdenticalLetters (= 1 (-> line distinct count))
        hasTwoLetters (= 2 (count line))]
    (and hasIdenticalLetters hasTwoLetters)))

(defn inner-rows-have-identical-letters
  [diamond]
  (let [rows (-> diamond str/split-lines)]
    (->> rows
         (filter #(not (str/includes? % "A")))
         (map str/trim)
         (map isTwoIdenticalLetters))))

;; Lower left spacing on triangle
(defn count-spaces
  [rows letter]
  (->> rows
       (filter #(not (str/includes? % "A")))
       (drop-while #(not (str/includes? % (str letter))))
       (map (comp count spaces))
       (map-indexed vector)))

(defn lower-left-triangle
  [diamond letter]
  (if (= \A letter)
    true
    (let [rows (str/split-lines diamond)
          spacing (count-spaces rows letter)]
      (every? true? (map (fn [[line spaces]] (= line spaces)) spacing)))))

;; Diamond is Symmetric
(defn top-rows
  [rows letter]
  (take-while #(not (str/includes? % (str letter))) rows))

(defn bottom-rows
  [rows letter]
  (->> rows
       (drop-while #(not (str/includes? % (str letter))))
       (drop 1)
       (reverse)))

(defn diamond-symmetric-horizontal
  [diamond letter]
  (let [rows (str/split-lines diamond)]
    (= (top-rows rows letter) (bottom-rows rows letter))))
