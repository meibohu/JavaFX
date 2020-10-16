import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.geometry.Orientation;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreditInput extends Application{
    public static void main(String[] args) {
        launch(args);
        System.out.println("Launched!");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FlowPane root = new FlowPane(Orientation.VERTICAL);
        primaryStage.setTitle("Credit Card form validator");

        Label card_number = new Label("Card Number:");
        card_number.setStyle("-fx-font-family: Arial;" +
                " -fx-padding: 10px;");
        root.getChildren().add(card_number);      //phone label: phone

        //The part of card number is gridpane.
        GridPane grid = new GridPane();

        TextField textField = new TextField();
        textField.setPromptText("XXXX XXXX XXXX XXXX");
        textField.setPrefWidth(300);
        textField.setStyle(
                "-fx-font-size: 15;");

        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

//                Image imageVisa = new Image("https://icons.iconarchive.com/icons/iconshock/credit-card/32/visa-icon.png");
//                Image imageMas = new Image("https://icons.iconarchive.com/icons/designbolts/credit-card-payment/32/Master-Card-Blue-icon.png");
//                Image imageAmex = new Image("https://icons.iconarchive.com/icons/designbolts/credit-card-payment/32/American-Express-icon.png");
//                Image imageJCB = new Image("https://icons.iconarchive.com/icons/iconshock/credit-card/32/jcb-icon.png");
                Image imageVisa = new Image("visa-icon.png");
                Image imageMas = new Image("MasterCard-icon.png");
                Image imageAmex = new Image("AMEX-icon.png");
                Image imageJCB = new Image("jcb-icon.png");
                //add logo
                if (newValue != null) {
                    if (newValue.length() >= 1) {
                        if (newValue.charAt(0) == '4') {
                            ImageView imageView = new ImageView(imageVisa);
                            grid.add(imageView, 1, 2);
                        } else if (newValue.charAt(0) == '5') {
                            ImageView imageView = new ImageView(imageMas);
                            grid.add(imageView, 1, 2);
                        }
                    }
                    if (newValue.length() >= 2) {
                        if (newValue.charAt(0) == '3') {
                            if (newValue.charAt(1) == '4' || newValue.charAt(1) == '7') {
                                ImageView imageView = new ImageView(imageAmex);
                                grid.add(imageView, 1, 2);
                            } else if (newValue.charAt(1) == '5') {
                                ImageView imageView = new ImageView(imageJCB);
                                grid.add(imageView, 1, 2);
                            }
                        }
                    }

                    //increase space automatically
                    if(oldValue.length() == 3 && newValue.length() == 4){
                        textField.setText(newValue + " ");
                    } else if(oldValue.length() == 8 && newValue.length() == 9){
                        textField.setText(newValue + " ");
                    } else if(oldValue.length() == 13 && newValue.length() == 14) {
                        textField.setText(newValue + " ");
                    }

                    //check if the number is valid. If's valid, add logo at the end of textfield.
                    if (newValue.length() == 19) {
                        if (isValidNum(newValue).matches()) {
                            textField.setStyle(
                                    "-fx-background-image:url('Accept-icon.png');" +
                                            "-fx-background-position: right center;" +
                                            "-fx-background-repeat: no-repeat;");

                            System.out.println("The card number is: " + newValue);
                        }else if(!isValidNum(newValue).matches()){
                            textField.setStyle(
                                    "-fx-background-image:url('Close-icon.png');" +
                                            "-fx-background-position: right center;" +
                                            "-fx-background-repeat: no-repeat;");

                            System.out.println("The card number is not valid.");

                        }
                    }
                    if (newValue.length() > 19) textField.setText(oldValue);
                }
            }
        });


        grid.add(textField, 0, 2);
        root.getChildren().add(grid);

        ////extra space:
        Separator s1 = new Separator();
        s1.setPrefHeight(20);
        root.getChildren().add(s1);

        //CVV and Expiration Date in Girdpand:
        GridPane layout = new GridPane();

        TextField TimeField = new TextField();
        TimeField.setStyle("-fx-font-size: 15;");
        TextField CVVField= new TextField();
        CVVField.setStyle("-fx-font-size: 15;");
        layout.add(new Label("CVV:"), 0, 0);
        layout.add(new Label("Expiration Date:"), 1, 0);
        CVVField.setPromptText("XXX");
        TimeField.setPromptText("mm/yyyy");
        layout.add(CVVField, 0, 1);
        layout.setHgap(15);     //spacing the buttons
        layout.add(TimeField, 1, 1);


        //Listener to CVV(amex is 4 digits and others are 3 digits)
        CVVField.textProperty().addListener((observable, oldValue, newValue) -> {
            //do not allow non numeric fields in CVV
//            CVVField.setText(newValue.replaceAll("[^0-9]", ""));
            Matcher matcher = isValidNum(textField.getText());
            if(matchGroup(matcher) != ""){
                if (matchGroup(matcher) == "amex") {
                    if (newValue.length() == 4) System.out.println("CVV is: " + newValue);
                    else if (newValue.length() > 4) CVVField.setText(oldValue);
                } else {
                    if (newValue.length() == 3) System.out.println("CVV is: " + newValue);
                    else if (newValue.length() > 3) CVVField.setText(oldValue);
                }
            }
        });

        //Listener to Expiration Date:
        TimeField.textProperty().addListener((observable, oldValue, newValue) -> {
            //do not allow non numeric fields in CVV
            if(newValue.matches("^[0-9]{2}/[0-9]{4}") && isValidYear(newValue)){
                System.out.println("The Expiration Date is: " + newValue);
            }
            if(newValue.length() > 7) TimeField.setText(oldValue);;
        });

        root.getChildren().add(layout);

        Scene scene = new Scene(root, 400, 300 );
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    //check Expiration Date is more than 2020
    public boolean isValidYear(String value){
        if(value.length() != 7) return false;
        String month = value.substring(0,2);
        String year = value.substring(3);
        if(1 <= Integer.parseInt(month) && Integer.parseInt(month) <= 12 && Integer.parseInt(year) >= 2020) return true;
        return false;
    }

    //check number is valid using regex
    public static Matcher isValidNum(String card){
        String cardd = card.replaceAll(" ", "");
        //16 bits:
        String regex = "^(?:(?<visa>4[0-9]{15})|" +
                "(?<mastercard>5[0-9]{15})|" +
                "(?<amex>3[47][0-9]{14})|" +
                "(?<jcb>35[0-9]{14}))$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cardd);
        return matcher;
    }

    public String matchGroup(Matcher matcher){
        if(matcher.matches()){
            if(matcher.group("visa") != null) return "visa";
            if(matcher.group("mastercard") != null) return "mastercard";
            if(matcher.group("amex") != null) return "amex";
            if(matcher.group("jcb") != null) return "jcb";
        }
        return "";
    }
}
