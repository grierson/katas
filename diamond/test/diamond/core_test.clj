(ns diamond.core-test
  (:require
   [clojure.test.check.clojure-test :refer [defspec]]
   [clojure.test.check.properties :as prop]
   [clojure.spec.alpha :as s]
   [clojure.string :as string]
   [diamond.core :refer [make letter? range-letters]]))

(defspec string-always-returned
  (prop/for-all
   [letter (s/gen letter?)]
   (let [diamond (make letter)]
     (false? (string/blank? diamond)))))

(defspec first-row-contains-A
  (prop/for-all
   [letter (s/gen letter?)]
   (let [diamond (make letter)]
     (= "A"
        (-> diamond
            (string/split-lines)
            (first)
            (string/trim))))))

(defspec last-row-contains-A
  (prop/for-all
   [letter (s/gen letter?)]
   (let [diamond (make letter)]
     (= "A"
        (-> diamond
            (string/split-lines)
            (last)
            (string/trim))))))

(defspec each-row-has-matching-outside-space
  (prop/for-all
   [letter (s/gen letter?)]
   (let [diamond (make letter)]
     (every?
      true?
      (map
       (fn [line]
         (let [line-letter (first (string/trim line))
               first-letter (string/index-of line line-letter)
               second-letter (string/last-index-of line line-letter)
               leading-space (subs line 0 first-letter)
               trailing-space (subs line (inc second-letter))]
           (= leading-space trailing-space)))
       (string/split-lines diamond))))))

(defspec all-rows-in-order
  (prop/for-all
   [letter (s/gen letter?)]
   (let [diamond (make letter)
         letters (range-letters letter)
         reverse-letters (rest (reverse letters))
         expected-letters (concat letters reverse-letters)
         actual (->> (string/split-lines diamond)
                     (map string/trim)
                     (map first))]
     (= expected-letters
        actual))))

(defspec width-matches-height
  (prop/for-all
   [letter (s/gen letter?)]
   (let [diamond (make letter)
         rows (string/split-lines diamond)
         height (count rows)]
     (every?
      true?
      (map (fn [row] (= height (count row))) rows)))))

(defn only-two [line]
  (let [contents (frequencies (string/replace line #" " ""))]
    (and (= 1 (count contents))
         (= 2 (second (first contents))))))

(defspec all-inner-rows-have-two-identical-letters
  (prop/for-all
   [letter (s/gen letter?)]
   (let [diamond (make letter)
         rows (string/split-lines diamond)
         rows (->> rows (rest) (drop-last 1))
         _ (prn rows)]
     (every?
      true?
      (map only-two rows)))))
