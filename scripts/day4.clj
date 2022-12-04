(ns day4
  (:require [clojure.string :as s]))

(println "Day 4:")

(def input (slurp "inputs/input4.txt"))

(defn pair [vector]
  (let [[x y] vector
        first-elf (s/split x #"-")
        second-elf (s/split y #"-")
        parse #(Integer/parseInt %)
        range-elf (fn [elf]
                    (let [[start end] elf]
                      (range (parse start)
                             (inc (parse end)))))]
    (map range-elf (list first-elf second-elf))))

(defn one-fully-contains-other? [pair]
  (let [s1 (set (first pair))
        s2 (set (second pair))
        intersection (clojure.set/intersection s1 s2)]
    #_(prn pair)
    (or (= intersection s1)
        (= intersection s2))))

#_(-> ["2-4" "6-8"]
      pair
      one-fully-contains-other?);=> false

#_(-> ["6-6" "4-6"]
      pair
      one-fully-contains-other?);=> true

(defn count-truthy [coll]
  (reduce (fn [cnt val] (if val (inc cnt) cnt)) 0 coll))

(println "Response 1 is:" (-> input
                              s/split-lines
                              (->> (map (comp
                                         one-fully-contains-other?
                                         pair
                                         #(s/split % #","))))
                              count-truthy))
; Part 2

(defn overlaps? [pair]
  (let [s1 (set (first pair))
        s2 (set (second pair))
        intersection (clojure.set/intersection s1 s2)]
    #_(prn pair)
    (seq intersection)))

(println "Response 2 is:" (-> input
                              s/split-lines
                              (->> (map (comp
                                         overlaps?
                                         pair
                                         #(s/split % #","))))
                              count-truthy))