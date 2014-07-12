(ns macrorama.test.core2
  (:use [macaron.core2])
  (:use clojure.test))

;(ns macrorama.test.core2
 ;;   (:use [macaron.core])
  ;;  (:require clojure.test))

;;(deftest replace-me ;; FIXME: write
;;    (is false "No tests have been written."))


;;(deftest simple-test
 ;;   (is (= 22 22)))

(def input [:html {:class ".html"} [:head {:class ".style"} "test"]])

(def output "<html class=\".html\" >
<head class=\".style\" >
</head></html>")

(deftest should
    (is (= (to-tag2 input) output)))


(run-tests)
