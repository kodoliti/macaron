(ns macaron.core-new)

(defn generate-tag [element]
     (str "<" (name element) ">" ))


(defn to-html [elements]
    (doseq [e elements]
        (if (vector? e)
            (to-html e)
            (generate-tag e))))


(defn to-html2 [elements]
  (doseq [e elements]
    (cond
        (vector? e)
        (to-html2 e)
        (keyword? e)
        e
        (string? e)
        e
        :else
        (generate-tag e))))

(defn to-tag [elements]
    (doseq [e elements]
        (cond
            (map? e)
            (to-html2 e)
            (vector? e)
            (to-html2 e)
            (keyword? e)
            e
            (string? e)
            e
            :else
            (generate-tag e))))


(defn to-tag2 [elements]
    (doseq [e elements]
        (cond
            (vector? e)
            (to-html2 e)
            :else
            (generate-tag e))))


(defn validate-tag [elements]
    (let [fElement (first elements)]
        (if (keyword? fElement)
            (to-tag2 elements)
            (throw (Exception. "There is no opening tag")))))



(defn to-html3 [elements]
   (if (vector? elements)
       (validate-tag elements)
       (throw (Exception. "Unrecognized form"))))


 ;  (throw (Exception. "Unrecognized form %s, was a %s" elements (class elements)))))

;    (cond
;       (vector? e)
;        (to-html2 e)
;        (keyword? e)
;        e
;        (string? e)
;        e
;        :else
;        (generate-tag e)))



;;(to-html3 [:html [:head {:class  ".style"} "test"]])

;(println (map to-html2 [:html "test"
 ;                                  [:body "test2"]]))

;(println (apply str (to-html2 [:html "test"
;                                   [:body "test2"]])))




