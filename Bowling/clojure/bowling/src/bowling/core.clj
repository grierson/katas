(ns bowling.core)

; Game - 10 Frames
; Frame - 10 lines

(defn strike? [frame]
  (= frame \X))


(defn spare? [[throw1 throw2]]
  (= throw2 \/))


(defn sum [[throw1 throw2]]
  (+ throw1 throw2))


(defn score
  ([game] (score game 0))
  ([game index]
   (let [frame (nth game index nil)]
     (cond
       (nil? frame) 0
       (strike? frame) (+ 10
                          (score game (+ index 1))
                          (score game (+ index 2)))
       (spare? frame) (+ 10 (score game (+ index 1)))
       :else (sum frame)))))

(comment
  (score (repeat 3 \X)))
