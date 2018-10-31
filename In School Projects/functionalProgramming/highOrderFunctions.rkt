#lang racket

;Dizzy Farbanish
;hw 3
;I have adhered to the honor code in this assignment


;1

(define firsts
  (lambda (llyst)
    (cond
      ((eqv? llyst '()) '())
      (else (cons (caar llyst) (firsts (cdr llyst))))
      )))

;(firsts '( (a b c) (d e f) (g h i) ) )

(define rests
  (lambda (llyst)
    (cond
      ((eqv? llyst '()) '())
      (else (cons (cdar llyst) (rests (cdr llyst))))
      )))

;(rests '( (a b c) (d e f) (g h i) ) )

;2

(define addvec
  (lambda (vec1 vec2)
    (cond
      (apply (map + vec1 vec2))
      )))

;(addvec '(1 2 3) '(4 5 6) )
;(addvec '( ) '( ) )

;3

(define mult
    (lambda (vec1 vec2)
    (cond
      ((map * vec1 vec2)))
      ))

  
(define dot-product
  (lambda (vec1 vec2)
    (cond
      ((eqv? vec1 '()) 0)
      (else (+ (car (mult vec1 vec2)) (dot-product (cdr vec1) (cdr vec2))))
      )))

;(dot-product '(1 4 3) '(4 5 6) )


;4

(define dot-row
  (lambda (vec1 vec2)
    (cond
      ((eqv? vec2 '()) '())
      (else (cons (apply + (mult vec1 (car vec2))) (dot-row vec1 (cdr vec2))))
      )))

;(dot-row '(1 2 3) '((1 4 7) (2 5 8) (3 6 9)) )

;5

(define transpose
  (lambda (mat)
    (cond
      ((eqv? (car mat) '()) '())
      (else (cons (map first mat) (transpose (rests mat))))
      )))

;(transpose '((1 2 3) (4 5 6)) )

;6


(define matmult
  (lambda (mat1 mat2)
    (cond
      ((null? mat1) '())
      (else (cons (dot-row (car mat1) (transpose mat2)) (matmult (cdr mat1) mat2)))
      )))

;(matmult '((1 2 3) (4 5 6)) '((1 2) (3 4) (5 6)) ) 

;7

      
(define flatten
  (lambda (L)
    (cond
      ((eqv? '() L) '())
      ((list? (car L)) (append (flatten (car L)) (flatten (cdr L))))
      (else (cons (car L) (flatten (cdr L))))
      )))

;(flatten '(a (x (y)) (((y)) y z)))

;8

(define sum
  (lambda (L)
    (apply + (flatten L))
    ))

;(sum '( (1 (2)) (((4))) 5))

;9

;(define add1
 ; (lambda (num)
  ;  (+ 1 num)
   ; ))

(define apply-to
  (lambda (f L)
    (cond
      ((eqv? L '()) '())
      ((eq? (list? L) #f) (f L))
      (else (map (lambda (x) (apply-to f x)) L))
      )))


;(apply-to add1 '(3 (4 5)))

;10

(define element?
  (letrec
      ([ h
         (lambda (a L acc)
           (cond
             ((eqv? L '()) #f)
             ((eqv? acc 0) (h a (flatten L) (+ 1 acc)))
             ((eqv? (car L) a) #t)
             (else (h a (cdr L) acc))
             ))])
    (lambda (a L) (h a L 0))
    ))

;(element? 'y  '(a (x (y)) (((y)) y z)))









