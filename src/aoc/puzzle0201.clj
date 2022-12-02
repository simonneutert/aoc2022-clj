(ns aoc.puzzle0201)

(def input ["A Y"
            "B X"
            "C Z"])

(clojure.string/split-lines (slurp "resources/puzzle0201.txt"))

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

(play (clojure.string/split-lines (slurp "resources/puzzle0201.txt")))