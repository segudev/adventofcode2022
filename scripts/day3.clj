(ns day3
  (:require [clojure.string :as s]
            [clojure.set :as set]))

(println "Day 3:")

(def input (slurp "inputs/input3.txt"))

(defn split-half [ruskstack]
  (let [c (count ruskstack)]
    (partition (/ c 2) ruskstack)))

(defn common-item [ruckstack]
  (-> ruckstack
      split-half
      (->> (map set)
           (apply set/intersection))
      first))

#_(def a (map common-item '("vJrwpWtwJgWrhcsFMMfFFhFp"
                            "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"
                            "PmmdzqPrVvPwwTWBwg"
                            "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"
                            "ttgJtRGJQctTZtZT"
                            "CrZsJsPPZsGzwwsLwLmpwMDw")));=> (\p \L \P \v \t \s)

(defn points [item]
  (let [i (str item)]
    (if (= (s/lower-case i) i)
      (- (int item) 96)
      (- (int item) (- 64 26)))))

(println "Response 1 is:"
         (-> input
             (s/split-lines)
             (->> (map common-item)
                  (map points)
                  (reduce +))))
; Part 2

(defn priority-group [group]
  (-> group
      (->> (map set)
           (apply set/intersection)
           first
           points)))

#_(priority-group '("vJrwpWtwJgWrhcsFMMfFFhFp"
                    "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"
                    "PmmdzqPrVvPwwTWBwg")); => 18

(println "Response 2 is:" (-> input
                              (s/split-lines)
                              (->> (partition 3)
                                   (map priority-group)
                                   (reduce +))))