package ua.yandex.sumofseries.threads;

/**
 * @author Dmitry Doroshenko.
 *         Yandex SHAD.
 */
public class SinCosThreads {
    private static final double STEP = 1e-4;
    private static final float N = 1;

    private static class Calculator implements Runnable {
        private final double start;
        private final double finish;

        private double ans;

        public Calculator(double start, double finish) {
            this.start = start;
            this.finish = finish;
        }

        @Override
        public void run() {
            ans = .0;
            for (double i = start; i <= finish + 1e-4; i += STEP) {
                ans += Math.sin(i) * Math.cos(i);
            }
        }

        public double getAns() {
            return ans;
        }
    }

    public double calculate(double n, int countOfThreads) {
        Thread[] threads = new Thread[countOfThreads - 1];
        Calculator[] calculators = new Calculator[countOfThreads - 1];

        double step = 2 * n / countOfThreads;

        for (int i = 0; i < countOfThreads - 1; i++) {
            calculators[i] = new Calculator(- n + (i + 1) * step, - n + (i + 2) * step);
            threads[i] = new Thread(calculators[i]);
        }

        double ans;
        Calculator mainCalc = new Calculator(- N, - N + step);
        mainCalc.run();
        ans = mainCalc.getAns();

        for (Thread thread : threads) {
            thread.start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }


        for (Calculator calculator : calculators) {
            ans += calculator.getAns();
        }

        return ans;
    }

    public static void main(String ... args){
        SinCosThreads sinCosThreads = new SinCosThreads();

        System.out.println("SinCosThreads:");

        double ans = sinCosThreads.calculate(10, 20);
        System.out.println(ans);

        ans = sinCosThreads.calculate(1, 1);
        System.out.println(ans);

        ans = sinCosThreads.calculate(2, 2);
        System.out.println(ans);

        ans = sinCosThreads.calculate(10, 10);
        System.out.println(ans);

        ans = sinCosThreads.calculate(10, 20);
        System.out.println(ans);
    }

}
