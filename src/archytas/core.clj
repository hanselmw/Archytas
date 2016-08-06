(ns archytas.core)
(:require [archytas.messages :as m])


(def ^:private mode-values (zipmap modes (range 0 (count modes))))    
(def ^:private MAX-PORTS 16)
(def arduino-port-count 7)
