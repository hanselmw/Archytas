(ns archytas.sensors.temperature)

(defmulti get-temperature-from-voltage
  "Get temperature from voltage in the specified unit of measurement symbol for temperature."
  (fn [unit _] unit )
  )

(defmethod get-temperature-from-voltage :celsius
  [_ voltage]
  (float (* (- voltage 0.5) 100)))

(defmethod get-temperature-from-voltage :fahrenheit
  [_ voltage]
  (float (+ (* (/ 9 5) (* (- voltage 0.5) 100)) 32)))

(defmethod get-temperature-from-voltage :kelvin
  [_ voltage]
  (float (+ (* (- voltage 0.5) 100) 273.15)))


(defmulti convert-temperature
  "Convert temperature from unit, to unit  according to temp value."
  (fn [from to _] [from to]))

(defmethod convert-temperature [:celsius :fahrenheit]
  [_ _ temp]
  (float (+ (* temp (/ 9 5)) 32)))

(defmethod convert-temperature [:celsius :kelvin]
  [_ _ temp]
  (float (+ temp 273.15)))

(defmethod convert-temperature [:fahrenheit :celsius]
  [_ _ temp]
  (float (* (- temp 32) (/ 5 9))))

(defmethod convert-temperature [:fahrenheit :kelvin]
  [_ _ temp]
  (float (* (+ temp 459.67) (/ 5 9))))

(defmethod convert-temperature [:kelvin :celsius]
  [_ _ temp]
  (float (- temp 273.15)))

(defmethod convert-temperature [:kelvin :fahrenheit]
  [_ _ temp]
  (float (- (* temp (/ 9 5)) 459.67)))
