(ns day2
  (:require [clojure.string :as s]))

(println "Day 2 :")

(def input (slurp "inputs/input2.txt"))

(defn compute-score [rule]
  (-> input
      (s/split-lines)
      (->> (map #(s/split % #" "))
           (map rule)
           (reduce +))))
; Part 1

(def my-hand {"X" 1 ;rock
              "Y" 2 ;paper
              "Z" 3 ;scissors
              })

(def outcomes {"A" {"X" 3
                    "Y" 6
                    "Z" 0}
               "B" {"X" 0
                    "Y" 3
                    "Z" 6}
               "C" {"X" 6
                    "Y" 0
                    "Z" 3}})

(defn rule1 [[first second]]
  (+ (my-hand second)
     (get-in outcomes [first second])))

#_(map rule1 '(["A" "Y"]
               ["B" "X"]
               ["C" "Z"]))

(println "Response 1 is:" (compute-score rule1))

; Part Two

(def my-strategy {"X" 0 ;lose
                  "Y" 3 ;draw
                  "Z" 6 ;win
                  })

(def outcomes2 {"A" {"X" 3
                     "Y" 1
                     "Z" 2}
                "B" {"X" 1
                     "Y" 2
                     "Z" 3}
                "C" {"X" 2
                     "Y" 3
                     "Z" 1}})

(defn rule2 [[first second]]
  (+ (my-strategy second)
     (get-in outcomes2 [first second])))

#_(map rule2 '(["A" "Y"];=>   1 + 3 = 4 
               ["B" "X"];=>   1 + 0 = 1 
               ["C" "Z"]));=> 1 + 6 = 7

(println "Response 2 is:" (compute-score rule2))