(ns wordwrap.core
  (:require [clojure.string :as str]))

(defn wrap
  [s width]
  (cond
    (nil? s) ""
    (<= (count s) width) s
    :else (let [breakpoint (or (str/last-index-of s " " width) width)]
            (str (subs s 0 breakpoint)
                 "\n"
                 (wrap (str/triml (subs s breakpoint)) width)))))

