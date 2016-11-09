package ua.yandex.mergesort;

import java.util.Arrays;
import java.util.concurrent.*;

/**
 * @author Dmitry Doroshenko.
 *         Yandex SHAD.
 */
public class MergeSort {

    private final ExecutorService executorService;

    private class Sorter implements Runnable {
        private final int[] array;
        private final int numberOfThreads;

        // duplicating array
        Sorter(int[] array, int numberOfThreads) {
            this.array = array;
            this.numberOfThreads = numberOfThreads;
        }

        @Override
        public void run() {
            sort(array, numberOfThreads);
        }
    }

    public MergeSort() {
        executorService = Executors.newCachedThreadPool();
    }

    public void merge(int[] a, int[] b, int[] ans) {
        int i = 0;
        int j = 0;

        for (int k = 0; k < ans.length; k++) {
            if (j >= b.length || (i < a.length && a[i] < b[j])) {
                ans[k] = a[i];
                i++;
            }
            else {
                ans[k] = b[j];
                j++;
            }
        }
    }

    public void sort(int[] array) {
        if (array.length == 1) {
            return;
        }

        int m = array.length / 2;

        int[] a = Arrays.copyOfRange(array, 0, m);
        int[] b = Arrays.copyOfRange(array, m, array.length);

        sort(a);
        sort(b);

        merge(a, b, array);
    }

    public void sort(int[] array, int numberOfThreads) {
        if (numberOfThreads == 1) {
            sort(array);
        }
        if (array.length == 1) {
            return;
        }

        int m = array.length / 2;
        int[] a = Arrays.copyOfRange(array, 0, m);
        int[] b = Arrays.copyOfRange(array, m, array.length);

        Thread aThread = new Thread(new Sorter(a, (numberOfThreads - 1) / 2));
        aThread.start();

        Thread bThread = new Thread(new Sorter(b, (numberOfThreads - 1) / 2));
        bThread.start();

        try {
            aThread.join();
            bThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        merge(a, b, array);
    }

    public static void main(String ... args) {
        int[] array = {1, 666, 14, 2361, 3, 88, 9, 56, 31};
        MergeSort mergeSort = new MergeSort();

        System.out.println("MergeSort:");

        mergeSort.sort(array, 4);
        boolean flag = true;

        System.out.print(array[0]);
        for (int i = 1; i < array.length; i++) {
            System.out.print(" " + array[i]);
            if (array[i] > array[i - 1]) {
                flag = false;
                break;
            }
        }

        System.out.println("\nIs sorted - " + flag);
    }
}
