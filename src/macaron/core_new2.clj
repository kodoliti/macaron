(ns macaron.core-new2)


(defn generate-tag [element]
    (println  (str "<" (name element) "/>" )))


(defn to-html [elements]
    (doseq [e elements]
        (if (vector? e)
            (to-html e)
            (generate-tag e))))


;(println (to-html [:html "test"
 ;                                  [:body "test2"]]))


(println (mapcat println [:html "test" [:body "test2"]]))