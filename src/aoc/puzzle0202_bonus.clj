(ns aoc.puzzle0202-bonus)

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

(def game-rules {:lose {"A" "Z"
                        "B" "X"
                        "C" "Y"}
                 :draw {"A" "X"
                        "B" "Y"
                        "C" "Z"}
                 :win {"A" "Y"
                       "B" "Z"
                       "C" "X"}})

(def scoring-board {"A X" 4
                    "A Y" 8
                    "A Z" 3
                    "B X" 1
                    "B Y" 5
                    "B Z" 9
                    "C X" 7
                    "C Y" 2
                    "C Z" 6})

(def decision-map
  {"X" :lose
   "Y" :draw
   "Z" :win})

(defn apply-strategy [k]
  (get decision-map k))

(defn map-play
  [games]
  (loop
   [coll []
    games games]
    (let [game (first games)
          opponent (str (first game))
          decision (str (last game))
          decision-key (apply-strategy decision)
          players-pick (get-in game-rules [decision-key opponent])]
      (if game
        (recur (conj coll (str opponent " " players-pick)) (rest games))
        coll))))

(defn scoring-pattern [pattern]
  (get scoring-board pattern))

(defn play
  [games]
  (loop
   [score 0
    games games]
    (let [scoring (scoring-pattern (first games))]
      (cond
        (nil? scoring) score
        (> scoring 0) (recur (+ score scoring) (rest games))))))

(->> puzzle-input
     map-play
     play)
