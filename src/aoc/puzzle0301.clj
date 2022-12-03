(ns aoc.puzzle0301)

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

(defn solve [data] (let [comp (->> (map #(conj [] % (/ (count %) 2)) data)
                                   (map #(split-at (last %) (first %))))]
                     (->> comp
                          (map setty-comps)
                          (map #(apply clojure.set/intersection %))
                          (map #(score-char (first %)))
                          (reduce +))))

(solve (clojure.string/split-lines (slurp "resources/puzzle0301.txt")))