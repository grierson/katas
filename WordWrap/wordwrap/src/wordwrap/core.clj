(ns wordwrap.core
  (:require [clojure.string :as str]))

(defn wrap
  [s width]
  (if (nil? s)
    ""
    (let [s (str/trim s)]
      (if (<= (count s) width)
        s
        (let [breakpoint (or (str/last-index-of s " " width) width)]
          (str (subs s 0 breakpoint)
               "\n"
               (wrap (str/triml (subs s breakpoint)) width)))))))
