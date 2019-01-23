#lang racket

;Dizzy Farbanish

;I have adhered to the honor code in this assignment

(require "env.rkt")
(require "parse.rkt")
(provide eval-exp)


(define apply-proc
  (lambda (p arg-values)
      (cond
	[(prim-proc? p) (apply-primitive-op (cadr p) arg-values)]
        [(closure? p) (eval-exp  (caddr p)
                              (extended-env (cadr p) (map box arg-values) (cadddr p)))]
	(else (error 'apply-proc "Bad procedure: ~s" p)))))

(define eval-sequence
  (lambda (exps env)
     (cond
       [(null? exps) '()]
       [(null? (cdr exps)) (eval-exp (car exps) env)]
       [else (begin (eval-exp (car exps) env) (eval-sequence (cdr exps) env))]
   )))

(define eval-exp 
        (lambda (exp env)
               (cond
                      [(lit-exp? exp) (if (box? exp) (unbox (LitValue exp)) (LitValue exp))]
                      [(lambda-exp? exp) (new-closure (lambda-exp-parameters exp) (lambda-exp-body exp) env)]
                      [(var-ref? exp) (if (box? (lookup-env env (Symbol exp))) (unbox (lookup-env env (Symbol exp))) (lookup-env env (Symbol exp)))]
                      [(if-exp? exp)
                                     (if (eqv? (eval-exp (parse (if-exp-exp exp)) env) #t)
                                         (eval-exp (parse (if-exp-bar exp)) env) (eval-exp (parse (if-exp-baz exp)) env))]
                      [(define-exp? exp) (let ([sym (define-exp-sym exp)]
                                               [val (eval-exp (define-exp-exp exp) env)])
                                                               (do-define sym val))]
                      [(app-exp? exp) (apply-proc (eval-exp (parse (app-exp-op exp)) env)
                                  (map (lambda (x) (eval-exp (parse x) env)) (app-exp-opper exp)))]
                      [(let-exp? exp) 
                                      (eval-exp (parse (let-exp-body exp)) (extended-env (let-exp-sym exp) (map box (map (lambda (x) (eval-exp (parse x) env)) (let-exp-vals exp))) env))]
                      [(ass-exp? exp) (set-box! (lookup-env env (ass-exp-bind exp)) (eval-exp (parse (ass-exp-val exp)) env))]
                      [(begin? exp) (eval-sequence (map parse (begin-exps exp)) env)]
                      (else (error 'eval-exp  "Invalid exp: ~s" exp))
                      )))



