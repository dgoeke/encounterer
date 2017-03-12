(ns encounterer.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [re-frisk.core :refer [enable-re-frisk!]]
              [encounterer.events]
              [encounterer.subs]
              [encounterer.routes :as routes]
              [encounterer.views :as views]
              [encounterer.config :as config]
              encounterer.firebase
              cljsjs.bootstrap))

(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (enable-re-frisk!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-app-window]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (routes/app-routes)
  (dev-setup)
  (re-frame/dispatch-sync [:initialize-db])
  (mount-root))
