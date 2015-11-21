(ns project-tree.core-test
  (:require [clojure.test :refer :all]
            [project-tree.core :refer :all]))

(def complete-state (atom {:project "Test Project v1"
                           :features {"ft1"
                                      {1 {:head "Issue 1"
                                          :body ""
                                          :status "open"}
                                       2 {:head "Issue 2"
                                          :body ""
                                          :status "open"}}}}))
(def empty-state (atom {:project "Test Project v1"
                        :features {}
                        :issue-count 0}))

(deftest empty-tree-test
  (def state (create-project "Test Project v1"))
  (testing "Default Project Data Structure."
    (testing "Project key."
      (is (= (@state :project) (@empty-state :project))))
    (testing "Features key."
      (is (= (@state :features) (@empty-state :features))))
    (testing "Issue count key."
      (is (= (@state :issue-count) (@empty-state :issue-count))))))

(deftest inspect-tree-test
  (testing "Inspect all features."
    (is (= (feat-inspect complete-state) (@complete-state :features))))
  (testing "Inspect specific feature."
    (is (= (feat-inspect complete-state "ft1")
           (get-in @complete-state [:features "ft1"]))))
  (testing "Inspect issue of a feature."
    (is (= (feat-inspect complete-state "ft1" 1)
           (get-in @complete-state [:features "ft1" 1])))
    (testing "Inspect head."
      (is (= (feat-inspect complete-state "ft1" 1 :head)
             (get-in @complete-state [:features "ft1" 1 :head]))))
    (testing "Inspect body."
      (is (= (feat-inspect complete-state "ft1" 1 :body)
             (get-in @complete-state [:features "ft1" 1 :body]))))
    (testing "Inspect status."
      (is (= (feat-inspect complete-state "ft1" 1 :status)
             (get-in @complete-state [:features "ft1" 1 :status])))))
  (testing "Inspect non-existing element"
    (is (= (feat-inspect complete-state "ft0") "Not found"))))

(deftest update-state-test
  (let [my-state (create-project "My Project")]
    (testing "Update data in state."
      (is (= (assoc-state! my-state [:features "ft1"] {})))
      (is (= (inspect-feat my-state) {"ft1" {}})))
    (testing "Add/Update specific feature."
      (is (= (assoc-feat! my-state "ft1") @my-state))
      (is (= (assoc-feat! my-state "ft2" {1 {}}) @my-state))
      (is (= (assoc-feat! my-state "ft2" {1 {}, 2 {}}) @my-state)))
    (testing "Remove specific feature."
      (is (= (dissoc-feat! my-state "ft2") @my-state)))))
