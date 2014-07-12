(ns macaron.core)

(defn to-tag [elements]
    (doseq [e elements]
        (cond
            (map? e)
            (print (str " map = " e))
            (vector? e)
            (to-tag e)
            (keyword? e)
            (print (str " keyword = " e))
            (string? e)
            (print (str " string = " e))
            :else
            (print e))))

;;(comment (defn generate-attr [attr]
 ;;   (let [key (nth (map key {:class ".html"}) 0)
   ;;       name (name key)
     ;;     value (key attr)]
       ;; (str name "=" "\"" value "\""))))

;;(defn generate-attr [attr]
;;    (let [keys (map key attr)]
  ;;      (doseq [k keys]
    ;;        (str (name k) "=" "\"" (k attr) "\""))))

(defn generate-attr [attr]
    (let [key (key attr)
          value (val attr)
          name (name key)]
        (str name "=" "\"" value "\" ")))


(defn generate-tag [start-tag attr]
    (str "<" start-tag
        (if (> (count attr) 0)
            " ")
        (apply str (map generate-attr attr)) ">"))

(defn generate-end-tag [start-tag]
    (str "</" start-tag ">"))

(defn add-content [elements]
    (let [content (reduce str (filter string? elements))]
        (if-not (nil? content)
            (str content))))


(defn to-html [elements]
    (let [start-tag (first (filter keyword? elements))
          attr (first (filter map? elements))
          child (filter vector? elements)]
        (apply str (generate-tag (name start-tag) attr)
            (if (or (nil? child) (< (count child) 1) )
                (add-content elements)
                    (apply str (map #(to-html %) child)))
            (generate-end-tag (name start-tag)))))


;;(println (to-html [:html [:head {:style ".class"} [:b "hello world"] [:strong "hello world 2"]]]))

