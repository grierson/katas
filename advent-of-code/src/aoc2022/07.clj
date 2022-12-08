(ns aoc2022.07
  (:require
   [clojure.java.io :as io]
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

(defn handle-cd [system cmd]
  (case cmd
    "$ cd /" (assoc system :wd [])
    "$ cd .." (update system :wd #(if (empty? %)
                                    []
                                    (pop %)))

    (update system :wd conj (keyword (str (last cmd))))))

(defn handle-dir [{:keys [wd] :as system} cmd]
  (let [name (keyword (str (last cmd)))
        path (conj wd name)]
    (assoc-in system path {:files []})))

(defn handle-file [{:keys [wd] :as system} file]
  (let [path (conj wd :files)
        [size name] (str/split file #" ")
        filedata {:size (parse-long size)
                  :name name}]
    (update-in system path conj filedata)))

(defn run [system cmd]
  (cond
    (= "$ ls" cmd)  system
    (str/starts-with? cmd "$ cd") (handle-cd system cmd)
    (str/starts-with? cmd "dir") (handle-dir system cmd)
    :else (handle-file system cmd)))

(defn parse [cmds]
  (dissoc  (reduce run {} cmds) :wd))

(comment
  (parse (str/split-lines sample)))

(require 'hashp.core)

(defn total
  [fs]
  (let [dirs (keys (dissoc fs :files))
        dir-total (reduce
                   +
                   (reduce + 0 (map :size (:files fs)))
                   (map :total (map total (map #(% fs) dirs))))]
    (if (nil? keys)
      {:total dir-total}
      (apply merge
             {:total dir-total}
             (map (fn [dir] {dir (total (get fs dir))})
                  dirs)))))

(defn solve1 [{:keys [total]
               :or {total 0}
               :as fs}]
  (let [ks (keys (dissoc fs :total))]
    (reduce
     +
     (if (<= total 100000) total 0)
     (map #(solve1 (% fs)) ks))))

(comment
  (def sample-fs (parse (str/split-lines sample)))
  (def sample-total (total sample-fs))
  (solve1 (total sample-fs)))

(comment
  (def data (slurp (io/resource "aoc2022/07.txt")))
  (def data-fs (parse (str/split-lines data)))
  (def data-total (total data-fs))
  (solve1 data-total))
