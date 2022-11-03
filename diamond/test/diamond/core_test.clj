(ns diamond.core-test
  (:require
   [clojure.test.check.clojure-test :refer [defspec]]
   [clojure.test.check.properties :as prop]
   [clojure.spec.alpha :as s]
   [clojure.string :as string]
   [diamond.core :refer [make letter? range-letters]]))

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

(defspec string-always-returned
  (prop/for-all
   [letter (s/gen letter?)]
   (let [diamond (make letter)]
     (false? (string/blank? diamond)))))

(defspec first-and-last-row-contain-A
  (prop/for-all
   [letter (s/gen letter?)]
   (let [diamond (make letter)
         rows (string/split-lines diamond)
         first-row (string/trim (first rows))
         last-row (string/trim (last rows))]
     (= "A"
        first-row
        last-row))))

(def every-true? (partial every? true?))

(defspec each-row-has-matching-outside-space
  (prop/for-all
   [letter (s/gen letter?)]
   (let [diamond (make letter)
         rows (string/split-lines diamond)]
     (every-true?
      (map
       (fn [line]
         (= (leading-space line)
            (trailing-space line)))
       rows)))))

(defspec all-rows-in-order
  (prop/for-all
   [letter (s/gen letter?)]
   (let [diamond (make letter)
         letters (range-letters letter)
         reverse-letters (rest (reverse letters))
         expected (concat letters reverse-letters)
         rows (string/split-lines diamond)
         actual (->> rows
                     (map string/trim)
                     (map first))]
     (= expected actual))))

(defspec width-matches-height
  (prop/for-all
   [letter (s/gen letter?)]
   (let [diamond (make letter)
         rows (string/split-lines diamond)
         height (count rows)]
     (every-true?
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
         body (->> rows (rest) (drop-last 1))]
     (every-true? (map only-two body)))))

(defspec bottom-triangle
  (prop/for-all
   [letter (s/gen letter?)]
   (let [diamond (make letter)
         rows (string/split-lines diamond)
         rows (drop-while #(not (string/includes? % (str letter))) rows)
         spaces (map leading-space rows)
         space-cnt (map count spaces)]
     (every-true? (map = space-cnt (range))))))

(defspec horizontal-symmetric
  (prop/for-all
   [letter (s/gen letter?)]
   (let [diamond (make letter)
         rows (string/split-lines diamond)
         letter (str letter)
         non-letter-rows #(not (string/includes? % letter))
         top-rows (take-while non-letter-rows rows)
         bottom-rows (->> rows
                          (drop-while non-letter-rows)
                          (drop 1)
                          (reverse))]
     (= top-rows bottom-rows))))
