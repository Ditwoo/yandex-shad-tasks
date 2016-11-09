package ua.yandex.fj;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.RecursiveTask;

/**
 * @author Dmitry Doroshenko.
 *         Yandex SHAD.
 */
public class WordCount {

    static class WordCountTask extends RecursiveTask<Map<String, Integer>> {
        private int l;
        private int r;
        private String[] s;
        private Map<String, Integer> hm = new HashMap<>();

        public WordCountTask(int l, int r, String[] s) {
            this.l = l;
            this.r = r;
            this.s = s;
        }

        private Map<String, Integer> put() {
            hm.put(s[l], 1);
            return hm;
        }

        private Map<String, Integer> merge(Map<String, Integer> a,
                                           Map<String, Integer> b) {
            Set<String> tmp = a.keySet();
            for (String k : tmp) {
                if (b.containsKey(k)) {
                    // a[k] += b[k] for set
                    a.put(k, a.get(k) + b.get(k));
                    b.remove(k);
                }
            }
            for (String k : b.keySet()) {
                a.put(k, b.get(k));
            }
            return a;
        }

        @Override
        protected Map<String, Integer> compute() {
            if (l == r) {
                return put();
            }

            int m = (l + r) / 2;
            WordCountTask left  = new WordCountTask(l, m, s);
            WordCountTask right = new WordCountTask(m + 1, r, s);

            right.fork();
            Map<String, Integer> leftMap  = left.compute();
            Map<String, Integer> rightMap = right.join();

            return merge(leftMap, rightMap);
        }
    }
}
