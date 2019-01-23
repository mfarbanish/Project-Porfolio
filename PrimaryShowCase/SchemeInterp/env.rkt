#lang racket

;Dizzy Farbanish

;I have adhered to the honor code in this assignment

(provide empty-env extended-env)

(provide empty-env? extended-env? environment?)

(provide syms vals old-env the-empty-env)

(provide lookup-env init-env do-define)


(define empty-env (lambda () (list 'empty-env)))
(define extended-env (lambda (syms vals old-env) (list 'extended-env syms vals old-env)))


(define empty-env? (lambda (x)
      (cond
            [(not (pair? x)) #f]
            [else (eq? (car x) 'empty-env)])))


(define extended-env? (lambda (x)
      (cond
            [(not (pair? x)) #f]
            [else (eq? (car x) 'extended-env)])))



(define environment? (lambda (x) (or (empty-env? x) (extended-env? x))))


(define syms (lambda (env)
     (cond
           [(extended-env? env) (cadr env)]
           [else (error 'syms "bad environment")])))


(define vals (lambda (env)
      (cond
            [(extended-env? env) (caddr env)]
            [else (error 'vals "bad environment")])))



(define old-env (lambda (env)
     (cond
           [(extended-env? env) (cadddr env)]
           [else (error 'old-env "bad environment")])))


(define the-empty-env (empty-env))




(define lookup-env
  (lambda (envlist s)
    ;(display envlist)
    (cond
      ;((eqv? envlist '(empty-env)) (error 'apply-env "No binding for ~s" )) 
      ((eqv? (lookup-recurse envlist s) #f) (lookup-env (cadddr envlist) s))
      ((and (eqv? (lookup-recurse envlist s) #f) (eqv? (cadddr envlist) empty-env?))
           (error 'apply-env "No binding for ~s" ))
      (else (lookup-recurse envlist s))
      )))

(define lookup-recurse
  (lambda (env s)
    ;(display s)
    (cond
    ((eqv? (index s (car (cdr env))) -1) #f)
    (else (get (car (cddr env)) (index s (car (cdr env)))))
    )))

(define get
  (lambda (lat ind)
    (cond
      ((eqv? ind 0) (car lat))
      (else (get (cdr lat) (- ind 1)))
      )))              
    
(define index
        (lambda (i lat)
          (if (eq? lat '()) -1
          (if (eq? (car lat) i) 0
          (if (= (index i (cdr lat)) -1) -1
          (+ 1 (index i (cdr lat))))))))

(provide primitive-operators)

(define primitive-operators '(+ - * / add1 sub1 minus list first rest build isempty? nil
                                equals? lt? gt? equals? lt? gt? False True))

(define new-prim-proc
  (lambda (exp)
    (list 'prim-proc exp)
    ))

(define init-env (extended-env (append (list 'True 'False) primitive-operators)
	(map box (append (list 1 0) (map new-prim-proc primitive-operators))) 
        (empty-env)))


(define do-define (lambda (sym val)
                    (set! init-env (extended-env (list sym) (map box (list val)) init-env))))

