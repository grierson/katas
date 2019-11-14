(ns lcdnumber.core-test
  (:require [midje.sweet :refer [future-fact unfinished fact provided =>]]
            [lcdnumber.core :refer [account-number digit digit-parcels]]))

(fact
  (account-number ["    _  _     _  _  _  _  _ "
                   "  | _| _||_||_ |_   ||_||_|"
                   "  ||_  _|  | _||_|  ||_| _|"])
  => "123456789")

(fact "an account number is constructed from character parcels"
  (account-number ..parcel..) => "01"
  (provided
    (digit-parcels ..parcel..) => [..0-parcel.. ..1-parcel..]
    (digit ..0-parcel..) => 0
    (digit ..1-parcel..) => 1))
