(ns aoc.puzzle0402
  (:require [clojure.string :as s]))

(def example ["2-4,6-8"
              "2-3,4-5"
              "5-7,7-9"
              "2-8,3-7"
              "6-6,4-6"
              "2-6,4-8"])

(def puzzle-input (s/split-lines (slurp "resources/puzzle0401.txt")))

(defn order-input
  [input]
  (->> (s/split input #",")
       (map #(s/split % #"-"))))

(defn detect-subset [input]
  (let [sorted-entries (order-input input)
        lowest-1 (str (first (first sorted-entries)))
        highest-1 (str (last (first sorted-entries)))
        lowest-2 (str (first (last sorted-entries)))
        highest-2 (str (last (last sorted-entries)))
        range-1 (range (Integer/parseInt lowest-1) (inc (Integer/parseInt highest-1)))
        range-2 (range (Integer/parseInt lowest-2) (inc (Integer/parseInt highest-2)))
        sorted-ranges (mapv set (sort-by count [range-1 range-2]))]
    (> (count (clojure.set/intersection
               (first sorted-ranges)
               (last sorted-ranges))) 0)))

(defn solve [input]
  (count (filter true? (map detect-subset input))))

(solve example)
(solve puzzle-input)
