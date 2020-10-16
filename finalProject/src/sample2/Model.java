package sample2;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Model {

    private StringProperty time;
    private StringProperty high;
    private StringProperty low;
    private StringProperty opening;
    private StringProperty closing;

    public Model(String time, String high, String low, String opening, String closing) {
        this.time = new SimpleStringProperty(this, "time");
        this.high = new SimpleStringProperty(this, "high");
        this.low = new SimpleStringProperty(this, "low");
        this.opening = new SimpleStringProperty(this, "opening");
        this.closing = new SimpleStringProperty(this, "closing");
        this.setTime(time);
        this.setHigh(high);
        this.setLow(low);
        this.setOpening(opening);
        this.setClosing(closing);
    }

    public String getTime(){ return time.get();}

    private void setTime(String _time) {
        this.time.set(_time);
    }

    public String getHigh() {
        return high.get();
    }

    public void setHigh(String _high) {
        this.high.set(_high);
    }

    public String getLow() {
        return low.get();
    }

    public void setLow(String _low) {
        this.low.set(_low);
    }

    public String getOpening() {
        return opening.get();
    }

    public void setOpening(String _opening) {
        this.opening.set(_opening);
    }

    public String getClosing() {
        return closing.get();
    }

    public void setClosing(String _closing) {
        this.closing.set(_closing);
    }

    @Override
    public String toString() {
        return "model{" +
                "time=" + time.get() +
                ", high=" + high.get() +
                ", low=" + low.get() +
                ", opening=" + opening.get() +
                ", closing=" + closing.get() +
                '}';
    }
    //10482.58 hour
    //10479.15 min
    //10477.22 day
    public static ObservableList<Model> readFromAPI(URL address){
        String contents = "";
        try {

            InputStreamReader reader = new InputStreamReader(address.openStream());
            BufferedReader buffer = new BufferedReader(reader);

            String line = "";
            while((line = buffer.readLine())!= null){
                contents += line;
            }

            ObservableList<Model> li = parseTime(contents);
            for(Model st: li){
                System.out.println(st.toString());
            }
            return li;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static ObservableList<Model> parseTime(String contents){
        System.out.println(contents);
        Gson parser = new Gson();
        JsonObject parsed = parser.fromJson(contents, JsonObject.class);
        JsonArray results = parsed.getAsJsonArray("Data");
//        ObservableList<String> result = FXCollections.observableArrayList();
        ObservableList<Model> result = FXCollections.observableArrayList();

        String startTime = "", openValue = "", closeValue = "", highValue = "", lowValue = "";
        for(JsonElement item: results) {
            JsonObject currency = item.getAsJsonObject();

            if (currency.getAsJsonPrimitive("time") != null)
                startTime = currency.getAsJsonPrimitive("time").getAsString();
            if (currency.getAsJsonPrimitive("close") != null)
                closeValue = currency.getAsJsonPrimitive("close").getAsString();
            if (currency.getAsJsonPrimitive("open") != null)
                openValue = currency.getAsJsonPrimitive("open").getAsString();
            if (currency.getAsJsonPrimitive("high") != null)
                highValue = currency.getAsJsonPrimitive("high").getAsString();
            if (currency.getAsJsonPrimitive("low") != null)
                lowValue = currency.getAsJsonPrimitive("low").getAsString();


            result.add(new Model(startTime, highValue, lowValue, openValue, closeValue));
        }

        return result;
    }


}
