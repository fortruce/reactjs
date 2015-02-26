(defproject reactjs "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :source-paths ["src/clj"]

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.2"]
                 [ring/ring-core "1.3.2"]]

  :plugins [[lein-ring "0.9.2"]]

  :ring {:handler reactjs.core/handler})
