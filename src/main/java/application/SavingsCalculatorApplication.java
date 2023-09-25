package application;

import application.domain.SavingsChart;
import application.domain.Sliders;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SavingsCalculatorApplication extends Application {

    
    @Override
    public void start(Stage window) throws Exception {
        BorderPane layout = getLayout();
        Scene scene = new Scene(layout, 800, 600);
        window.setTitle("Savings Calculator");
        window.setScene(scene);
        window.show();
    }
    
    private BorderPane getLayout() {
        BorderPane layout = new BorderPane();
        
        SavingsChart chart = new SavingsChart();
        Sliders sliders = new Sliders(chart);
        
        VBox slidersView = sliders.getSliders();
        LineChart lineChart = chart.getLineChart();
        
        layout.setTop(slidersView);
        layout.setCenter(lineChart);
        
        return layout;
    }

    public static void main(String[] args) {
        launch(SavingsCalculatorApplication.class);
    }
    
}
