(ns archytas.messages)

;; Message Types
;; bytes(128-255/0x80-0xFF)

(def ANALOG-IO-MESSAGE     0xE0) ;; send data for a digital port (collection of 8 pins)
(def DIGITAL-IO-MESSAGE    0x90) ;; send data for an analog pin (or PWM)
(def REPORT-ANALOG-PIN     0xC0) ;; enable analog input by pin #
(def REPORT-DIGITAL-PORT   0xD0) ;; enable digital input by port pair
(def SET-PIN-MODE          0xF4) ;; set a pin to INPUT/OUTPUT/PWM/etc
(def SET-DIGITAL-PIN-VALUE 0xF5) ;; set value of an individual digital pin
(def PROTOCOL-VERSION      0xF9) ;; report protocol version
(def SYTEM-RESET           0xFF) ;; reset from MIDI

;; SysEx Messages

(def START-SYSEX 0xF0) ;; start a MIDI Sysex message
(def SYSEX-END   0xF7) ;; end a MIDI Sysex message

;; SysEx-Based Sub-commands

(def STRING           0x71)
(def FIRMWARE-VERSION 0x79)

;; SysEx Commands

(def RESERVED   (range 0x00 0x10)) ;; The first 16 bytes are reserved for custom commands
(def SERIAL-MESSAGE          0x60) ;; communicate with serial devices, including other boards
(def ENCODER-DATA            0x61) ;; reply with encoders current positions
(def ANALOG-MAPPING-QUERY    0x69) ;; ask for mapping of analog to pin numbers
(def ANALOG-MAPPING-RESPONSE 0x6A) ;; reply with mapping info
(def CAPABILITY-QUERY        0x6B) ;; ask for supported modes and resolution of all pins
(def CAPABILITY-RESPONSE     0x6C) ;; reply with supported modes and resolution
(def PIN-STATE-QUERY         0x6D) ;; ask for a pin's current mode and state (different than value)
(def PIN-STATE-RESPONSE      0x6E) ;; reply with a pin's current mode and state (different than value)
(def EXTENDED-ANALOG         0x6F) ;; analog write (PWM, Servo, etc) to any pin
(def SERVO-CONFIG            0x70) ;; pin number and min and max pulse
(def STRING-DATA             0x71) ;; a string message with 14-bits per char
(def STEPPER-DATA            0x72) ;; control a stepper motor
(def ONEWIRE-DATA            0x73) ;; send an OneWire read/write/reset/select/skip/search request
(def SHIFT-DATA              0x75) ;; shiftOut config/data message (reserved - not yet implemented)
(def I2C-REQUEST             0x76) ;; I2C request messages from a host to an I/O board
(def I2C-REPLY               0x77) ;; I2C reply messages from an I/O board to a host
(def I2C-CONFIG              0x78) ;; Enable I2C and provide any configuration settings
(def REPORT-FIRMWARE         0x79) ;; report name and version of the firmware
(def SAMPLING-INTERVAL       0x7A) ;; the interval at which analog input is sampled (default = 19ms)
(def SCHEDULER-DATA          0x7B) ;; send a createtask/deletetask/addtotask/schedule/querytasks/querytask request to the scheduler
(def SYSEX-NON-REALTIME      0x7E) ;; MIDI Reserved for non-realtime messages
(def SYSEX-REALTIME          0x7F) ;; MIDI Reserved for realtime messages

;; Defined in StandardFirmata.ino

(def I2C-WRITE                   2r00000000)
(def I2C-READ                    2r00001000)
(def I2C-READ-CONTINUOUSLY       2r00010000)
(def I2C-STOP-READING            2r00011000)
(def I2C-READ-WRITE-MODE-MASK    2r00011000)
(def I2C-10BIT-ADDRESS-MODE-MASK 2r00100000)
(def I2C-END-TX-MASK             2r01000000)
(def I2C-STOP-TX                 1)
(def I2C-RESTART-TX              0)
(def I2C-MAX-QUERIES             8)
(def I2C-REGISTER-NOT-SPECIFIED  -1)
(def MINIMUM-SAMPLING-INTERVAL   1) ;; the minimum interval for sampling analog input


(def  modes [:input :output :analog :pwm :servo :shift :i2c :onewire :stepper :encoder :serial :pullup :ignore]) ;; 13 modes 

(defn is-digital? [message]
  (<= 0x90 message 0x9F))

(defn is-analog? [message]
  (<= 0xE0 message 0xEF))
