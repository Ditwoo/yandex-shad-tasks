package ua.yandex.shad.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {

    private static final double MINIMAL_TEMPERATURE = -273.0;

    private double[] temperatures;

    public TemperatureSeriesAnalysis() {
        temperatures = new double[0];
    }

    public TemperatureSeriesAnalysis(double... temperatureSeries) {
        temperatures = temperatureSeries;
    }

    private boolean isLengthZero() {
        if (temperatures.length == 0) {
            return true;
        }
        return false;
    }

    public double average() {
        if (isLengthZero()) {
            throw new IllegalArgumentException();
        }

        double ans = 0;

        for (double element : temperatures) {
            ans += element;
        }

        return ans / temperatures.length;
    }

    public double deviation() {
        if (isLengthZero()) {
            throw new IllegalArgumentException();
        }

        double middle = this.average();
        double ans = 0;

        for (double element : temperatures) {
            ans += (element - middle) * (element - middle);
        }

        ans = ans / temperatures.length;
        ans = Math.sqrt(ans);

        return ans;
    }

    public double min() {
        if (isLengthZero()) {
            throw new IllegalArgumentException();
        }
        double minimalTemperature = temperatures[0];

        for (double element : temperatures) {
            if (element < minimalTemperature) {
                minimalTemperature = element;
            }
        }

        return minimalTemperature;
    }

    public double max() {
        if (isLengthZero()) {
            throw new IllegalArgumentException();
        }
        double maximumTemperature = temperatures[0];

        for (double element : temperatures) {
            if (element > maximumTemperature) {
                maximumTemperature = element;
            }
        }

        return maximumTemperature;
    }

    public double findTempClosestToZero() {
        if (isLengthZero()) {
            throw new IllegalArgumentException();
        }
        double closest = temperatures[0];

        for (double element : temperatures) {
            if (Math.abs(element) < Math.abs(closest)) {
                closest = element;
            }
            if (element == Math.abs(closest)
                    && element < 0) {
                closest = element;
            }
        }

        return closest;
    }

    public double findTempClosestToValue(double tempValue) {
        if (temperatures.length == 0) {
            throw new IllegalArgumentException();
        }
        double closest = temperatures[0];

        for (double element : temperatures) {
            if (Math.abs(tempValue - element) < Math.abs(tempValue - closest)) {
                closest = element;
            }
            if (element == Math.abs(closest)
                    && element < tempValue) {
                closest = element;
            }
        }

        return closest;
    }

    public double[] findTempsLessThen(double tempValue) {
        if (isLengthZero()) {
            throw new IllegalArgumentException();
        }
        int countOfLess = 0;

        for (double element : temperatures) {
            if (element < tempValue) {
                countOfLess++;
            }
        }

        double[] ans = new double[countOfLess];
        int iInLess = 0;
        for (int i = 0; i < temperatures.length; i++) {
            if (temperatures[i] < tempValue) {
                ans[iInLess] = temperatures[i];
                iInLess++;
            }
        }

        return ans;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        if (isLengthZero()) {
            throw new IllegalArgumentException();
        }
        int countOfGreater = 0;

        for (double element : temperatures) {
            if (element >= tempValue) {
                countOfGreater++;
            }
        }

        double[] ans = new double[countOfGreater];
        int iInGreater = 0;

        for (int i = 0; i < temperatures.length; i++) {
            if (temperatures[i] >= tempValue) {
                ans[iInGreater] = temperatures[i];
                iInGreater++;
            }
        }

        return ans;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (isLengthZero()) {
            throw new IllegalArgumentException();
        }

        TempSummaryStatistics ans = new TempSummaryStatistics(
            this.average(), this.deviation(), this.min(), this.max());

        return ans;
    }

    public int addTemps(double... temps) {
        int countOftemperatures = temperatures.length;
        int realCountOftemperatures = temperatures.length + temps.length;

        for (double value : temps) {
            if (value < MINIMAL_TEMPERATURE) {
                throw new InputMismatchException();
            }
        }

        if (countOftemperatures == 0) {
            countOftemperatures = 1;
        }

        while (countOftemperatures < realCountOftemperatures) {
            countOftemperatures *= 2;
        }

        double[] newTemperatures = new double[countOftemperatures];

        for (int i = 0; i < temperatures.length; i++) {
            newTemperatures[i] = temperatures[i];
        }

        for (int i = 0; i < temps.length; i++) {
            newTemperatures[i + temperatures.length] = temps[i];
        }
        temperatures = newTemperatures;

        return realCountOftemperatures;
    }
}
