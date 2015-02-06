/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numberconverter;

import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Joon
 */
public class NumberConverter extends Application {

    private static final BigInteger ZERO = new BigInteger("0");
    private static final BigInteger TWO = new BigInteger("2");
    private static final BigInteger THREE = new BigInteger("3");
    private static final BigInteger FOUR = new BigInteger("4");
    private static final BigInteger FIVE = new BigInteger("5");
    private static final BigInteger SIX = new BigInteger("6");
    private static final BigInteger SEVEN = new BigInteger("7");
    private static final BigInteger EIGHT = new BigInteger("8");
    private static final BigInteger NINE = new BigInteger("9");
    private static final BigInteger TEN = new BigInteger("10");
    private static final BigInteger ELEVEN = new BigInteger("11");
    private static final BigInteger TWELVE = new BigInteger("12");
    private static final BigInteger THIRTEEN = new BigInteger("13");
    private static final BigInteger FOURTEEN = new BigInteger("14");
    private static final BigInteger FIFTEEN = new BigInteger("15");
    private static final BigInteger SIXTEEN = new BigInteger("16");

    private ObservableList<String> bases
            = FXCollections.observableArrayList(
                    "10",
                    "2",
                    "3",
                    "4",
                    "5",
                    "6",
                    "7",
                    "8",
                    "9",
                    "16"
            );
    private int base1 = 10;
    private int base2 = 2;
    private String num1;
    private String num2;
    TextField textField1 = new TextField();
    TextField textField2 = new TextField();

    @Override
    public void start(Stage primaryStage) throws Exception {

        // combo box 1
        final ComboBox base1ComboBox = new ComboBox(bases);
        base1ComboBox.setMinWidth(35);
        base1ComboBox.setPromptText("10");

        base1ComboBox.setOnAction(e -> {
            base1 = Integer.parseInt(base1ComboBox.getSelectionModel().getSelectedItem().toString());
        });

        // combo box 2
        final ComboBox base2ComboBox = new ComboBox(bases);
        base2ComboBox.setMinWidth(35);
        base2ComboBox.setPromptText("2");

        base2ComboBox.setOnAction(e -> {
            base2 = Integer.parseInt(base2ComboBox.getSelectionModel().getSelectedItem().toString());
        });

        ConvertButton1Handler handler1 = new ConvertButton1Handler();
        ConvertButton2Handler handler2 = new ConvertButton2Handler();

        // textfield 1
        textField1.setText("15");
        textField1.setAlignment(Pos.CENTER_RIGHT);
        textField1.setPrefWidth(250);
        textField1.setOnAction(handler1);

        // textfield 2
        textField2.setText("1111");
        textField2.setAlignment(Pos.CENTER_RIGHT);
        textField2.setPrefWidth(250);
        textField2.setOnAction(handler2);

        // convert button 1
        final Button convertButton1 = new Button("Convert");
        convertButton1.setMinWidth(55);
        convertButton1.setOnAction(handler1);

        // convert button 2
        final Button convertButton2 = new Button("Convert");
        convertButton2.setMinWidth(55);
        convertButton2.setOnAction(handler2);

        // set stage
        GridPane pane = new GridPane();
        pane.setVgap(4);
        pane.setHgap(10);
        pane.setPadding(new Insets(10, 5, 10, 10));

        pane.add(base1ComboBox, 0, 0);
        pane.add(base2ComboBox, 0, 1);
        pane.add(textField1, 1, 0);
        pane.add(textField2, 1, 1);
        pane.add(convertButton1, 2, 0);
        pane.add(convertButton2, 2, 1);

        Scene scene = new Scene(pane, 400, 65);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Number Converter");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private String convert(String number, int from, int to) throws Exception {
        if (from == 0 || to == 0) {
            throw new IllegalArgumentException("Must choose bases");
        }
        switch (from) {
            case 10:
                if (!number.matches("[0-9]+")) {
                    throw new IllegalArgumentException("Incorrect base 10 number");
                }
                break;
            case 2:
                if (!number.matches("[01]+")) {
                    throw new IllegalArgumentException("Incorrect base 2 number");
                }
                break;
            case 3:
                if (!number.matches("[012]+")) {
                    throw new IllegalArgumentException("Incorrect base 3 number");
                }
                break;
            case 4:
                if (!number.matches("[0-3]+")) {
                    throw new IllegalArgumentException("Incorrect base 4 number");
                }
                break;
            case 5:
                if (!number.matches("[0-4]+")) {
                    throw new IllegalArgumentException("Incorrect base 5 number");
                }
                break;
            case 6:
                if (!number.matches("[0-5]+")) {
                    throw new IllegalArgumentException("Incorrect base 6 number");
                }
                break;
            case 7:
                if (!number.matches("[0-6]+")) {
                    throw new IllegalArgumentException("Incorrect base 7 number");
                }
                break;
            case 8:
                if (!number.matches("[0-7]+")) {
                    throw new IllegalArgumentException("Incorrect base 8 number");
                }
                break;
            case 9:
                if (!number.matches("[0-8]+")) {
                    throw new IllegalArgumentException("Incorrect base 8 number");
                }
                break;
            case 16:
                if (!number.matches("[0-9a-fA-F]+")) {
                    throw new IllegalArgumentException("Incorrect base 16 number");
                }
                break;
        }

        if (from == 10) {
            return decToR(number, to);
        }
        if (to == 10) {
            return rToDec(number, from) + "";
        }

        return decToR(rToDec(number, from), to);
    }

