package ua.yandex.shad.collections;

public class Tuple {
    public final String term;
    public final int weight;

    public Tuple(String term, int weight) {
        this.term = term;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        boolean flag = false;

        if (obj instanceof Tuple) {
            Tuple value = (Tuple) obj;
            flag = (term == value.term && weight == value.weight);
        }

        return flag;
    }
}
