(ns encounterer.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame :refer [reg-sub]]))

(reg-sub :route
         (fn [db _]
           (:route db)))

(reg-sub :user
         (fn [db _]
           (:user db)))

(reg-sub :app-state
         (fn [db _]
           (:app-state db)))
