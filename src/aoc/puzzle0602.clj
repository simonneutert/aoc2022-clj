(ns aoc.puzzle0602)

(def example ["bvwbjplbgvbhsrlpgdmjqwftvncz"
              "nppdvjthqldpwncqszvftbrmjlhg"
              "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"
              "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"])

(def puzzle-input (first (clojure.string/split-lines (slurp "resources/puzzle0601.txt"))))

(defn finder
  [s length]
  (prn s)
  (loop
   [i 0
    j length]
    (let [snip (take length (drop i s))]
      (cond
        (= (count (set snip)) length) j
        :else (recur (inc i) (inc j))))))

(finder puzzle-input 14)
