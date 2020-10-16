package sample2;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.Event;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

public class curController implements viewDelegate{
    public viewer view;
    public Model model;
    private Button b;
    public float endpoint;

    public curController(){

    }

    public void buildView(Stage stage) {
        stage.setTitle("currency");
        viewer root = new viewer(Orientation.HORIZONTAL);
        this.view = root;
        root.build();
        root.delegate = this;

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void dayDoLoad(){
        CompletableFuture<ObservableList<Model>> future = new CompletableFuture<>();
//        future.supplyAsync(this::asyncLoadData).thenApply(this::setupChartValues).thenAccept(this::drawChart);  //draw

    }

    public void drawChart(XYChart.Series<Number, Number> v) {
        Platform.runLater(()->{
            view.linechart.getData().setAll(v);
        });
        view.text.setText("current price: $" + endpoint);
    }

    public XYChart.Series<Number, Number> setupChartValues(ObservableList<Model> values){
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        int time = 12;
        for(int i = 0; i < values.size(); i++){
            Model temp = values.get(i);

            if(i != 0)  time += 1;

            float opening = Float.parseFloat(temp.getOpening());
            float closing = Float.parseFloat(temp.getClosing());
            float high = Float.parseFloat(temp.getHigh());
            float low = Float.parseFloat(temp.getLow());

            if(opening == high && closing == low || opening == low && closing == high){

                float ttime = (float)time;
                series.getData().add(new XYChart.Data<>(ttime, opening));
            }else if(opening == high && closing > low || closing == high && opening > low){
                float ttime1 = (float)time;
                float ttime2 = (float)(time + 0.5);
                series.getData().add(new XYChart.Data<>(ttime1, opening));
                series.getData().add(new XYChart.Data<>(ttime2, low));
            }else if(opening == low && closing < high || closing == low && opening < high){
//
                float ttime1 = (float)time;
                float ttime2 = (float)(time + 0.5);
                series.getData().add(new XYChart.Data<>(ttime1, opening));
                series.getData().add(new XYChart.Data<>(ttime2, high));
            }else if(opening < high && closing > low || opening > low && closing < high){

                float ttime1 = (float)time;
                float ttime2 = (float)(time + 0.33);
                float ttime3 = (float)(time + 0.66);
                series.getData().add(new XYChart.Data<>(ttime1, opening));
                series.getData().add(new XYChart.Data<>(ttime2, high));
                series.getData().add(new XYChart.Data<>(ttime3, low));
            }
            //process the last time endpoint.
            if(i == values.size() - 1) {
                series.getData().add(new XYChart.Data<>((float)time + 1, closing));
                endpoint = closing;
            }
        }

        return series;  //putting into a new series
    }


    public ObservableList<Model> asyncLoadData() {
        String time = b.getText();
        try {
            //in three situation
            if (time.equals("Day")) {
                URL address = new URL("https://min-api.cryptocompare.com/data/histoday?aggregate=1&e=CCCAGG&extraParams=CryptoCompare&fsym=BTC&limit=10&tryConversion=false&tsym=USD");
                ObservableList<Model> values = model.readFromAPI(address);
                return values;
            } else if (time.equals("Hour")) {
                URL address = new URL("https://min-api.cryptocompare.com/data/histohour?aggregate=1&e=CCCAGG&extraParams=CryptoCompare&fsym=BTC&limit=10&tryConversion=false&tsym=USD");
                ObservableList<Model> values = model.readFromAPI(address);
                return values;
            } else if (time.equals("Min")) {
                URL address = new URL("https://min-api.cryptocompare.com/data/histominute?aggregate=1&e=CCCAGG&extraParams=CryptoCompare&fsym=BTC&limit=10&tryConversion=false&tsym=USD");
                ObservableList<Model> values = model.readFromAPI(address);
                return values;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void handleEvent(Event event) {
        b = (Button) event.getSource();
        predraw();      //according to buttun different process
        dayDoLoad();
    }

    private void predraw() {
        NumberAxis yAxis = new NumberAxis(9000,13000,500);
        if(b.getText().equals("Day")) {
            view.linechart.getXAxis().setLabel("day");
        }else if(b.getText().equals("Hour")){
            view.linechart.getXAxis().setLabel("hour: pm");
        }else if(b.getText().equals("Min")){
            view.linechart.getXAxis().setLabel("minute");
        }
    }

}
