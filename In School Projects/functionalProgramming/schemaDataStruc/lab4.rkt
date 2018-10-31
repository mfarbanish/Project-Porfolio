#lang racket

(require "TreeDatatype.rkt")

;Dizzy Farbanish

;I have adhered to the honor code in this assignment

;1

(define length (lambda (lat) (foldr (lambda (x y) (+ 1 y)) 0 lat)))

(define index
  (lambda (a lat)
    (foldr (lambda (x y)
             (+ 1 (if (eq? x a) -1 y))) (- -1 (length lat)) lat)))



;(index  10 '(1 2 3 4 4 3 10 1))


;2

 (define replace
    (lambda (a b lat)
      (foldr (lambda (x y)
               (cons (if (equal? a x) b x) y))
               '()
               lat)
             ))

;(replace 3 5 '(1 2 3 4 5 4 3 2 1))

;3

(define bags '((duffle 8) (garment-bag 2) (briefcase 5) (steamer-trunk 65) (valise 7)))

(define weigh
  (lambda (bags)
    (foldr (lambda (x y)
             (+ (cadr x) y)) 0 bags)))

;(weigh bags)

;(define a 0)


(define heaviest
  (lambda (bags)
    (car (foldr (lambda (x y)
             (if (> (cadr x) (cadr y)) x y))
           '(base 0) bags))
    ))

;(heaviest bags)

;4

(define childSum
  (lambda (tr)
    (cond
      ((empty-tree? tr) 0)
      ((leaf? tr) 0)
      (else (apply + (map value (list-of-children tr))))
    )))

;(childSum T6)
;(childSum T8)

;5

(define allSum
  (lambda (tr)
    (cond
      ((empty-tree? tr) 0)
      ((leaf? tr) (value tr))
      (else (+ (value tr) (apply + (map allSum (list-of-children tr)))))
      )))

;(allSum T8)
;(allSum T6)

;6

(define add1
  (lambda (z)
    (+ 1 z)
    ))

(define visitTree
  (lambda (f tr)
    (cond
    ((leaf? tr) (makeTree (f (value tr))))
    (else (append (list 'tree (f (value tr))) (list (map (lambda (t) (visitTree f t)) (list-of-children tr)))))
    )))

;(visitTree add1 T8)
;T8

;7

(define sizeof
  (lambda (tr)
    (cond
      ((empty-tree? tr) 0)
      ((leaf? tr) 1)
      (else (+ 1 (apply + (map sizeof (list-of-children tr)))))
      )))

;(sizeof T8)
;(sizeof T6)

;8

(define height
 (lambda (tr)
   (cond
     ((empty-tree? tr) -1)
     ((leaf? tr) 0)
     (else (+ 1 (apply max (map height (list-of-children tr)))))
     )))

;(height T1)
;(height T8)

;9

(define count
         (lambda (n tr)
           (cond
             ((and (leaf? tr) (eq?(eqv? (value tr) n) #f)) 0)
             ((and (leaf? tr) (eqv? (value tr) n)) 1)
             ((eqv? (value tr) n) (+ 1 (apply + (map (lambda (x) (count n x)) (list-of-children tr)))))
             (else (apply + (map (lambda (x) (count n x)) (list-of-children tr))))
             )))

;(count 7 T8)

;10

(define flatten
  (lambda (L)
    (cond
      ((eqv? '() L) '())
      ((list? (car L)) (append (flatten (car L)) (flatten (cdr L))))
      (else (cons (car L) (flatten (cdr L))))
      )))

(define preorder
  (lambda (tr)
            (cond
              ((leaf? tr) (cadr tr))
              (else (cons (value tr) (flatten (map preorder (list-of-children tr)))))
              )))


;(preorder T8)

;11
(define postorder
  (lambda (tr)
            (cond
              ((leaf? tr) (cadr tr))
              (else (append (flatten (map postorder (list-of-children tr))) (list (value tr))))
              )))


;(postorder T8)

;T8


