(ns aoc2016.04-test
  (:require [midje.sweet :refer :all]
            [aoc2016.04 :refer [get-name
                                order-name]]))

(fact "order-name"
  (order-name (get-name "aaaaa-bbb-z-y-x-123[abxyz]")) => [\a \b \x \y \z]
  (order-name (get-name "a-b-c-d-e-f-g-h-987[abcde]")) => [\a \b \c \d \e]
  (order-name (get-name "not-a-real-room-404[oarel]")) => [\o \a \r \e \l]
  (order-name (get-name "totally-real-room-200[decoy]")) => [\l \o \a \r \t])
