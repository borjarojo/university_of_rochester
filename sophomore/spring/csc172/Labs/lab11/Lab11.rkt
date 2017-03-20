;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname Lab11) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f ())))
486
(+ 1 3)
(- 7 4)
(- 4 7)
(* 3 5)
(/ 35 7)
(/ 7 35)
(+ 1 2 3 4)
(* 3 5 7)
(/ 7 5 3)
(* ( + 3 4)( + 10 3))
(+ ( * 3 ( + ( * 2 4) (+ 3 5 ))) (+ ( - 10 7 ) 6 ))
(+ ( * 3
       (+ (* 24) 
          (+ 3 5)))
   (+ (- 10 7) 
      6 ))
(< 3 0)
(> 3 0)
(and (> 3 0) (< 3 0))
(or (> 3 0) (< 3 0))
(print "Hello, world")
(print "\n") ;tried to escape a line break... didnt work

(define length2 13)
length2
(* 3 length2)
; this will draw an error
; don't worry it's just an example
; you have to define variables before you use them
(define width 17)
(* length2 width)
(define area (* width length2))
area
(define my_pi 3.14159 )
(define radius 11)
(* my_pi (* radius radius ))
(define circumference (* 2 my_pi radius))
circumference

(define (square x) (* x x))
(square 10)
(square length2)
(+ (square 5) (square 4))
(square (square 3))
(define (sum-of-squares x y) (+ (square x) (square y)))
(sum-of-squares 3 4)

;my own practice
(define (circumference2 r) (* pi r r))
(circumference2 1)
(circumference2 9)
(circumference2 4)
;Interesting... functions cannot be defined as the same as variable names...

(if (< width 3) (print "short") (print "long"))
(define width2 2)
(if (< width2 3) (print "short") (print "long"))
(if (> length2 2) 7 11)
(define (absolute-value x)
  (if (< x 0)
      (- x)
      x )
  )
(absolute-value -3)
(absolute-value (* 7 -3))
(define (maximum x y) (if (> x y) x y))
(maximum 3 7)
(define (factorial n)
  (if (< n 1 ) 1 (* n (factorial (- n 1 )))))
(factorial 3)
(factorial 7)

(print "This is my GCD Code and some Examples")
(newline)
; greatest common divisor using modulo
(define (GCD x y)
   (if (or (= x 0)(= y 0))
       (if (= x 0) y x)
       (if (< x y)
           (GCD (modulo y x) x)
           (GCD (modulo x y) y)
       )
   )
)
;If any of them is equal to zero, the which ever is not equal to zero is GCD
;Other wise, modulo the larger by the smalled and called GCD with the modulo and the smaller
;This will cause a GCD to come out at some point as the modulo will eventuall hit zero, meaning the 
;GCD was indeed the smallest of the two.
(print "GCD 18 12")
(GCD 18 12)
(print "GCD 52 91")
(GCD 52 91)
(print "GCD 21 35")
(GCD 35 21)
(print "GCD 135 54")
(GCD 54 135)

      


