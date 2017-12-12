(ns clj-advent-of-code.puzzle3.core)

(defn find-overfitting-square
  [number]
  (+ 2 (last (take-while #(< (* % %) number) (filter odd? (range))))))

(defn solution1
  [number]
  (let [side-size (find-overfitting-square number)
        half-side-size (/ side-size 2)
        max-val (* side-size side-size)
        middle (- max-val half-side-size)
        distance-to-middle (Math/abs (float (- number middle)))]
    (+ distance-to-middle (dec half-side-size))))

(defn transform-xy
  [key transform-fn]
  (fn
    [xy]
    (update xy key transform-fn)))

(def directions [(transform-xy :y dec) (transform-xy :x dec) (transform-xy :y inc) (transform-xy :x inc)])
(def corners [(comp (nth directions 0) (nth directions 3))
              (comp (nth directions 3) (nth directions 2))
              (comp (nth directions 2) (nth directions 1))
              (comp (nth directions 1) (nth directions 0))])
(def neighbours (concat directions corners))

(defn next-move
  [num min max side]
  (if (< num (- (+ min side) 2))
    (nth directions 0)
    (if (< num (- (+ min (* 2 side)) 3))
      (nth directions 1)
      (if (< num (- (+ min (* 3 side)) 4))
        (nth directions 2)
        (nth directions 3)))))

(defn get-neighbour-coords
  [xyval]
  (map #(% xyval) neighbours))

(defn filter-neighbour-coords
  [coords world]
  (filter #(and (contains? world (% :x))
                (contains? (world (% :x)) (% :y))) coords))

(defn calculate-next-position
  [xy world]
  (let [all-neighbour-coords (get-neighbour-coords xy)
        existing-neighbour-coords (filter-neighbour-coords all-neighbour-coords world)]
    (reduce + (map #((world (% :x)) (% :y)) existing-neighbour-coords))))

(defn put-coordinate-in-map
  [world xyval]
  (merge-with merge world xyval))

(defn solution2-fn
  [number]
  (fn
    ([]
     ((solution2-fn number) {0 {0 1}} {:x 1 :y 0} 2))
    ([world xyval i]
     (let [val (calculate-next-position xyval world)
           new-world (put-coordinate-in-map world {(xyval :x) {(xyval :y) val}})
           side (find-overfitting-square i)
           max-val (* side side)
           min-val (- max-val (* (- side 1) 3) (- side 2))
           new-xyval ((next-move i min-val max-val side) xyval)]
       (if (< val number)
         (recur new-world new-xyval (inc i))
         val)))))

(defn run
  []
  (solution1 347991)
  ((solution2-fn 347991)))
        
