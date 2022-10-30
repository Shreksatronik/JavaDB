(defn cc
  [lst a]
  (filter (fn [x] (let [[f s] x] (not= f s)))
          (map (fn [x] (str a x)) lst)))

(defn make_comb
  [el1 el2]
  (reduce (fn [acc x] (concat acc (cc el1 x))) () el2))

(defn search
  [x l]
  (reduce (fn [acc y] (if (= x y) true
                                  (if (= acc false) false true))) false l))

(defn clean [alphabet] (reduce (fn [acc x] (if (= (search x acc) true) acc (cons x acc))) () alphabet))

(defn foo
  [alphabet N]
  (if (= N 0) ()
              (let [clean_alphabet (clean alphabet)]
                (reduce (fn [acc _] (make_comb acc clean_alphabet)) clean_alphabet (range (dec N))))))

(let [abs (list "a" "b" "c")
      N 2]
  (println (foo abs N)))