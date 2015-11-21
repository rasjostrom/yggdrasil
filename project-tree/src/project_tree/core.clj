(ns project-tree.core)

(defn create-project 
  "Returns an empty project atom with a name."
  [title]
  (atom {:project title :features {} :issue-count 0}))

;; NOTE: Can this be done easier with select-keys?
(defn inspect-feat
  "Explores the project features map in specific depths."
  ([state]
   (@state :features))

  ([state & depth]
   (get-in @state (vec (cons :features depth)) "Not found")))

(defn assoc-state!
  "Associates new data with a given project-state.
  Data must consist of a key-value pair.

  Used by more specific function calls like
  'assoc-feat!' and 'set-title'. Do not use this."
  [state depth data]
  (swap! state assoc-in depth data))

(defn assoc-feat!
  "Associates a new feature to a given project-state.
  Overwrites existing data if present."
  [state feature]
  (assoc-state! state [:features feature] {}))

(defn careful-assoc-feat!
  "Associates a new feature to a given project-state,
  only if it can do so without overwriting data."
  ([state feature]
   (if (= false (contains? (@state :features) feature))
     (assoc-feat! state feature)
     (str "Feature already exists"))))


(defn dissoc-feat!
  "Dis-associates a feature from a given project-stat.
  Asserts feature existence in state."
  [state feature]
  {:pre [(contains? (@state :features) feature)]}
  (swap! state assoc :features (dissoc (@state :features) feature)))

(defn -main []
  (def skeleton (create-project "Test-Project"))
  (println @skeleton))
