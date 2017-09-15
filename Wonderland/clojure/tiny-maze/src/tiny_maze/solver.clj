(ns tiny-maze.solver)


(defn get-position
  "Get value at position in maze"
  [maze [row col]]
  (get-in maze [row col]))


(defn update-position
  "Apply :x to posistion"
  [maze [row col]]
  (assoc-in maze [row col] :x))


(defn open-position?
  "Check if posistion is open"
  [maze [row col]]
  (let [value (get-position maze [row col])]
    (some true?
     [(= value :S)
      (= value :E)
      (= value 0)])))


(defn next-move
  [maze [row col]]
  (cond
    (open-position? maze [(inc row) col]) "Down"
    (open-position? maze [row (inc col)]) "Right"
    :else "deadend"))


(defn path-finder
  [maze pos]
  (if (open-position? maze pos)
    (let [m (assoc-in maze pos (update-position maze pos))]
     (next-move m pos))
    maze))


(defn solve-maze
  [maze]
  maze)


(def maze [[:S 0 1]
           [1  0 1]
           [1  0 :E]])
(get-position maze [0 0])
(update-position maze [0 0])
(open-position? maze [0 1])
(path-finder maze [0 2])
