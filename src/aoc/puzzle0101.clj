(ns aoc.puzzle0101)

(def example ["1000"
              "2000"
              "3000"
              ""
              "4000"
              ""
              "5000"
              "6000"
              ""
              "7000"
              "8000"
              "9000"
              ""
              "10000"])

(def puzzle-input (->> (slurp "resources/puzzle0101.txt")
                       clojure.string/split-lines))

(defn solve
  [input]
  (->> (loop [bucket []
              carry []
              data input]
         (let [h (first data)]
           (case h
             nil bucket
             "" (recur (conj bucket carry) [] (rest data))
             (recur bucket (conj carry h) (rest data)))))))

(->> (solve puzzle-input)
     (mapv #(mapv (fn [i] (Integer/parseInt i)) %))
     (mapv #(apply + %))
     (apply max))
