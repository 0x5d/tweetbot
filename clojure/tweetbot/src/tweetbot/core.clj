(ns tweetbot.core
  (:gen-class)
  (:use
   [twitter.oauth]
   [twitter.api.restful])
  (:require
   [yaml.core :as yaml]))

(defn load-config
  []
  (yaml/from-file (str (System/getenv "HOME") (java.io.File/separator) ".tweetbot")))

(defn get-creds
  []
  (let [config (load-config)]
    (make-oauth-creds (get config "consumer-key")
                      (get config "consumer-secret")
                      (get config "access-token")
                      (get config "access-token-secret"))))

(defn tweet
  [message, creds]
  (statuses-update :oauth-creds creds
                   :params {:status message}))

(defn -main
  "Posts a status update to twitter"
  [& args]
  (if-let [first-arg (first args)]
    (tweet first-arg (get-creds))
    (println "Sorry, something happened")))
