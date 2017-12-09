(ns clj-advent-of-code.puzzle3.core)

(defn find-overfitting-square
  [number]
  (inc (last (take-while #(< (* % %) number) (filter odd? (range))))))

(defn solution1
  [number]
  (let [side-size (find-overfitting-square number)
        half-side-size (/ side-size 2)
        max-val (* side-size side-size)
        middle (- max-val half-side-size)
        distance-to-middle (Math/abs (- number middle))]
    (+ distance-to-middle (dec half-side-size))))

;; (defn sum-revealed-adjacent
;;   [lookup x y]
;;   )
;; (defn first-larger-than
;;   [number]
;;   
;;   )
;; 
;; (defn sequence-pos-to-spiral-coordinate
;;   [sequence n-limit current-n current-x current-y]
;;   (if (< current-n n-limit)
;;     
;;     (into [] '(current-x current-y))))

;; R U L D
(defn step-range
  []
  (map (fn [x]
         (nth [#(cons (inc %1) [%2])
              #(cons %1 [(inc %2)])
              #(cons (dec %1) [%2])
              #(cons %1 [(dec %2)])] (mod x 4)) (range))))

;;#(cons (+ 1 %1) [(+ 2 %2)])

;;(apply #(cons (+ 1 %1) [(+ 2 %2)]) [1 2])
       
(defn solution2
  [number]
  ;;(first-larger-than number)
  ;;(prn (take 8 (step-range)))
  )

(defn run
  []
    (solution1 347991))
  ;;(solution2 347991))
        
