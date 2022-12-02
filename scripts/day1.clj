(ns day1
  (:require [clojure.string :as s]))

(def input (slurp "inputs/input1.txt"))

(def elves (-> input
               (s/split-lines)
               (->> (partition-by (partial = ""))
                    (remove #(or (= '("") %) (= '("…") %))))))

(def elves-calories (map (fn [l] (apply + (map #(Integer. %) l))) elves))

(def response1 (apply max elves-calories))

(def response2 (apply + (take 3 (sort > elves-calories))))

(println "Day 1:")
(println "Response 1 is: " response1)
(println "Response 2 is: " response2)