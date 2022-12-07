(ns aoc2022.07
  (:require
   [clojure.string :as str]))

(def sample "$ cd /
$ ls
dir a
14848514 b.txt
8504156 c.dat
dir d
$ cd a
$ ls
dir e
29116 f
2557 g
62596 h.lst
$ cd e
$ ls
584 i
$ cd ..
$ cd ..
$ cd d
$ ls
4060174 j
8033020 d.log
5626152 d.ext
7214296 k")

;; {} : fs, [] : wd

;; $ cd / - clear wd
;; $ cd .. - pop wd
;; $ cd <dir> - conj wd <dir>

;; dir <dir> - assoc fs <dir> []
;; ls <dir> - ls = <dir> 
;; <#> <name> - update-in fs (conj wd ls) conj {:file <name> :size <#>}   

(defn handle-cd [{:keys [fs wd] :as system} cmd]
  (case cmd
    "$ cd /" (assoc system :wd [])
    "$ cd .." (update system :wd #(if (empty? %)
                                    []
                                    (pop %)))

    (update system :wd conj (keyword (str (last cmd))))))

(defn handle-dir [{:keys [wd] :as system} cmd]
  (let [name (keyword (str (last cmd)))
        path (into [:fs] wd)
        path (conj path name)]
    (assoc-in system path {})))

(defn handle-file [{:keys [fs wd] :as system} file]
  (let [path (into [:fs] wd)
        [size name] (str/split file #" ")
        filedata {:size (parse-long size)
                  :name name}]
    (if (contains? (get-in fs path) :files)
      (update-in system path conj filedata)
      (assoc-in system (conj path :files) [filedata]))))

(defn run [system cmd]
  (cond
    (= "$ ls" cmd)  system
    (str/starts-with? cmd "$ cd") (handle-cd system cmd)
    (str/starts-with? cmd "$ dir") (handle-dir system cmd)
    :else (handle-file system cmd)))
