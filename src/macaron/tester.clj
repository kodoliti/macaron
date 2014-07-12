(ns macaron.tester)


(defn- literal?
  "Returns true if the given form is an atomic compile-time literal."
  [form]
  (or (string? form)
      (keyword? form)
      (number? form)
      (contains? #{nil false true} form)))

(defn- expand-tree [tree]
  "Returns a flat list of forms to evaualte and append to render a tree."
  (cond
    (or (literal? tree) (list? tree) (instance? clojure.lang.Cons tree))
      (list tree)
    (and (vector? tree) (keyword? (first tree)))
      (println tree)
    (symbol? tree)
      (list tree)
    :else
      (throw "Unrecognized form %s, was a %s" tree (class tree))))


(defn mapper [& elements]
    (println (mapcat expand-tree elements))
    (println (.size elements ))
    (map println elements))


(println (mapper [:html "test"
                  [:head "111"]]))






;(compact )

;(mapper 1 2)

;(println (mapper 1 2))
