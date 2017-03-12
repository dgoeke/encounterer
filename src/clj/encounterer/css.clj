(ns encounterer.css
  (:require [garden.def :refer [defstyles]]))

(defstyles screen

  [:body {:background-color "white"
          :padding-top      "1em"}]

  [:a.toggle-dropdown
   [:img.profile-image.img-circle {:position "relative"
                                   :top "-5px"
                                   :float "left"
                                   :left "-5px"
                                   :height "35px"
                                   :padding-right "10px"}]])
