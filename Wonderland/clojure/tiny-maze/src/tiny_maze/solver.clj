(ns tiny-maze.solver)


(defn update-position!
  "Apply :x to position"
  [maze position]
  (assoc-in maze position :x))


(defn get-position
  "Get value at position in maze"
  [maze position]
  (get-in maze position))


(defn open?
  "Check if position is open in maze"
  [maze position]
  (let [pos (get-position maze position)]
    (some #(= pos %) '(:S :E 0))))


(defn surronding-spaces
  "Return all surrounding open spaces"
  [maze [row col]]
  (let [north [(dec row) col]
        east [row (inc col)]
        south [(inc row) col]
        west [row (dec col)]]
    (->> [south east north west]
         (filter (comp some? (partial open? maze))))))


(defn next-move
  "Return next move"
  [maze position]
  (first (surronding-spaces maze position)))


(defn move
  "Update maze"
  [maze position]
  (let [new-maze (update-position! maze position)
        move-to (next-move new-maze position)]
    (cond
      (= :E (get-position maze position)) new-maze
      (nil? move-to) new-maze
      :else (move new-maze (next-move new-maze position)))))


(defn solve-maze
  [maze]
  (move maze [0 0]))
