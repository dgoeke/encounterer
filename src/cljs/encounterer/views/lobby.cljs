(ns encounterer.views.lobby
  (:require [re-frame.core :as re-frame]))

(def initiative-data
  [{:name "David" :init 23 :player? true}
   {:name "Laura" :init 21 :active true :player? true}
   {:name "Giddeon" :init 19 :player? true}
   {:name "Devon" :init 14 :player? true}
   {:name "Enemy 1" :init 13}
   {:name "Enemy 2" :init 10}
   {:name "Big Boss" :init 3}])

(def engagements-data
  {:unengaged [{:name "Giddeon has a very long name" :player? true}{:name "Someone"}]
   :engagements [[{:name "David" :player? true}{:name "Enemy 1"}]
                 [{:name "Laura" :player? true}{:name "Devon" :player? true}{:name "Big Boss"}{:name "Enemy 2"}]
                 [{:name "Giddeon" :player? true}]]})

(defn- player-li [p]
  (let [name    (:name p)
        player? (:player? p)
        tag     (if player? :div.mob.player :div.mob.npc)]
    [tag {:key name} #_[:span.name] name]))

(defn- list-unengaged [players]
  (when (pos? (count players))
    [:div.unengaged
     [:div.engagement
      (map player-li players)]]))

(defn- show-engagement [key players]
  [:div.engagement {:key key}
   (map player-li players)])

(defn- list-engagements [engagements]
  (when (pos? (count engagements))
    [:div.engagements
     (map-indexed show-engagement engagements)]))

(defn- engagements-view []
  (let [{:keys [unengaged engagements]} engagements-data]
    [:div
     [list-engagements engagements]
     [list-unengaged unengaged]]))

(defn- initiative-list []
  [:tbody
   (for [{:keys [name init active]} initiative-data]
     (let [props {:key (str name init)}
           tag (if active :tr.success :tr)]
       [tag props
        [:td name]
        [:th {:scope "row"} init]]))
   [:tr.warning
    [:td "Escalation"]
    [:td 3]]])

(defn- initiative-view []
  [:div
   [:table.table.table-hover.table-condensed
    [initiative-list]]])

(defn- event-log-view []
  [:div.event-log
   [:div.event.dmg [:span.player "Laura"] [:span " hit "] [:span.npc "Enemy 1"] [:span " for "] [:span.damage "10"]]
   [:div.event.turn "Laura's turn!"]
   [:div.event.dmg [:span.player "Giddeon"] [:span " hit "] [:span.npc "Enemy 2"] [:span " for "] [:span.damage "10"]]
   [:div.event.engage [:span.player "Giddeon"] [:span " is engaged with "] [:span.player "Enemy 2"]]
   [:div.event.turn [:span "Giddeon's turn!"]]
   [:div.event.engage [:span.player "David"] [:span " is unengaged"]]
   [:div.event.dmg [:span.player "David"] [:span " hit "] [:span.npc "Big Boss"] [:span " for "] [:span.damage "10"]]
   [:div.event.turn "David's turn!"]])

(defn- game-list-panel [user]
  [:div.row
   [:div.col-md-2 [initiative-view]]
   [:div.col-md-7 [engagements-view]]
   [:div.col-md-3 [event-log-view]]])

(defn- logged-in-view [user]
  [game-list-panel user])

(defn- logged-out-view []
  [:h2 "Login to view games."])

(defn view []
  (let [user (re-frame/subscribe [:user])]
    (if (nil? @user)
      [logged-out-view]
      [logged-in-view @user])))
