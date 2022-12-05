(ns task2
  (:require [clojure.test :as test]))

(defn eratosthenes_sieve [seq]
  (cons
    (first seq)
    (lazy-seq (eratosthenes_sieve(filter #(not= 0 (mod % (first seq)))(rest seq))))))
(def primes (eratosthenes_sieve (iterate inc 2)))


(test/deftest task2-test
  (test/testing "Testing eratosthenes_sieve"
    (test/is (= (nth primes 0) 2))
    (test/is (= (nth primes 1) 3))
    (test/is (= (nth primes 999) 7919))
    (test/is (= (nth primes 1999) 17389))
    (test/is (<= (time (nth primes 1999)) (time (nth primes 1999))))))


(test/run-tests)


