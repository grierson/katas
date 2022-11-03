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

(defn find-line-letter [line]
  (first (string/trim line)))

(defn leading-space
  ([line]
   (leading-space line (find-line-letter line)))
  ([line letter]
   (subs line 0 (string/index-of line letter))))

(defn trailing-space
  ([line]
   (leading-space line (find-line-letter line)))
  ([line letter]
   (subs line (inc (string/last-index-of line letter)))))

(defspec each-row-has-matching-outside-space
  (prop/for-all
   [letter (s/gen letter?)]
   (let [diamond (make letter)]
     (every?
      true?
      (map
       (fn [line]
         (let [leading-space (leading-space line)
               trailing-space (trailing-space line)]
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

(defspec bottom-triangle
  (prop/for-all
   [letter (s/gen letter?)]
   (let [diamond (make letter)
         rows (string/split-lines diamond)
         rows (drop-while #(not (string/includes? % (str letter))) rows)
         spaces (map leading-space rows)
         space-cnt (map count spaces)]
     (every?
      true?
      (map = space-cnt (range))))))

(defspec horizontal-symmetric
  (prop/for-all
   [letter (s/gen letter?)]
   (let [diamond (make letter)
         rows (string/split-lines diamond)
         top-rows (take-while #(not (string/includes? % (str letter))) rows)
         bottom-rows (->>
                      rows
                      (drop-while #(not (string/includes? % (str letter))))
                      (drop 1)
                      (reverse))]
     (= top-rows bottom-rows))))
