(ns aoc.puzzle0502
  (:require [clojure.string :as str]))

(def example-data
  (clojure.string/split-lines "    [D]    
[N] [C]    
[Z] [M] [P]
 1   2   3 

move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2"))

(def puzzle-input (clojure.string/split-lines (slurp "resources/puzzle0501.txt")))

(defn clean-line [line]
  (mapv #(or (re-find #"\w" (apply str %)) "") line))

(def example
  (->> (take 3 example-data)
       (map #(partition 4 4 (str % " ")))
       (map clean-line)))

(def boxes (->> (take 8 puzzle-input)
                (map #(partition 4 (str % " ")))
                (map clean-line)))

(defn base-box-row [boxes]
  (count (first boxes)))

(defn create-vec-of-vec
  "creates a vector of empty vectors"
  [length]
  (loop [i (first (range length))
         coll []]
    (cond
      (= i length) coll
      :else (recur (inc i) (conj coll [])))))

(defn build-stack [i boxes-rows coll]
  (into [] (->> (mapv #(nth % i) boxes-rows)
                (assoc coll i))))

(defn transpose [boxes-rows]
  (let [rows (base-box-row boxes-rows)
        coll (create-vec-of-vec rows)]
    (loop
     [i (first (range rows))
      stacks coll]
      (cond
        (= rows i) stacks
        :else (recur (inc i) (build-stack i boxes-rows stacks))))))

(defn extract-triple [input-string]
  (->> (re-matcher #"(\d+).+(\d+).+(\d+)" input-string)
       re-find
       (drop 1)
       (map #(Integer/parseInt %))
       (into [])))

(defn extract-command-triple [input]
  (->> input
       (filter #(clojure.string/includes? % "move"))
       (map extract-triple)))

(defn move-stack [command stack]
  (let [[n f t] command]
    (let [from-column (into [] (remove empty? (nth stack (dec f))))
          to-columns (into [] (remove empty? (nth stack (dec t))))
          items (take n from-column)
          new-to-column (into [] (flatten (cons items to-columns)))
          new-from-column (into [] (drop n from-column))
          next-stack (-> (assoc stack (dec f) new-from-column)
                         (assoc (dec t) new-to-column))]
      next-stack)))

(defn solve [command-triples box-stacks]
  (->>
   (loop
    [commands command-triples
     stack box-stacks]
     (cond
       (nil? (first commands)) stack
       :else (recur (rest commands) (move-stack (first commands) stack))))
   (map first)
   (apply str)))

(comment
  (extract-command-triple example-data)
  (transpose example)

  (solve (extract-command-triple puzzle-input) (transpose boxes))
  ;;
  )