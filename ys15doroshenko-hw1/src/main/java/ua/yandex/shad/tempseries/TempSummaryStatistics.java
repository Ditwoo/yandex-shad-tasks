package ua.yandex.shad.tempseries;

public class TempSummaryStatistics {

    private static final double EPSILON = 0.00000001;

    private final double avgTemp;
    private final double devTemp;
    private final double minTemp;
    private final double maxTemp;

    public TempSummaryStatistics() {
        avgTemp = 0.0;
        devTemp = 0.0;
        minTemp = 0.0;
        maxTemp = 0.0;
    }

    public TempSummaryStatistics(double a, double d, double min, double max) {
        avgTemp = a;
        devTemp = d;
        minTemp = min;
        maxTemp = max;
    }

    @Override
    public boolean equals(Object obj) {
        TempSummaryStatistics value = (TempSummaryStatistics) obj;

        if (Math.abs(this.avgTemp - value.avgTemp) > EPSILON) {
            return false;
        }
        if (Math.abs(this.devTemp - value.devTemp) > EPSILON) {
            return false;
        }
        if (Math.abs(this.minTemp - value.minTemp) > EPSILON) {
            return false;
        }
        if (Math.abs(this.maxTemp - value.maxTemp) > EPSILON) {
            return false;
        }

        return true;
    }
}
