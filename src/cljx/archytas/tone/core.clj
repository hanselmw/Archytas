(ns archytas.tone.core
  (:require [archytas.core :as c]
            [archytas.tone.notes :as n]
            [archytas.util :as util]))

(defn write-note 
  "make the tone capable device produce a square wave"
  [board pin delay cycle]
  (loop [acc cycle]
    (when (> acc 0)
      (c/set-digital board pin 1)
      (util/delay-microseconds delay)
      (c/set-digital board pin 0)
      (util/delay-microseconds delay)
      (recur (- acc 1))
      ))
  (c/set-digital board pin 0)
  )


(defn play
  "Play the specified note at the given length of time"
  [board pin note length]
  (def frequency (if (number? note) note (note n/tones)))
  (if (nil? frequency)
    (write-note board pin 0 0)
    (do
      (def note-duration (/ 1000 length))
      (def delay-value (/ (/ 1000000 frequency) 2))
      (def cycles (Math/round (float (/ (* frequency note-duration) 1000))))
      (write-note board pin delay-value cycles)))
  )
