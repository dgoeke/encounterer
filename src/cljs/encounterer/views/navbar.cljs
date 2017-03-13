(ns encounterer.views.navbar
  (:require [re-frame.core :as re-frame :refer [dispatch]]))

(defn route-li [route-name children]
  (let [route (re-frame/subscribe [:route])]
    (if (= @route route-name)
      [:li.active children]
      [:li children])))

(defn view []
  (let [user (re-frame/subscribe [:user])]
    [:nav.navbar.navbar-inverse.navbar-static-top
     [:div.container-fluid
      [:div.navbar-header
       [:button.navbar-toggle.collapsed {:role          "button"
                                         :data-toggle   "collapse"
                                         :target        "#navbar"
                                         :aria-expanded "false"
                                         :aria-controls "navbar"}
        [:span.sr-only "Toggle navigation"]
        [:span.icon-bar]
        [:span.icon-bar]
        [:span.icon-bar]]
       [:a.navbar-brand {:href "#"} "Encounterer"]]
      [:div#navbar.navbar-collapse.collapse
       [:ul.nav.navbar-nav
        [route-li :lobby [:a {:href "#/"} "Lobby"]]
        [route-li :about [:a {:href "#/about"} "About"]]]
       [:ul.nav.navbar-nav.navbar-right
        (if (nil? @user)
          [:li.navbar-form
           [:button.btn.btn-success {:on-click #(dispatch [:trigger-login])
                                     :href "#"} "Sign in"]]
          [:li.dropdown
           [:a.toggle-dropdown {:data-toggle   "dropdown"
                                :role          "button"
                                :aria-haspopup "true"
                                :aria-expanded "false"
                                :href          "#"}
            [:img.profile-image.img-circle {:src (:avatar @user)}]
            [:span (:name @user)] [:span.caret]]
           [:ul.dropdown-menu
            [:li [:a {:href "#" } "Settings"]]
            [:li [:a {:href "#"
                      :on-click #(dispatch [:trigger-logout])}
                  "Log out"]]]])]]]]))
