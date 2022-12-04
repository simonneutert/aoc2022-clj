(ns aoc.puzzle0302)

(defn score-char [c]
  (inc (clojure.string/index-of "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ" c)))

(def example ["vJrwpWtwJgWrhcsFMMfFFhFp"
              "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"
              "PmmdzqPrVvPwwTWBwg"
              "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"
              "ttgJtRGJQctTZtZT"
              "CrZsJsPPZsGzwwsLwLmpwMDw"])

(def puzzle-input
  (clojure.string/split-lines (slurp "resources/puzzle0301.txt")))

(defn solve [data] (let [triples (partition 3 data)]
                     (->>
                      (->> (map #(map (fn [x] (set x)) %) triples)
                           (map #(apply clojure.set/intersection %))
                           (map #(score-char (first %)))
                           (reduce +)))))

(solve puzzle-input)
