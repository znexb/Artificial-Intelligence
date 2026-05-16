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
(deffacts vending-machine-arcs
   (arc (from start)   (to s5)      (input N))
   (arc (from start)   (to s10)     (input Q))
   (arc (from s5)      (to s10)     (input N))
   (arc (from s5)      (to s15)     (input Q))
   (arc (from s10)     (to s15)     (input N))
   (arc (from s10)     (to s20)     (input Q))
   (arc (from s15)     (to s20)     (input N))
   (arc (from s15)     (to success) (input Q))
   (arc (from s20)     (to success) (input N))
   (arc (from s20)     (to success) (input Q)))

;;; Initial State
(deffacts initial-state
   (state (name start)))