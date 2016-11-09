package ua.yandex.sumofseries.utilconcurrent;

import ua.yandex.sumofseries.threads.SinCosThreads;

import java.util.concurrent.*;

/**
 * @author Dmitry Doroshenko.
 *         Yandex SHAD.
 */
public class SinCosConcurrent {
    private static final float STEP = (float) 1e-4;
    private static final float N = 1;

    private static class Calculator implements Callable<Double> {
        private final double start;
        private final double finish;

        private Double ans = null;

        public Calculator(double start, double finish) {
            this.start = start;
            this.finish = finish;
        }

        @Override
        public Double call() {
            ans = .0;
            for (double i = start; i <= finish + 1e-4; i += STEP) {
                ans += Math.sin(i) * Math.cos(i);
            }

            return ans;
        }

        public Double getAns() {
            return ans;
        }
    }

    public Double calculate(double n, int countOfThreads) {
        ExecutorService executorService = Executors.newFixedThreadPool(countOfThreads);
        Future<Double>[] future = new Future[countOfThreads];
        Calculator[] calculators = new Calculator[countOfThreads];

        double partition = n / countOfThreads;
        partition *= 2;

        calculators[0] = new Calculator(-n, -n + partition);
        for (int i = 1; i < countOfThreads; i++) {
            calculators[i] = new Calculator(-n + partition * i * STEP,
                    -n + partition * (i + 1));
        }

        for (int i = 0; i < countOfThreads; i++) {
            future[i] = executorService.submit(calculators[i]);

        }

        Double ans = .0;

        try {
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.DAYS);

            for (int i = 0; i < countOfThreads; i++) {
                ans += future[i].get();
            }
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return ans;
    }

    public static void main(String ... args) {
        SinCosConcurrent sinCosConcurrent = new SinCosConcurrent();

        System.out.println("SinCosConcurrent:");

        double ans = sinCosConcurrent.calculate(10, 20);
        System.out.println(ans);

        ans = sinCosConcurrent.calculate(1, 1);
        System.out.println(ans);

        ans = sinCosConcurrent.calculate(2, 2);
        System.out.println(ans);

        ans = sinCosConcurrent.calculate(10, 10);
        System.out.println(ans);

        ans = sinCosConcurrent.calculate(10, 20);
        System.out.println(ans);
    }
}
