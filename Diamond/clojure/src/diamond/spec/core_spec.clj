(ns diamond.spec.core-spec
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]))

(s/def ::letter (s/with-gen #(Character/isUpperCase %) gen/char-alpha))
