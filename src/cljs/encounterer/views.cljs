(ns encounterer.views
  (:require [re-frame.core :as re-frame]
            [encounterer.views.lobby :as lobby]
            [encounterer.views.about :as about]
            [encounterer.views.navbar :as navbar]))

(defn route->view [route]
  (case route
    :lobby [lobby/view]
    :about [about/view]
    [:div "Unknown route!"]))

(defn current-route-view []
  (let [route (re-frame/subscribe [:route])]
    [:div
     [navbar/view]
     (route->view @route)]))

(defn loading-view []
  [:div "Loading and checking authentication..."])

(defn main-app-window []
  (let [state (re-frame/subscribe [:app-state])]
    [:div.container
     (if (= @state :loading)
       [loading-view]
       [current-route-view])]))
