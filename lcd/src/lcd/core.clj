(ns lcd.core)

(defn digit [parcel])


(defn digit-parcels [lcd])


(defn account-number [lcd]
  (apply str (map digit (digit-parcels lcd))))