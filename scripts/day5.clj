(ns day5
  (:require [clojure.string :as s]))

(println "Day 5:")

(def input (slurp "inputs/input5.txt"))

(defn split-input [input]
  (loop [lines (s/split-lines input)
         stacks []
         moves []
         type :stacks]
    (if (empty? lines)
      [stacks moves]
      (let [line (first lines)]
        (cond
          (empty? line) (recur (rest lines) stacks moves :moves)
          (= type :stacks) (recur (rest lines) (conj stacks line) moves :stacks)
          :else (recur (rest lines) stacks (conj moves line) :moves))))))

(defn nb-stacks [stack-lines]
  (dec (count (s/split (last stack-lines) #"\s+"))))

(defn break-into-columns [n-columns stack-line]
  (let [groups-of-4-chars (partition 4 4 " " stack-line)
        partial-columns (map #(str (nth % 1)) groups-of-4-chars)
        column-pad (repeat n-columns " ")]
    (take n-columns (concat partial-columns column-pad))))

(defn extract-stack-names [stack-lines]
  (let [n-stacks (nb-stacks stack-lines)
        stack-lines (drop-last stack-lines)
        columns (map (partial break-into-columns n-stacks) stack-lines)]
    columns))

(defn remove-spaces [stack-with-spaces]
  (remove #(= " " %) stack-with-spaces))

(defn build-stacks [stack-lines]
  (let [n-rows (dec (count stack-lines))
        stack-rows (extract-stack-names stack-lines)
        stacks-with-spaces (partition n-rows (apply interleave stack-rows))]
    (map remove-spaces stacks-with-spaces)))

(defn parse-move [move]
  (let [matches (re-matches #"move (\d+) from (\d+) to (\d+)" move)]
    (map #(Integer/parseInt %) (rest matches))))

(defn parse-moves [moves]
  (map parse-move moves))

(defn move-1 [stacks [from to]]
  (let [stacks (vec stacks)
        from-index (dec from)
        to-index (dec to)
        item (first (nth stacks from-index))
        stacks (update-in stacks [from-index] rest)
        stacks (update-in stacks [to-index] (partial concat [item]))]
    stacks))

(defn cm9000-mover [stacks move]
  (let [[n from to] move]
    (reduce move-1 stacks (repeat n [from to]))))

(defn execute-moves [mover stacks moves]
  (reduce mover stacks moves))

(defn tops-of-stacks [stacks]
  (apply str (map first stacks)))

(defn solve [mover]
  (let [[stack-lines move-lines] (split-input input)
        stacks (build-stacks stack-lines)
        moves (parse-moves move-lines)
        stacks (execute-moves mover stacks moves)]
    (tops-of-stacks stacks)))

(defn cm9001-mover [stacks [n from to]]
  (let [stacks (vec stacks)
        from-index (dec from)
        to-index (dec to)
        items (take n (nth stacks from-index))
        stacks (update-in stacks [from-index] (partial drop n))
        stacks (update-in stacks [to-index] (partial concat items))]
    stacks))

(assert (= "SHQWSRBDL" (solve cm9000-mover)))
(assert (= "CDTQZHBRS" (solve cm9001-mover)))

(println "Response 1 is: " (solve cm9000-mover))
(println "Response 2 is: " (solve cm9001-mover))