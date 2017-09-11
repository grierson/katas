(ns alphabet-cipher.coder)


;; Domain

(def alphabet (\a \b \c \d \e \f \g \h \i \j \k \l \m \n \o \p \q \r \s \t \u \v \w \x \y \z))


(defn pos [x]
  (- (int x) 97))


(defn offset [letter]
  (mod (pos letter) (count alphabet)))

(offset \a)

;; (letter from keyword) + (letter from message) = (encoded letter)

;; Logic
;; (offset "s" "m")
;; e


(defn encode [keyword message]
  "egsgqwtahuiljgs")


(defn decode [keyword message]
  "meetmeontuesdayeveningatseven")


(defn decipher [cipher message]
  "vigilance")

