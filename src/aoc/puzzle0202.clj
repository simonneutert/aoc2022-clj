(ns aoc.puzzle0202)

;; could be solved in a cycled picker
;; Rock -> Paper -> Scissor -> Rock -> Paper -> ...
;; Stone beaten by successor in chain
;; Paper beaten by successor in chain
;; Scissor beaten by successor in chain

;; Rock beats successor's successor
;; Paper beats successor's successor
;; Scissor beats successor's successor

(def input ["A Y"
            "B X"
            "C Z"])

(def puzzle-input
  (clojure.string/split-lines (slurp "resources/puzzle0201.txt")))

(defn map-play
  [games]
  (loop
   [coll []
    games games]
    (case (first games)
      nil coll
      "A X" (recur (conj coll "A Z") (rest games))
      "A Y" (recur (conj coll "A X") (rest games))
      "A Z" (recur (conj coll "A Y") (rest games))
      "B X" (recur (conj coll "B X") (rest games))
      "B Y" (recur (conj coll "B Y") (rest games))
      "B Z" (recur (conj coll "B Z") (rest games))
      "C X" (recur (conj coll "C Y") (rest games))
      "C Y" (recur (conj coll "C Z") (rest games))
      "C Z" (recur (conj coll "C X") (rest games)))))

(defn play
  [games]
  (loop
   [score 0
    games games]
    (case (first games)
      nil score
      "A X" (recur (+ score 4) (rest games))
      "A Y" (recur (+ score 8) (rest games))
      "A Z" (recur (+ score 3) (rest games))
      "B X" (recur (+ score 1) (rest games))
      "B Y" (recur (+ score 5) (rest games))
      "B Z" (recur (+ score 9) (rest games))
      "C X" (recur (+ score 7) (rest games))
      "C Y" (recur (+ score 2) (rest games))
      "C Z" (recur (+ score 6) (rest games)))))

(defn solve [puzzle-input]
  (->> puzzle-input
       map-play
       play))

(solve puzzle-input)
