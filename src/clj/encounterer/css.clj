(ns encounterer.css
  (:refer-clojure :exclude [rem])
  (:require [garden.def :refer [defstyles]]
            [garden.units :refer :all]))

(def player-height 80)

(def player-width (* player-height 1.618))

(defstyles screen

  [:body {:background-color "white"}]

  [:.center {:text-align "center"}]

  [:a.toggle-dropdown
   [:img.profile-image.img-circle {:position      "relative"
                                   :top           "-5px"
                                   :float         "left"
                                   :left          "-5px"
                                   :height        "35px"
                                   :padding-right "10px"}]]

  [:.engagements :.unengaged {:vertical-align "top"
                              :margin         "0 auto"
                              :text-align     "center"}]

  [:.engagement {:background     "#f5f5f5"
                 :padding        "0.3em"
                 :margin         "1em"
                 :border-radius  "5px"
                 :vertical-align "top"
                 :display        "inline-block"}

   [:.player :.npc {:list-style      "none"
                    :margin          "0.1em"
                    :margin-bottom   "0.3em"
                    :padding         "0.1em"
                    :border-radius   "5px"
                    :display         "flex"
                    :align-items     "center"
                    :justify-content "center"
                    :text-align      "center"
                    :width           (px player-width)
                    :height          (px player-height)}]
   [:.player {:background-color "#a0f5a0"}]
   [:.npc {:background-color "#f5a0a0"}]]

  [:.unengaged {:width "100%"}
   [:.engagement {:background "white"
                  :display    "inline-flex"
                  :box-shadow "none"}
    [:.player :.npc {:margin     "1em"
                     :box-shadow "2px 2px 3px #ddd"
                     :color      "#777"}]
    [:.player {:background-color "#d0ffd0"}]
    [:.npc {:background-color "#ffd0d0"}]]]

  [:.event-log
   [:.event {:width            "100%"
             :background-color "#f5f5f5"
             :padding          "0.5em"
             :border-radius    "5px"
             :margin-bottom    "5px"}
    [:.player {:font-weight "bold"}]]

   [:.turn {:background-color "white"
            :color            "#999"
            :font-style       "italic"
            :font-weight      "bold"
            :text-align       "center"
            :border-radius    0}]
   [:.dmg {:background-color "#f5e0e0"}]
   [:.engage {:background-color "#f9f9f9"}]])
