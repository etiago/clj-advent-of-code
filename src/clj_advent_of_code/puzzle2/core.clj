(ns clj-advent-of-code.puzzle2.core)


(defn process-puzzle
  [str]
  (map #(map (fn [spl] (Integer/valueOf spl)) (clojure.string/split % #"\t")) (clojure.string/split-lines str)))

(defn solution1
  [puzzle]
  (reduce + (map #(- (apply max %) (apply min %)) puzzle)))

(defn find-even-division
  [first-val]
  (fn [second-val]
    (let [max-val (max first-val second-val)
          min-val (min first-val second-val)]
      (zero? (mod max-val min-val)))))
      
(defn even-division
  [row]
  (let [first-val (first row)
        find-even-division-fn (find-even-division first-val)
        dividers (filter find-even-division-fn (rest row))]
    (if (seq dividers)
      (let [divider (first dividers)
            min-val (min first-val divider)
            max-val (max first-val divider)]
        (/ max-val min-val))
      (recur (rest row)))))

(defn solution2
  [puzzle]
  (reduce + (map even-division puzzle)))

(defn run
  [first-half]
  (let [puzzle (process-puzzle (slurp "resources/puzzle2.txt"))]
    (if first-half
      (solution1 puzzle)
      (solution2 puzzle))))
