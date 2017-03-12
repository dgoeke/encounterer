(ns encounterer.events
  (:require [re-frame.core :as re-frame :refer [reg-event-db reg-event-fx]]
            [encounterer.db :as db]
            [encounterer.firebase :as fb]
            [reagent.core :as r]))

(reg-event-fx :initialize-db
              (fn [cofx _]
                {:db db/default-db
                 :dispatch [:start-firebase]}))

(reg-event-db :set-route
              (fn [db [_ route]]
                (assoc db :route route)))

(reg-event-fx :start-firebase
              (fn [_ _]
                (fb/register-auth-events)
                nil))

(reg-event-db :auth-changed
              (fn [db [_ user-info]]
                (-> db
                    (assoc :user user-info)
                    (assoc :app-state :running))))

(reg-event-fx :trigger-login
              (fn [_ _]
                (fb/trigger-login)
                nil))

(reg-event-fx :trigger-logout
              (fn [_ _]
                (fb/trigger-logout)
                nil))
