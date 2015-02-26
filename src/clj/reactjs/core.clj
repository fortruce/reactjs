(ns reactjs.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.util.response :refer [resource-response]]))

(defroutes app
  (GET "/" [] (resource-response "index.html" {:root "public"}))
  (route/resources "/")
  (route/not-found "<h1>Page not found</h1>"))

(def handler
  (handler/site app))
