(ns macaron.core-draft)

(defn- literal?
  "Returns true if the given form is an atomic compile-time literal."
  [form]
  (or (string? form)
      (keyword? form)
      (number? form)
      (contains? #{nil false true} form)))

(defn append-code [form]
    (if (literal? form)
    '(println "literal " form)
    '(if-let [content# ~form]
       ("NON literal " content#))))

(defn html [& elements]
    (map append-code elements))

(println (html [:body "Hello World"]))



;;(println (map reverse [[3 2 1 0] [6 5 4] [9 8 7]]))

;;(defmacro unless [test then]
;;`(if (not ~test)
;;;then))

;;(defmacro unless [test then]
  ;  (list 'if (list 'not test)
   ;     then))

;(defn exhibits-oddity? [x]
 ;   (unless (even? x)
  ;      (println "Very odd, indeed!")))




