(ns day1.core
  (:require [clojure.string :as string])
  (:gen-class))

(defn total-calories [lines]
  "Sum of calories for a set of lines"
  ;; Need to wrap Integer/parseInt because it is a static Java method.
  (reduce + (map #(Integer/parseInt %) (string/split-lines lines))))

(defn elf-calories-map
  "I don't do a whole lot ... yet."
  [input]
  (let [input-by-elf (string/split input #"\n\n")
         calorie-by-elf (map total-calories input-by-elf)]
    (into {} (map-indexed vector calorie-by-elf))))

(defn -main []
  (let [input-raw (slurp "input.txt")
        elf-calories (elf-calories-map input-raw)
        [e1 c1] (apply max-key #(get % 1) elf-calories)
        [e2 c2] (apply max-key #(get % 1) (dissoc elf-calories e1))
        [e3 c3] (apply max-key #(get % 1) (dissoc elf-calories e1 e2))]
    (+ c1 c2 c3)))

(-main)
