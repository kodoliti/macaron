(ns macaron.core2)

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

(comment (defn generate-attr [attr]
    (let [key (nth (map key {:class ".html"}) 0)
          name (name key)
          value (key attr)]
        (str name "=" "\"" value "\""))))

(defn generate-attr [attr]
    (let [keys (map key attr)]
        (doseq [k keys]
            (str (name k) "=" "\"" (k attr) "\""))))

(defn generate-attr2 [attr]
    (let [key (key attr)
          value (val attr)
          name (name key)]
        (str name "=" "\"" value "\" ")))


(defn generate-tag [start-tag attr]
    (str "<" start-tag
        (if (> (count attr) 0)
            " ")
        (apply str (map generate-attr2 attr)) ">"))

(defn add-end-tag [start-tag]
    (str "</" start-tag ">"))

(defn add-content [elements]
    (let [content (reduce str (filter string? elements))]
        (if-not (nil? content)
            (str content))))

(defn to-tag2 [elements]
    (let [start-tag (first (filter keyword? elements))
          attr (first (filter map? elements))
          child (first (filter vector? elements))]
        ;; (println (str " wynik = " start-tag "; " attr "; " child) )
        (str (generate-tag (name start-tag) attr)
            (if-not (nil? child)
                (if (vector? child)
                    (to-tag2 child))
                (add-content elements))
            (add-end-tag (name start-tag)))))

;;(defn generate-sub-element [elements func]
 ;;   (apply str (map #('to-tag3 %) elements)))


(defn to-tag3 [elements]
    (let [start-tag (first (filter keyword? elements))
          attr (first (filter map? elements))
          child (filter vector? elements)]
        ;;(println child)
        ;; (println (str " wynik = " start-tag "; " attr "; " child) )
        (str (generate-tag (name start-tag) attr)
            (if (or (nil? child) (< (count child) 1) )
                (add-content elements)
                    (apply str (map #(to-tag3 %) child)))
            (add-end-tag (name start-tag)))))


;;(println (to-tag2 [:html {:class ".html" :style ".xxx" :style2 ".xxx2"} [:head {:class ".style"} "test"]]))

(println (to-tag3 [:html [:head [:b "hello world"] [:strong "hello world 2"]]]))
