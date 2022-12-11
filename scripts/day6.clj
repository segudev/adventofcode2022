(ns day6
  (:require [clojure.string :as s]))

(println "Day 6:")

(def input (slurp "inputs/input6.txt"))

(defn subroutine [signal]
  (loop [start-packet-pos 0]
    (let [packet (take 4 (drop start-packet-pos signal))
          end-of-packet-pos (+ 3 start-packet-pos)]
      (if (apply distinct? packet)
        (inc end-of-packet-pos)
        (recur
         (inc start-packet-pos))))))

(assert (= '(7 5 6 10 11)
           (map subroutine '("mjqjpqmgbljsphdztnvjfqwrcgsmlb"
                             "bvwbjplbgvbhsrlpgdmjqwftvncz"
                             "nppdvjthqldpwncqszvftbrmjlhg"
                             "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"
                             "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"))))

(println "Response 1 is: " (subroutine input))

; Part 2

(defn subroutine2 [signal]
  (loop [start-packet-pos 0]
    (let [packet (take 14 (drop start-packet-pos signal))
          end-of-packet-pos (+ 13 start-packet-pos)]
      (if (apply distinct? packet)
        (inc end-of-packet-pos)
        (recur
         (inc start-packet-pos))))))

(assert (= '(19 23 23 29 26)
           (map subroutine2 '("mjqjpqmgbljsphdztnvjfqwrcgsmlb"
                             "bvwbjplbgvbhsrlpgdmjqwftvncz"
                             "nppdvjthqldpwncqszvftbrmjlhg"
                             "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"
                             "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"))))

(println "Response 2 is: " (subroutine2 input))