(ns archytas.midi.core
  (:require [archytas.core :as c]
            [archytas.midi.util :as u]
            [archytas.util :as util]))

(defmulti midi-cmd
  "execute the given symbol midi command"
  (fn [cmd _ _ _ _] cmd))

(defmethod midi-cmd :note-on
  [_ board channel note velocity]
  (c/send-message board (channel (:note-on u/commands-channels))) ;;make channel a symbol
  (c/send-message board (note u/notes))
  (c/send-message board velocity)
  )

(defmethod midi-cmd :note-off
  [_ board channel note velocity]
  (c/send-message board (channel (:note-off u/commands-channels))) ;;make channel a symbol
  (c/send-message board (note u/notes))
  (c/send-message board velocity)
  )

(defmethod midi-cmd :polyphonic-kp
  [_ board channel note velocity]
  (c/send-message board (channel (:polyphonic-kp u/commands-channels))) ;;make channel a symbol
  (c/send-message board (note u/notes))
  (c/send-message board velocity)
  )

(defmethod midi-cmd :channel-pressure
  [_ board channel note velocity]
  (c/send-message board (channel (:channel-pressure u/commands-channels))) ;;make channel a symbol
  (c/send-message board (velocity))
  )

(defmethod midi-cmd :pitch-bend-change
  [_ board channel note velocity]
  (c/send-message board (channel (:pitch-bend-change u/commands-channels))) ;;make channel a symbol
  (c/send-message board (util/lsb velocity))
  (c/send-message board (util/msb velocity))
  )
