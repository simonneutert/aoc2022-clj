(ns aoc.puzzle0302)

(defn score-char [c]
  (inc (clojure.string/index-of "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ" c)))

(def example ["vJrwpWtwJgWrhcsFMMfFFhFp"
              "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"
              "PmmdzqPrVvPwwTWBwg"
              "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"
              "ttgJtRGJQctTZtZT"
              "CrZsJsPPZsGzwwsLwLmpwMDw"])

(defn setty-comps [comp]
  (map set comp))

(defn solve [data] (let [triples (partition 3 data)]
                     (->>
                      (->> (map #(map (fn [x] (set x)) %) triples)
                           (map #(apply clojure.set/intersection %))
                           (map #(score-char (first %)))
                           (reduce +)))))

(solve (clojure.string/split-lines (slurp "resources/puzzle0301.txt")))
