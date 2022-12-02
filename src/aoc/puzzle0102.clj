(ns aoc.puzzle0102)

(def data ["1000"
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

(def input (->> (slurp "resources/puzzle0101.txt")
                clojure.string/split-lines))

(defn extract
  [input]
  (->> (loop [bucket []
              carry []
              data input]
         (let [h (first data)]
           (case h
             nil bucket
             "" (recur (conj bucket carry) [] (rest data))
             (recur bucket (conj carry h) (rest data)))))))

(->> (extract input)
     (mapv #(mapv (fn [i] (Integer/parseInt i)) %))
     (mapv #(apply + %))
     (sort >)
     (take 3)
     (apply +))
