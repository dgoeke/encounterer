(ns encounterer.views.lobby
  (:require [re-frame.core :as re-frame]))

(defn- game-list-panel [user]
  [:h2 "Game list"])

(defn- logged-in-view [user]
  [game-list-panel user])

(defn- logged-out-view []
  [:div
   [:h2 "Currently logged out."]
   [:p [:button {:on-click #(re-frame/dispatch [:trigger-login])} "Log in"]]])

(defn view []
  (let [user (re-frame/subscribe [:user])]
    (if (nil? @user)
      [logged-out-view]
      [logged-in-view @user])))
