(ns encounterer.firebase
  (:require [firebase-cljs.core :as fb]
            [firebase-cljs.auth :as auth]
            [firebase-cljs.auth.provider :as provider]
            [firebase-cljs.user :as user]
            [re-frame.core :as re-frame]))

(defonce fb-app
  (fb/init {:apiKey            "AIzaSyCD8gZgTCTGJ5VnNuLLOIQYUYwDneb9nC0"
            :authDomain        "encounterer-5c528.firebaseapp.com"
            :databaseUrl       "https://encounterer-5c528.firebaseio.com"
            :storageBucket     "encounterer-5c528.appspot.com"
            :messagingSenderId "333116696378"}))

(defonce auth
  (fb/get-auth))

(defn on-auth-changed [user]
  (let [details (when-not (nil? user)
                  {:name   (user/name user)
                   :email  (user/email user)
                   :avatar (user/photo-url user)
                   :id     (user/uid user)})]
    (re-frame/dispatch [:auth-changed details])))

(defn register-auth-events []
  (auth/auth-changed auth on-auth-changed))

(defn trigger-login []
  (auth/login-redirect auth (provider/google)))

(defn trigger-logout []
  (auth/logout auth))
