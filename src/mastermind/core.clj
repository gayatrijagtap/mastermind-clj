(ns mastermind.core)

(def colors ["r" "g" "b" "y" "w" "p" "c" "a"])

(def random-colors (vec (take 5 (shuffle colors))))

(defn get-inorder-cols [l]
      (loop [ele-count 4 ordered 0]
            (if (< ele-count 0)
              ordered
              (if (= (random-colors ele-count) (l ele-count)) (recur (dec ele-count) (inc ordered)) (recur (dec ele-count) ordered)))))

(defn get-correct-guess [l]
      (count (filter (fn [x]
                         (contains? (set random-colors) x)) l)))

(defn game []
      (loop [attempts 0]
            (println random-colors)
            (println "attempt" attempts)
            (println "color options" colors)
            (println "Guess:")
            (let [user-guess (read-string (read-line)) in-order (get-inorder-cols user-guess) correct-guess (get-correct-guess user-guess)]
                 (println "correct guess:" correct-guess)
                 (println "in order:" in-order)
                 (if (> attempts 12) (println "maximum attempts!!!! Game over!!! your guess:" user-guess) (if (= user-guess random-colors) (println "correct guess!!!! you won!!!" user-guess) (recur (inc attempts)))))))