    private String decToR(String original, int to) throws Exception {
        // original will be base 10
        BigInteger number = new BigInteger(original);
        BigInteger radix = new BigInteger(to + "");
        String converted = "";

        while (number.compareTo(ZERO) > 0) {
            converted = numToChar(((number.remainder(radix)))) + converted;
            number = number.divide(radix);
        }
        return converted;
    }

    private String rToDec(String original, int from) throws Exception {
        // answer will be base 10
        int base = 0;
        BigInteger converted = ZERO;

        BigInteger add = ZERO;
        BigInteger power;
        for (int i = original.length() - 1; i >= 0; i--) {
            power = new BigInteger((int) Math.pow(from, base) +"");
            add = charToNum(original.charAt(i)).multiply(power);
            converted = converted.add(add);
            base++;
        }

        return converted + "";
    }

    private char numToChar(BigInteger num) throws Exception {
        if (num.compareTo(SIXTEEN) > 0 || num.compareTo(ZERO) < 0) {
            throw new Exception();
        }
        if (num.compareTo(TEN) == 0) {
            return 'A';
        }
        if (num.compareTo(ELEVEN) == 0) {
            return 'B';
        }
        if (num.compareTo(TWELVE) == 0) {
            return 'C';
        }
        if (num.compareTo(THIRTEEN) == 0) {
            return 'D';
        }
        if (num.compareTo(FOURTEEN) == 0) {
            return 'E';
        }
        if (num.compareTo(FIFTEEN) == 0) {
            return 'F';
        }

        return num.toString().charAt(0);

    }

    private BigInteger charToNum(char c) throws Exception {
        if (!((c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f') || (c >= '0' && c <= '9'))) {
            throw new Exception();
        }

        if (c == 'a' || c == 'A') {
            return TEN;
        }
        if (c == 'b' || c == 'B') {
            return ELEVEN;
        }
        if (c == 'c' || c == 'C') {
            return TWELVE;
        }
        if (c == 'd' || c == 'D') {
            return THIRTEEN;
        }
        if (c == 'e' || c == 'E') {
            return FOURTEEN;
        }
        if (c == 'f' || c == 'F') {
            return FIFTEEN;
        }
        return new BigInteger(c + "");

    }

    class ConvertButton1Handler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            // convert num1 from base1 to base 2 and change num2 to result 
            num1 = textField1.getText();

            try {
                num2 = convert(num1, base1, base2);
            } catch (Exception ex) {
                Logger.getLogger(NumberConverter.class.getName()).log(Level.SEVERE, null, ex);
            }
            textField2.setText(num2);
        }

    }

    class ConvertButton2Handler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            // convert num2 from base2 to base 1 and change num1 to result 
            num2 = textField2.getText();

            try {
                num1 = convert(num2, base2, base1);
            } catch (Exception ex) {
                Logger.getLogger(NumberConverter.class.getName()).log(Level.SEVERE, null, ex);
            }
            textField1.setText(num1);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
