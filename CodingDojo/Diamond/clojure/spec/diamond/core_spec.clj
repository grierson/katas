(ns diamond.core-spec
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]
            [clojure.spec.test.alpha :as st]))

(s/def ::letter (s/with-gen #(Character/isUpperCase %) gen/char-alpha))

(s/exercise ::letter)
