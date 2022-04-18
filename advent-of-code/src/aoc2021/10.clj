(ns aoc2021.10
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(def sample
  (str/split-lines "[({(<(())[]>[[{[]{<()<>>\n[(()[<>])]({[<{<<[]>>(\n{([(<{}[<>[]}>{[]{[(<()>\n(((({<>}<{<{<>}{[]{[]{}\n[[<[([]))<([[{}[[()]]]\n[{[{({}]{}}([{[{{{}}([]\n{<[[]]>}<{[{[{[]{()[[[]\n[<(<(<(<{}))><([]([]()\n<{([([[(<>()){}]>(<<{{\n<{([{{}}[<[[[<>{}]]]>[]]"))

(def points
  {\) 3
   \] 57
   \} 1197
   \> 25137})


(def parens {\( \)
             \[ \]
             \{ \}
             \< \>})

(defn open-paren? [c]
  (contains? #{\( \[ \{ \<} c))

(defn step-corrupted? [stack c]
  (if (open-paren? c)
    (conj stack c)
    (if (= (get parens (first stack)) c)
      (rest stack)
      (reduced c))))

(defn corrupt [line]
  (let [value (reduce step-corrupted? '() line)]
    (when (char? value)
      value)))

(defn solve [lines]
  (->> lines
       (map corrupt)
       (filter identity)
       (map points)
       (apply +)))

(comment
  (def file (str/split-lines (slurp (io/resource "aoc2021/10.txt"))))
  (solve file))
