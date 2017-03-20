;Author: Borja Rojo
;Mostly copied from the Lab prompt, as instructed
;Geo-sum method located after the code from part 4

(define x 3)
(define y 5)
(display x) (newline)
(display y) (newline)
(+ x y)

(define foo (list + x y))
(define bar (list + 'x 'y))

(display foo) (newline)
(display bar) (newline)

(eval foo)
(eval bar)

(set! x 123)
(set! y 456)

(eval foo)
(eval bar)


(define (sum-ints a b)
  (if (> a b)
      0
      (+ a (sum-ints (+ a 1) b))))

(sum-ints 1 3)
(sum-ints 1 10)

(define (square x) (* x x))
(define (cube x) (* x x x))
(define (sum-sqrs a b)
  (if (> a b)
      0
      (+ (square a) (sum-sqrs (+ a 1) b))))
(sum-sqrs 1 5)
(define (sum-cubes a b)
  (if (> a b)
      0
      (+ (cube a) (sum-cubes (+ a 1) b))))
(sum-cubes 1 5)

(define (summer term a b)
  (if (> a b)
      0
      (+ (term a) (summer term (+ a 1) b))))

(define (sum-cubes2 a b) (summer cube a b))
(define (sum-sqrs2 a b) (summer square a b))
(sum-sqrs2 1 5)
(sum-cubes2 1 5)

(summer square 1 5)
(summer cube 1 5)

(define (plus-three x) (+ x 3)) ; standard
(plus-three 9)


(define plus-four (lambda (x) (+ x 4))) ; same using lambda
(plus-four 13)

(lambda (x) (+ x 4)) ; a stand alone method
((lambda (x) (+ x 5)) 21) ; take some time to digest this
; we are building a nameless function on the fly
; and then using it at the head of a list
; and passing it “21” as a parameter
; similarly
((lambda (x y) (* x y)) 7 13)
; the function only exists for the moment
; now, we can use lambda to build in-line functions
(define (tri-sum3 a b) (summer (lambda (x) x) a b))
(define (sum-sqrs3 a b) (summer (lambda (x) (* x x)) a b))
(define (sum-cubes3 a b) (summer (lambda (x) (* x x x)) a b))
(tri-sum3 1 5)
(sum-sqrs3 1 5)
(sum-cubes3 1 5)
(newline) (print "Result of Geo-sum: ") (newline)

;;;;;;;;;;;;;;;;;;;;
;;;Geo-sum method;;;
;;;;;;;;;;;;;;;;;;;;
(define (geo-sum a b) (summer (lambda (x) (/ 1 x)) a b))
(print "Geo-sum 1 1:") (geo-sum 1 1) (newline)
(print "Geo-sum 1 2:") (geo-sum 1 2) (newline)
(print "Geo-sum 1 3:") (geo-sum 1 3) (newline)
(print "Geo-sum 1 4:") (geo-sum 1 4) (newline)
(print "Geo-sum 1 5:") (geo-sum 1 5) (newline)
(print "Geo-sum 1 6:") (geo-sum 1 6) (newline)
(print "Geo-sum 1 7:") (geo-sum 1 7) (newline)

(define (f x y)
  ((lambda (a b)
     (+ (* x (* a a))
        (* y b)
        (* a b))
     ) ; end of "the function"
   (+ 1 (* x y)) ; local variable “a”
   (- 1 y) ; local variable “b”
   ))
(f 1 1)
(f 8 7)


(define (f2 x y)
  (let ((a (+ 1 (* x y )))
        (b (- 1 y)))
    (+ (* x (* a a))
       (* y b)
       (* a b )
       ) ; end of "+"
    ) ; end of "let"
  )  ; end of "define"
(f2 1 1)
(f2 8 7)
; note, we use the above syntax to define “local” variables
(display a) ; try this, a is not defined

