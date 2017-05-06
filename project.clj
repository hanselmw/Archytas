(defproject archytas "0.1.0-SNAPSHOT"
  :description "A Standard Firmata (http://firmata.org/) client with some extra features."
  :url "https://github.com/hanselmw/archytas"
  :license {:name "GNU General Public License v3.0"
            :url "https://www.gnu.org/licenses/gpl-3.0.en.html"}

  :jar-exclusions [#"\.cljx"]

  :source-paths ["src/clj" "src/cljx"]

  :test-paths ["target/test-classes"]

  :resource-paths ["src/cljs"]

  :jvm-opts ["-Xmx384m"]

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.521"]
                 [org.clojure/core.async "0.3.442"]
                 [clj-serial "2.0.4-SNAPSHOT"]]

  :scm {:name "git"
        :url "https://github.com/hanselmw/archytas"}

  :profiles {:dev {:plugins [[com.keminglabs/cljx "0.6.0"]
                             [lein-cloverage "1.0.2"]
                             [lein-cljsbuild "1.1.6"]
                             [com.cemerick/clojurescript.test "0.3.3"]
                             [lein-npm "0.6.2"]
                             [lein-codox "0.10.3"]]

                   :aliases {"cleantest" ["do" "clean," "cljx" "once," "test,"
                                          "npm" "install," "cljsbuild" "test"]
                             "cljstest" ["do" "cljx" "once," "cljsbuild" "test"]}}}

  :prep-tasks [["cljx" "once"]]

  :node-dependencies [[serialport "1.7.4"]] ;;4.0.7
  :npm-root "target"

  :codox {:defaults {:doc/format :markdown}
          :sources  ["src/clj" "target/classes"]
          :exclude [archytas.stream.impl]
          :output-dir "target/doc"}

  :cljx {:builds [{:source-paths ["src/cljx"]
                 :output-path "target/classes"
                 :rules :clj}

                {:source-paths ["src/cljx"]
                 :output-path "target/classes"
                 :rules :cljs}

                {:source-paths ["test/cljx"]
                 :output-path "target/test-classes"
                 :rules :clj}

                {:source-paths ["test/cljx"]
                 :output-path "target/test-classes"
                 :rules :cljs}]}

  :cljsbuild {:builds [{:id "test"
                        :source-paths ["src/cljs" "test/cljs" "target/classes" "target/test-classes"]
                        :compiler {:output-to   "target/testable.js"
                                   :output-dir  "target/test-js"
                                   :target :nodejs
                                   :optimizations :simple
                                   :hashbang false}}]
              :test-commands {"unit-tests" ["node" :node-runner
                                            "target/testable.js"]}})
