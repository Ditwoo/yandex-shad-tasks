package ua.yandex.shad.tempseries;

import java.util.InputMismatchException;

import static org.junit.Assert.*;

import org.junit.Test;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void TestAverageOnEasyArray() {
        double[] temperatureSeries = { -6.0, 7.0, 6.0, -7.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.0;
        double actualResult = seriesAnalysis.average();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void TestAverageOnOneElementArray() {
        double[] temperatureSeries = { 99.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 99.0;
        double actualResult = seriesAnalysis.average();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestAverageFailOnEmptyArray() {
        double[] emptyArray = {};

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(emptyArray);
        double actualValue = seriesAnalysis.average();
    }

    @Test
    public void TestDeviationOnEasyArray() {
        double[] temperatureSeries = {28.0, 17.0, 23.0, 21.0, 12.0, 9.0, 13.0, 16.0, 29.0, 32.0};
        double expResult = 7.46993975;

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void TestDeviationOnOneElementArray() {
        double[] temperatureSeries = {78.0};
        double expResult = 0.0;

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestDeviationFailOnEmptyArray() {
        double[] emptyArray = {};

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(emptyArray);
        double actualValue = seriesAnalysis.deviation();
    }

    @Test
    public void TestMinOnEasyArray() {
        double[] temperatureSeries = {1.0, 3.0, 5.0, -4.0, 100.0, -3.9};
        double expResult = -4.0;

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.min();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void TestMinOnOneElementArray() {
        double[] temperatureSeries = {56.7};
        double expResult = 56.7;

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.min();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestMinFailOnEmptyArray() {
        double[] emptyArray = {};

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(emptyArray);
        double actualValue = seriesAnalysis.min();
    }

    @Test
    public void TestMaxOnEasyArray() {
        double[] temperatureSeries = { -9.0, 10.0, -18.0, 56.0};
        double expResult = 56.0;

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.max();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void TestMaxOnOneElementArray() {
        double[] temperatureSeries = { -9.0};
        double expResult = -9.0;

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.max();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestMaxFailOnEmptyArray() {
        double[] emptyArray = {};

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(emptyArray);
        double actualValue = seriesAnalysis.max();
    }

    @Test
    public void TestFindTempClosestToZeroOnEasyArray() {
        double[] temperatureSeries = {100.0, 99.0, 98.0, -75.0, 56.0};

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 56.0;
        double actualResult = seriesAnalysis.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void TestFindTempClosestToZeroOnOneElementArray() {
        double[] temperatureSeries = { -65.0};

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -65.0;
        double actualResult = seriesAnalysis.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void TestFindTempClosestToZeroOnNegativeAndPositiveClosestValue() {
        double[] temperatureSeries = {100.0, 0.45, 67.0, -5.0, -0.45};

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.45;
        double actualResult = seriesAnalysis.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestFindTempClosestToZeroOnEmptyArray() {
        double[] emptyArray = {};

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(emptyArray);
        double actualValue = seriesAnalysis.findTempClosestToZero();
    }

    @Test
    public void TestFindTempClosestToValueOnEasyArray() {
        double[] temperatureSeries = {100.0, 99.0, 98.0, -75.0, 56.0};

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 100.0;
        double actualResult = seriesAnalysis.findTempClosestToValue(110.0);

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void TestFindTempClosestToValueOnOneElementArray() {
        double[] temperatureSeries = {88.56};

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 88.56;
        double actualResult = seriesAnalysis.findTempClosestToValue(45.8);

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void TestFindTempClosestToValueOnNegativeAndPositiveClosestValue() {
        double[] temperatureSeries = {100.0, 75.5, 99.0, 98.0, -75.5, 56.0};

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 75.5;
        double actualResult = seriesAnalysis.findTempClosestToValue(74.0);

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestFindTempClosestToValueOnEmptyArray() {
        double[] emptyArray = {};

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(emptyArray);
        double actualValue = seriesAnalysis.findTempClosestToValue(19.0);
    }

    @Test
    public void TestFindTempsLessThenOnEasyArray() {
        double[] temperatureSeries = {100.0, 99.0, 88.0, -66.0, -43.1, 0.0, 12.0};

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = { -66.0, -43.1};
        double[] actualResult = seriesAnalysis.findTempsLessThen(-10.0);

        assertEquals(expResult.length, actualResult.length);
        assertArrayEquals(expResult, actualResult, 0.0001);
    }

    @Test
    public void TestFindTempsLessThenOnEasyArrayAndTooLowValue() {
        double[] temperatureSeries = {100.0, 99.0, 88.0, -66.0, -43.1, 0.0, 12.0};

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {};
        double[] actualResult = seriesAnalysis.findTempsLessThen(-70.0);

        assertEquals(expResult.length, actualResult.length);
        assertArrayEquals(expResult, actualResult, 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestFindTempsLessThenOnEmptyArray() {
        double[] emptyArray = {};

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(emptyArray);
        double[] actualValue = seriesAnalysis.findTempsLessThen(0.0);
    }

    @Test
    public void TestFindTempsGreaterThenOnEasyArray() {
        double[] temperatureSeries = {100.0, 99.0, 88.0, -66.0, -43.1, 0.0, 12.0, 77.0};

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {100.0, 99.0, 88.0, 77.0};
        double[] actualResult = seriesAnalysis.findTempsGreaterThen(65.0);

        assertEquals(expResult.length, actualResult.length);
        assertArrayEquals(expResult, actualResult, 0.0001);
    }

    @Test
    public void TestFindTempsGreaterThenOnEasyArrayAndTooHighValue() {
        double[] temperatureSeries = {100.0, 99.0, 88.0, -66.0, -43.1, 0.0, 12.0, 77.0};

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {};
        double[] actualResult = seriesAnalysis.findTempsGreaterThen(130.0);

        assertEquals(expResult.length, actualResult.length);
        assertArrayEquals(expResult, actualResult, 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestFindTempsGreaterThenOnEmptyArray() {
        double[] emptyArray = {};

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(emptyArray);
        double[] actualValue = seriesAnalysis.findTempsGreaterThen(0.0);
    }

    @Test
    public void TestSummaryStatisticsOnEasyArray() {
        double[] temperatureSeries = {28.0, 17.0, 23.0, 21.0, 12.0, 9.0, 13.0, 16.0, 29.0, 32.0};
        double expAvg = 20.0;
        double expDev = 7.46993975;
        double expMin = 9.0;
        double expMax = 32.0;
        TempSummaryStatistics expResult = new TempSummaryStatistics(expAvg, expDev, expMin, expMax);

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        TempSummaryStatistics actualValue = seriesAnalysis.summaryStatistics();

        assertEquals(expResult, actualValue);
    }

    @Test
    public void TestSummaryStatisticsOnAllOne() {
        double[] temperatureSeries = {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
        double expAvg = 1.0;
        double expDev = 0;
        double expMin = 1.0;
        double expMax = 1.0;
        TempSummaryStatistics expResult = new TempSummaryStatistics(expAvg, expDev, expMin, expMax);

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        TempSummaryStatistics actualValue = seriesAnalysis.summaryStatistics();

        assertEquals(expResult, actualValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestSummaryStatisticsOnEmptyArray() {
        double[] emptyArray = {};

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(emptyArray);
        TempSummaryStatistics actualValue = seriesAnalysis.summaryStatistics();
    }

    @Test
    public void TestAddTempsOnEasyArray() {
        double[] temperatureSeries = {1.0, 2.0, 3.0};
        double[] inputSeries = {4.0, 5.0};
        int expResult = 5;

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        int actualValue = seriesAnalysis.addTemps(inputSeries);

        assertEquals(expResult, actualValue);
    }

    @Test
    public void TestAddTempsOnInputEmptyArray() {
        double[] temperatureSeries = {1.0, 2.0, 45.6, 34.0, 0.0, 32.9, 78.5};
        double[] inputSeries = {};
        int expResult = 7;

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        int actualValue = seriesAnalysis.addTemps(inputSeries);

        assertEquals(expResult, actualValue);
    }

    @Test(expected = InputMismatchException.class)
    public void TestAddTempsOnInputMismatchException() {
        double[] temperatureSeries = {1.0, 2.0, 3.0};
        double[] inputSeries = {4.0, -274.0};

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        int actualValue = seriesAnalysis.addTemps(inputSeries);
    }
}
