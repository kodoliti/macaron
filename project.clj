(defproject macaron "1.0.0-SNAPSHOT"
  :description "Clojure-based template engine"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [ring/ring-core "1.1.1"]
                 [ring/ring-jetty-adapter "1.1.1"]]
  :plugins [[lein-ring "0.6.3"]]
  :ring {:handler macaron.hello/app})