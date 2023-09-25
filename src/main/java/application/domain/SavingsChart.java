
package application.domain;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class SavingsChart {
    
    private final LineChart lineChart;
    private final XYChart.Series monthlySavings;
    private final XYChart.Series interestRate;
    
    public SavingsChart() {
        this.lineChart = createLineChart();
        this.monthlySavings = new XYChart.Series();
        this.interestRate = new XYChart.Series();
        
        this.lineChart.getData().add(this.monthlySavings);
        this.lineChart.getData().add(this.interestRate);
    }
    
    private LineChart createLineChart() {  
        NumberAxis xAxis = new NumberAxis(0, 30, 1);
        NumberAxis yAxis = new NumberAxis(0, 125000, 25000);
        xAxis.setLabel("Year");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Savings calculator");
        lineChart.setLegendVisible(false);
        lineChart.setAnimated(false);
        
        return lineChart;
    }

    public void addSavingsValues(double amount) {
        int year = 0;
        double sum = 0;
        this.monthlySavings.getData().add(new XYChart.Data(year, sum));
        
        for (year = 1; year <= 30; year++) {
            sum += amount * 12;
            this.monthlySavings.getData().add(new XYChart.Data(year, sum));
        }     
    }
    
    public void addInterestValues(double amount, double rate) {
        int year = 0;
        double saved = 0;
        this.interestRate.getData().add(new XYChart.Data(year, saved));
        
        for (year = 1; year <= 30; year++) {
            saved += amount * 12;
            saved *= 1 + (rate/100);
            this.interestRate.getData().add(new XYChart.Data(year, saved));
        }
    }
    
    public void clearMonthlySavings() {
        this.monthlySavings.getData().clear();
    }
    
    public void clearInterest() {
        this.interestRate.getData().clear();
    }
    
    public LineChart getLineChart() {
        return this.lineChart;
    }
    
}
