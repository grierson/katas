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

(comment
  (reduce run
          {}
          (str/split-lines sample)))

(defn folder-size
  [{:keys [files] :as fs}]
  (if (empty? files)
    0
    (reduce +
            (reduce + (map :size files))
            (map #(folder-size (get fs %)) (keys (dissoc fs :files))))))

(comment
  (def fs (dissoc (reduce run {} (str/split-lines sample)) :wd))
  (folder-size fs))

(comment
  {:total 10
   :a {:total 5
       :b {:total 3}}})

(defn total
  [{:keys [files] :as fs}]
  (apply merge
         {:total (folder-size fs)}
         (map (fn [dir] {dir (total (get fs dir))}) (keys (dissoc fs :files)))))
