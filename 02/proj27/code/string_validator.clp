;;; Templates
(deftemplate state (slot name))
(deftemplate arc (slot from) (slot to) (slot input))

;;; Rules
(defrule fsm-transition
   ?current <- (state (name ?s))
   ?inp     <- (input ?value)
   (arc (from ?s) (to ?next) (input ?value))
   =>
   (retract ?current)
   (retract ?inp)
   (assert (state (name ?next)))
   (printout t "Transition: " ?s " --[" ?value "]--> " ?next crlf))

(defrule fsm-no-transition
   (state (name ?s))
   (input ?value)
   (not (arc (from ?s) (to ?) (input ?value)))
   =>
   (printout t "ERROR: No transition from " ?s " on input '" ?value "'" crlf))

;;; FSM Arc Facts
(deffacts string-validator-arcs
   ;; BEGIN path
   (arc (from start) (to sB)      (input B))
   (arc (from sB)    (to sBE)     (input E))
   (arc (from sBE)   (to sBEG)    (input G))
   (arc (from sBEG)  (to sBEGI)   (input I))
   (arc (from sBEGI) (to success) (input N))
   ;; WHILE path
   (arc (from start) (to sW)      (input W))
   (arc (from sW)    (to sWH)     (input H))
   (arc (from sWH)   (to sWHI)    (input I))
   (arc (from sWHI)  (to sWHIL)   (input L))
   (arc (from sWHIL) (to success) (input E))
   ;; WRITE path
   (arc (from sW)    (to sWR)     (input R))
   (arc (from sWR)   (to sWRI)    (input I))
   (arc (from sWRI)  (to sWRIT)   (input T))
   (arc (from sWRIT) (to success) (input E)))

;;; Initial State
(deffacts initial-state
   (state (name start)))