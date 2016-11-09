package ua.yandex.shad.stream;

import ua.yandex.shad.function.IntBinaryOperator;
import ua.yandex.shad.function.IntConsumer;
import ua.yandex.shad.function.IntFunction;
import ua.yandex.shad.function.IntPredicate;
import ua.yandex.shad.function.IntToIntStreamFunction;
import ua.yandex.shad.function.IntUnaryOperator;
import ua.yandex.shad.vector.VectorArray;

public class AsIntStream implements IntStream {

    private VectorArray<Integer> values = new VectorArray<>();
    private VectorArray<IntFunction> operations = new VectorArray<>();

    private AsIntStream() {
    /*
             ∧＿∧
            ( ･ω･｡)つ━☆・*。
            ⊂　　 ノ 　　　・゜+.
            しーＪ　　　°。+ *´¨)
            　　　　　　　　　.· ´¸.·*´¨) ¸.·*¨)
            　　　　　　　　　　(¸.·´ (¸.·'*  Mysterious constructor ;)
     */
    }

    public static IntStream of(int... values) {
        AsIntStream stream = new AsIntStream();

        Integer[] integerValues = new Integer[values.length];
        for (int i = 0; i < values.length; i++) {
            integerValues[i] = values[i];
        }
        stream.values.add(integerValues);

        return stream;
    }

    @Override
    public Double average() {
        if (this.values.isEmpty()) {
            throw new IllegalArgumentException("Illegal capacity of values");
        }
        this.updateOperations();

        return (double) this.sum() / this.count();
    }

    @Override
    public Integer max() {
        if (this.values.isEmpty()) {
            throw new IllegalArgumentException("Illegal capacity of values");
        }
        this.updateOperations();

        return reduce(Integer.MIN_VALUE, (m, v) -> {
            // returns maximal value of (m, v)
            if (m > v) {
                return m;
            }
            else {
                return v;
            }
        });
    }

    @Override
    public Integer min() {
        if (this.values.isEmpty()) {
            throw new IllegalArgumentException("Illegal capacity of values");
        }
        this.updateOperations();

        return reduce(Integer.MAX_VALUE, (m, v) -> {
            // returns minimal value of (m, v)
            if (m < v) {
                return m;
            }
            else {
                return v;
            }
        });
    }

    @Override
    public long count() {
        this.updateOperations();
        return (long) this.values.size();
    }

    @Override
    public Integer sum() {
        if (this.values.isEmpty()) {
            throw new IllegalArgumentException("Illegal capacity of values");
        }
        this.updateOperations();
        return reduce(0, (sum, v) -> sum += v);
    }

    @Override
    public int[] toArray() {
        this.updateOperations();
        int[] ans = new int[this.values.size()];

        for (int i = 0; i < this.values.size(); i++) {
            ans[i] = this.values.get(i).intValue();
        }

        return ans;
    }

    @Override
    public void forEach(IntConsumer action) {
        this.updateOperations();
        for (int i = 0; i < this.values.size(); i++) {
            action.accept(this.values.get(i).intValue());
        }
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        this.operations.add(predicate);
        return this;
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        this.operations.add(mapper);
        return this;
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        this.operations.add(func);
        return this;
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        this.updateOperations();
        int ans = identity;
        for (int i = 0; i < this.values.size(); i++) {
            ans = op.apply(ans, this.values.get(i));
        }

        return ans;
    }

    private void usePredicate(IntPredicate predicate) {
        VectorArray<Integer> ans = new VectorArray<>();

        for (int i = 0; i < this.values.size(); i++) {
            if (predicate.test(this.values.get(i))) {
                ans.add(this.values.get(i));
            }
        }

        this.values = ans;
    }

    private void useUnaryOperator(IntUnaryOperator operator) {
        VectorArray<Integer> ans = new VectorArray<>();

        for (int i = 0; i < this.values.size(); i++) {
            ans.add(operator.apply(this.values.get(i)));
        }

        this.values = ans;
    }

    private void useStreamFunction(IntToIntStreamFunction function) {
        VectorArray<Integer> ans = new VectorArray<>();

        for (int i = 0; i < this.values.size(); i++) {
            AsIntStream nstr =
                    (AsIntStream) function.applyAsIntStream(
                            this.values.get(i));
            ans.add(nstr.values);
        }

        this.values = ans;
    }

    private void updateOperations() {
        for (int i = 0; i < this.operations.size(); i++) {
            if (this.operations.get(i) instanceof IntPredicate) {
                usePredicate((IntPredicate) this.operations.get(i));
            }
            if (this.operations.get(i) instanceof IntUnaryOperator) {
                useUnaryOperator((IntUnaryOperator) this.operations.get(i));
            }
            if (this.operations.get(i) instanceof IntToIntStreamFunction) {
                useStreamFunction(
                        (IntToIntStreamFunction) this.operations.get(i));
            }

        }
    }
}
