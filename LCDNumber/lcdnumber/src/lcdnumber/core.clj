(ns lcdnumber.core)

(def one ['(\space \space \space) '(\space \space \|) '(\space \space \|)])
(def two ['(\space \_ \space) '(\space \_ \|) '(\| \_ \space)])
(def three ['(\space \_ \space) '(\space \_ \|) '(\space \_ \|)])
(def four ['(\space \space \space) '(\| \_ \|) '(\space \space \|)])
(def five ['(\space \_ \space) '(\| \_ \space) '(\space \_ \|)])
(def six ['(\space \_ \space) '(\| \_ \space) '(\| \_ \|)])
(def seven ['(\space \_ \space) '(\space \space \|) '(\space \space \|)])
(def eight ['(\space \_ \space) '(\| \_ \|) '(\| \_ \|)])
(def nine ['(\space \_ \space) '(\| \_ \|) '(\space \_ \|)])

(defn digit [number]
  (condp = number
    one 1
    two 2
    three 3
    four 4
    five 5
    six 6
    seven 7
    eight 8
    nine 9))

(defn digit-parcels [lcd]
  (let [top (partition 3 (nth lcd 0))
        middle (partition 3 (nth lcd 1))
        bottom (partition 3 (nth lcd 2))]
    (map vector top middle bottom)))

(defn account-number [lcd]
  (apply str (map digit (digit-parcels lcd))))
