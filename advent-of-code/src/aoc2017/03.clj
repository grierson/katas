(ns aoc2017.03)

(defn make-spiral [number previous-end step]
  (let [start (inc previous-end)
        end (+ previous-end (* 8 step))]
    (if (< number end)
      {:step step
       :spiral [start end]}
      (make-spiral number end (inc step)))))

(defn find-spiral [number]
  (if (= number 1)
    {:step 0 :spiral [1 1]}
    (make-spiral number 1 1)))

(defn find-axis [{:keys [step spiral]}]
  (let [[start _] spiral
        gap (* 2 step)
        right (+ start (dec step))
        top (+ right gap)
        left (+ top gap)
        bottom (+ left gap)]
    [right top left bottom]))

(defn steps->axis [axis number]
  (apply min (map #(abs (- number %)) axis)))

(defn solve1 [number]
  (if (= number 1)
    0
    (let [{:keys [step] :as foo} (find-spiral number)
          axis (find-axis foo)
          ajust (steps->axis axis number)]
      (+ step ajust))))
