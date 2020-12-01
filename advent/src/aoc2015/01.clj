(ns aoc2015.01
  (:require [aoc.common :as common]))

(def data (first (common/read "aoc2015/01.txt")))

(def count-if (comp count filter))

(- (count-if #{\(} data)
   (count-if #{\)} data))
