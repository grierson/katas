(defproject tennis "0.0.1-SNAPSHOT"
  :description "Cool new project to do things and stuff"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/core.match "0.3.0"]]
  :aliases {"kaocha" ["with-profile" "+kaocha" "run" "-m" "kaocha.runner"]}
  :profiles {:dev {:dependencies [[midje "1.9.9"]]}
             :kaocha {:dependencies [[lambdaisland/kaocha "0.0-554"]
                                     [lambdaisland/kaocha-midje "0.0-5"]]}
             ;; You can add dependencies that apply to `lein midje` below.
             ;; An example would be changing the logging destination for test runs.
             :midje {}})
             ;; Note that Midje itself is in the `dev` profile to support
             ;; running autotest in the repl.

  
