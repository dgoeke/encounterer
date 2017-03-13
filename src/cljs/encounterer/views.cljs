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
     [:div.container-fluid
      (route->view @route)]]))

(defn loading-view []
  [:div "Loading and checking authentication..."])

(defn main-app-window []
  (let [state (re-frame/subscribe [:app-state])]
    (if (= @state :loading)
      [loading-view]
      [current-route-view])))
