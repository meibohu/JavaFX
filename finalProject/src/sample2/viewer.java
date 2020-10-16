package sample2;

import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;

public class viewer extends FlowPane {
    final NumberAxis xAxis = new NumberAxis(12,23,1);
    final NumberAxis yAxis = new NumberAxis(9500,12500,500);

    viewDelegate delegate;

    LineChart linechart;
    TextField text;
    public viewer(Orientation orientation) {
        super(orientation);
    }

    public void build() {
        Separator sep = new Separator();
        sep.setPrefHeight(50);
        this.getChildren().add(sep);

        Button butt0 = new Button("Min");
        butt0.addEventHandler(MouseEvent.MOUSE_CLICKED, (event)->{
            if(this.delegate != null){
                this.delegate.handleEvent(event);
            }
        });
        Button butt1 = new Button("Hour");
        butt1.addEventHandler(MouseEvent.MOUSE_CLICKED, (event)->{
            if(this.delegate != null){
                this.delegate.handleEvent(event);
            }
        });
        Button butt2 = new Button("Day");
        butt2.addEventHandler(MouseEvent.MOUSE_CLICKED, (event)->{
            if(this.delegate != null){
                this.delegate.handleEvent(event);
            }
        });

        this.getChildren().add(butt0);
        this.getChildren().add(butt1);
        this.getChildren().add(butt2);

        Separator sep2 = new Separator();
        sep2.setPrefHeight(50);
        this.getChildren().add(sep2);


        text = new TextField("");
        this.getChildren().add(text);

        linechart = new LineChart(xAxis, yAxis);
        linechart.getYAxis().setLabel("Price");
        this.getChildren().add(linechart);
    }
}
