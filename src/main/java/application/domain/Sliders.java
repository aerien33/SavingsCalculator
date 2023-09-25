
package application.domain;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Sliders {
    
    private final Parameters parameters;
    private final SavingsChart chart;
    
    public Sliders(SavingsChart chart) {
        this.parameters = new Parameters();
        this.chart = chart;
    }
    
    public VBox getSliders() {
        VBox layout = new VBox();
        
        BorderPane first = getFirstSlider();
        BorderPane second = getSecondSlider();
        
        layout.getChildren().add(first);
        layout.getChildren().add(second);
        setFormatting(layout);
 
        return layout;
    }
    
    private BorderPane getFirstSlider() {
        BorderPane layout = new BorderPane();
        
        Slider firstSlider = new Slider(25, 250, 25);
        firstSlider.setSnapToTicks(true);
        setFormatting(firstSlider, 25, 4);
        
        Label firstValue = new Label(
                Double.toString(firstSlider.getValue()));

        setAmount(firstSlider.getValue());
        getChart().addSavingsValues(getAmount());
        
        firstSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> change,
                Number oldValue, Number newValue) {
                    setAmount(newValue.doubleValue());
                    firstValue.setText(String.format("%.1f", getAmount()));
                    
                    getChart().clearMonthlySavings();
                    getChart().clearInterest();
                    
                    getChart().addSavingsValues(getAmount());   
                    
                    if (getRate() != 0) {
                        getChart().addInterestValues(getAmount(), getRate());
                    }
            }
        });
        
        layout.setLeft(new Label("Monthly savings"));
        layout.setCenter(firstSlider);
        layout.setRight(firstValue);
        setFormatting(layout);
        
        return layout;
    }
    
    private BorderPane getSecondSlider() {
        BorderPane layout = new BorderPane();
        
        Slider secondSlider = new Slider(0, 10, 0);
        setFormatting(secondSlider, 10, 9);

        Label secondValue = new Label(
            Double.toString(secondSlider.getValue()));
        
        setRate(secondSlider.getValue());
        
        if (getRate() != 0) {
            getChart().addInterestValues(getAmount(), getRate());
        }  
        
        secondSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> change,
                Number oldValue, Number newValue) {
                    setRate(newValue.doubleValue());
                    secondValue.setText(String.format("%.1f", getRate()));
                    
                    getChart().clearInterest();
                    
                    if (getRate() != 0) {
                        getChart().addInterestValues(getAmount(), getRate());
                    }  
            }
        });
        
        layout.setLeft(new Label("Yearly interest rate"));
        layout.setCenter(secondSlider);
        layout.setRight(secondValue);
        setFormatting(layout);
        
        return layout;
    }
    
    private void setFormatting(Slider slider, int majorTickUnit, int minorTickCount) {
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(majorTickUnit);
        slider.setMinorTickCount(minorTickCount);
        BorderPane.setMargin(slider, new Insets(3));
    }
    
    private void setFormatting(BorderPane layout) {
        layout.setPadding(new Insets(10, 10, 10, 10));
    }
      
    private void setFormatting(VBox layout) {
        layout.setSpacing(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
    }
    
    private void setAmount(double amount) {
        this.parameters.setAmount(amount);
    }
    
    private void setRate(double rate) {
        this.parameters.setRate(rate);
    }
    
    private double getAmount() {
        return this.parameters.getAmount();
    }
    
    private double getRate() {
        return this.parameters.getRate();
    }
    
    private SavingsChart getChart() {
        return this.chart;
    }

}
