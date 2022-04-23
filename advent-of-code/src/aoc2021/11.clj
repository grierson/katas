(ns aoc2021.11)

(require 'hashp.core)

(defn recharge [grid] (update-vals grid inc))

(defn neighbours [[bx by] [x y]]
  (remove
    (fn [[x y]] (or (> x bx)
                    (> y by)))
    (remove (fn [[x y]] (or (neg? x) (neg? y)))
            [[(dec x) (dec y)] [(dec x) y] [(dec x) (inc y)]
             [x (dec y)] [x (inc y)]
             [(inc x) (dec y)] [(inc x) y] [(inc x) (inc y)]])))


(defn charge [grid neighbour]
  (if (zero? (get grid neighbour))
    grid
    (update grid neighbour inc)))

(defn flashers [grid]
  (map first (filter (fn [[_ charge]] (> charge 9)) grid)))

(defn flash [boundary grid octopus]
  (reduce charge (assoc grid octopus 0) (neighbours boundary octopus)))

(defn step [game boundary]
  (let [game (update game :grid recharge)]
    (loop [round game]
      (let [flashers (flashers (:grid round))]
        (if (empty? flashers)
          round
          (recur (-> round
                     (update :flashes + (count flashers))
                     (update :grid #(reduce (partial flash boundary) % flashers)))))))))

(defn run [game boundary times]
  (reduce
    (fn [state _] (step state boundary))
    game
    (range times)))

(def input ["4738615556"
            "6744423741"
            "2812868827"
            "8844365624"
            "4546674266"
            "4518674278"
            "7457237431"
            "4524873247"
            "3153341314"
            "3721414667"])


(defn make-grid
  [x y]
  (let [keys (for [x (range x)
                   y (range y)]
               [x y])]
    (apply hash-map (interleave keys (repeat 0)))))

(defn str->grid [s]
  (let [size (count s)
        grid (make-grid size size)]
    (reduce (fn [grid position]
              (let [value (parse-long (str (get-in s position)))]
                (assoc grid position value)))
            grid
            (for [x (range size)
                  y (range size)]
              [x y]))))

(run {:grid    (str->grid input)
      :flashes 0} [9 9] 100)
