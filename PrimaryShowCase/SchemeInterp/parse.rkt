#lang racket

;Mahlon Farbanish

;I have adhered to honor code in this assignment

(provide parse LitValue lit-exp?)
(provide var-ref? Symbol define-exp? define-exp-sym define-exp-exp)
(provide new-prim-proc prim-proc? new-var-ref)

;scheme a

(define new-lit-exp
  (lambda (exp)
    (list 'lit-exp exp)
    ))

(define lit-exp?
  (lambda (exp)
    (cond
      ((list? exp) (if (eq? (car exp) 'lit-exp) #t #f))
      (else #f)
      )))

(define LitValue
  (lambda (exp)
    (cond
      (lit-exp? (cadr exp))
      (else (error 'expression "Invalid syntax ~s" exp))
    )))

;scheme b

(define new-var-ref
  (lambda (exp)
    (list 'var-ref exp)
    ))

(define var-ref?
  (lambda (exp)
     (cond
      ((list? exp) (if (eq? (car exp) 'var-ref) #t #f))
      (else #f)
      )))

(define Symbol
  (lambda (var)
    (cond
      (var-ref? (cadr var))
      (else (error 'expression "Invalid syntax ~s" var))
    )))

(define new-define-exp
 (lambda (sym val)
   (list 'define sym val)
   ))

(define define-exp?
  (lambda (exp)
    (cond
      ((list? exp) (if (eq? (car exp) 'define) #t #f))
      (else #f)
      )))


(define define-exp-sym
  (lambda (exp)
    (cadr exp)
    ))

(define define-exp-exp
  (lambda (exp)
    (caddr exp)
    ))


;scheme c

(define new-prim-proc
  (lambda (exp)
    (list 'prim-proc exp)
    ))


(define add1
  (lambda (x)
    (+ 1 x)
    ))

(define sub1
  (lambda (x)
    (- x 1)
    ))

(define minus
  (lambda (x)
    (- 0 x)
    ))

(define build
  (lambda (y)
    (cons (car y) (cadr y))
    ))

(define isempty?
 (lambda (x)
   (cond
     ((eqv? x '()) #t)
     (else #f)
     )))


(define prim-proc?
  (lambda (exp)
    (cond
      ((eqv? (car exp) 'prim-proc) #t)
      (else #f)
      )))


(provide app-exp? new-app-exp app-exp? app-exp-op app-exp-opper apply-primitive-op)

(define new-app-exp
  (lambda (exp)
    (list 'app-exp exp)
    ))


(define app-exp?
  (lambda (exp)
    (cond
      ((list? exp) (if (eq? (car exp) 'app-exp) #t #f))
      (else #f)
      )))

(define app-exp-op
  (lambda (exp)
    (caadr exp)
    ))

(define app-exp-opper
  (lambda (exp)
    (cdr (cadr exp))
    ))
    

(define apply-primitive-op
  (lambda (op arg-values)
	(cond
	    [(eq? op '+) (+ (car arg-values) (cadr arg-values))]
	    [(eq? op '-) (- (car arg-values) (cadr arg-values))]
            [(eq? op '*) (* (car arg-values) (cadr arg-values))]
	    [(eq? op '/) (/ (car arg-values) (cadr arg-values))]
            [(eq? op 'add1) (add1 (car arg-values))]
            [(eq? op 'sub1) (sub1 (car arg-values))]
            [(eq? op 'minus) (minus (car arg-values))]
            [(eq? op 'list) arg-values]
            [(eq? op 'first) (caar arg-values)]
            [(eq? op 'rest) (cdar arg-values)]
            [(eq? op 'build) (build arg-values)]
            [(eq? op 'isempty?) (isempty? (car arg-values))]
            [(eq? op 'nil) '()]
            [(eq? op 'equals?) (eqv? (car arg-values) (cadr arg-values))]
            [(eq? op 'lt?) (< (car arg-values) (cadr arg-values))]
            [(eq? op 'gt?) (> (car arg-values) (cadr arg-values))]
            )))


;parseD

(provide if-exp? if-exp-exp if-exp-bar if-exp-baz)


(define if-exp?
  (lambda (exp)
    (cond
      ((list? exp) (if (eq? (car exp) 'if) #t #f))
      (else #f)
      )))

(define if-exp-exp
  (lambda (foo)
    (cadr foo)
    ))

(define if-exp-bar
  (lambda (bar)
    (caddr bar)
    ))

(define if-exp-baz
  (lambda (baz)
    (cadddr baz)
    ))

;parseE

(provide expression new-define-exp)



(provide let-exp-sym  let-exp-vals let-exp-body let-exp?)


(define let-exp?
  (lambda (exp)
    (cond
      ((list? exp) (if (eq? (car exp) 'let) #t #f))
      (else #f)
      )))

(define let-exp-sym
  (lambda (exp)
    (cond
      ((null? exp) '())
      ((eqv? (car exp) 'let) (let-exp-sym (cadr exp)))
      (else (cons (caar exp) (let-exp-sym (cdr exp))))
    )))


(define let-exp-vals
  (lambda (exp)
    (cond
      ((null? exp) '())
      ((eqv? (car exp) 'let) (let-exp-vals (cadr exp)))
      (else (cons (cadar exp) (let-exp-vals (cdr exp))))
    )))

(define let-exp-body
  (lambda (exp)
    (caddr exp)
    ))


;(let-exp-body '(let ( (a 1) (b 5) ) (+ a b)))


;schemeF

(provide lambda-exp? lambda-exp-parameters lambda-exp-body new-closure closure? new-lambda-exp)

(define new-lambda-exp
  (lambda (exp)
    (list 'lambda-exp (cadr exp) (parse (caddr exp)))
    ))

(define lambda-exp?
  (lambda (exp)
    (cond
      ((list? exp) (if (eq? (car exp) 'lambda-exp) #t #f))
      (else #f)
      )))

(define lambda-exp-parameters
  (lambda (exp)
    (cadr exp)
    ))

;(parse '(+ x y))

(define lambda-exp-body
  (lambda (exp)
    (caddr exp)
    ))

(define new-closure
  (lambda (par body env)
    (list 'closure par body env)
    ))

(define closure?
  (lambda (exp)
    (cond
      ((list? exp) (if (eq? (car exp) 'closure) #t #f))
      (else #f)
      )))

(provide closure-body closure-param closure-env)

(define closure-body
  (lambda (exp)
    (caddr)
    ))

(define closure-param
  (lambda (exp)
    (cadr)
    ))

(define closure-env
  (lambda (exp)
    (cadddr)
    ))

;MiniSchemeG


(provide ass-exp? ass-exp-bind ass-exp-val begin? begin-exps)

(define ass-exp?
  (lambda (exp)
    (cond
      ((list? exp) (if (eq? (car exp) 'set!) #t #f))
      (else #f)
      )))

(define ass-exp-bind
  (lambda (exp)
          (cadr exp)
          ))

(define ass-exp-val
  (lambda (exp)
    (caddr exp)
    ))

(define begin?
  (lambda (exp)
    (cond
      ((list? exp) (if (eq? (car exp) 'begin) #t #f))
      (else #f)
      )))

(define begin-exps
  (lambda (exp)
    (cdr exp)
    ))


(define make-bindinglist
  (lambda (symlat binglat)
    (cond
      ((null? symlat) '())
      (else (cons (list (car symlat) (car binglat))
                  (make-bindinglist (cdr symlat) (cdr binglat))
                  )))))

(define new-let-exp
  (lambda (sym bindings body)
    (list 'let (make-bindinglist sym bindings) body)))


(define new-begin-exp
  (lambda (exp)
    (cons 'begin exp)))

(define new-assign-exp
  (lambda (sym exp)
    (list 'set! sym exp)))

;minischemeH

(provide make-letrec)

(define letrec?
  (lambda (exp)
    (cond
      ((list? exp) (if (eq? (car exp) 'letrec) #t #f))
      (else #f)
      )))

(define make-letrec
  (lambda (ids vals body)
    (let* ([new-vals (map (lambda(x) 0) ids)] [new-ids (map (lambda(x) (gensym)) vals)])
    (new-let-exp ids new-vals (new-let-exp new-ids vals (new-begin-exp (append (map (lambda(x y) (new-assign-exp x y)) ids new-ids) (list body)))))))) 


 (define expression
   (lambda (input)
          (cond
            [(number? input) (new-lit-exp input)]
            [(symbol? input) (new-var-ref input)]
            [else (error 'expression "Invalid syntax ~s" input)])))


(define parse
  (lambda (input)
    (cond
      [(not (pair? input)) (expression input)]
      [(eq? (car input) 'if) input]
      [(eq? (car input) 'define)(new-define-exp (cadr input) (expression (caddr input)))]
      [(eqv? (car input) 'let) input]
      [(eqv? (car input) 'lambda) (new-lambda-exp input)]
      [(eqv? (car input) 'set!) input]
      [(eqv? (car input) 'begin) input]
      [(eqv? (car input) 'letrec) (make-letrec (map car (cadr input)) (map (lambda(x) (cadr x)) (cadr input)) (caddr input))]
      [else (new-app-exp input)]
      )))

