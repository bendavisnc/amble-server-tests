(ns amble.config
  (:require
    [environ.core :as environ]))

(defmacro defenv
  [name]
  (let [config-value (environ/env (keyword name))]
    (assert (some? config-value)
            (format "Missing config, `%s`." name))
    `(def ~name ~config-value)))

(defenv server-url)

