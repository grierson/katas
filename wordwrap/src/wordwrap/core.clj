(ns wordwrap.core
  (:require [clojure.string :as str]))

(def line "bc")

(defn wrap [s width]
  (if (or (empty? s)
          (<= (max 0 width) 0))
    ""
    (let [line (str/trim s)]
      (if (<= (count line) width)
        line
        (let [last-space (str/last-index-of line " " width)
              breakpoint (if last-space last-space width)]
          (str (subs line 0 breakpoint)
               "\n"
               (wrap (subs (str/trim line) breakpoint) width)))))))
