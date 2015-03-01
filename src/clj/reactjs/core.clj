(ns reactjs.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.util.response :refer [resource-response response]]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]))

(defonce comments (atom []))

(defn add-comment! [comment]
  (swap! comments conj comment))

(defroutes app
  (GET "/" [] (resource-response "index.html" {:root "public"}))
  (GET "/comments" [] (response @comments))
  (POST "/comments" req (do (add-comment! (:body req))
                            (response @comments)))
  (route/resources "/")
  (route/not-found "<h1>Page not found</h1>"))

(def handler
  (-> (handler/site app)
      (wrap-json-body {:keywords? true})
      wrap-json-response))
