(ns clj-advent-of-code.core
  (:require
   [clj-advent-of-code.puzzle1.core :as puzzle-1]
   [clj-advent-of-code.puzzle2.core :as puzzle-2]
   [clj-advent-of-code.puzzle3.core :as puzzle-3]
;;   [clj-advent-of-code.puzzle2.core :as puzzle-2]
  (:gen-class)))

(defn -main
  [& args]
  ;; Puzzle 1 - First half
  ;; (prn (puzzle-1/run true))

  ;; Puzzle 1 - Second half
  ;; (prn (puzzle-1/run true))

  ;; Puzzle 2 - First half
  ;; (prn (puzzle-2/run true))

  ;; Puzzle 2 - Second half
  ;; (prn (puzzle-2/run false)))
  (prn (puzzle-3/run)))
