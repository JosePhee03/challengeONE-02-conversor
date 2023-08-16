package main.conversor;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.conversor.api.Converter;
import main.conversor.api.Currencies;
import main.conversor.api.Temperatures;

import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    @FXML
    public ComboBox<String> comboBoxOutput;
    @FXML
    public Button buttonCurrency;
    @FXML
    public Button buttonTemperature;
    @FXML
    private ComboBox<String> comboBoxInput;
    @FXML
    public TextField textFieldInput;
    @FXML
    public TextField textFieldOutput;

    private final Currencies currencies = new Currencies();
    private final Temperatures temperatures = new Temperatures();

    private String CONVERSE = "Moneda";

    @FXML
    private void handleSwitch () {
        String comboBoxInputValue = comboBoxInput.getValue();
        String comboBoxOutputValue = comboBoxOutput.getValue();
        comboBoxInput.getSelectionModel().select(comboBoxOutputValue);
        comboBoxOutput.getSelectionModel().select(comboBoxInputValue);

        this.changeValue();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.toggleConverter();

        comboBoxInput.valueProperty().addListener((o) -> this.changeValue());
        comboBoxOutput.valueProperty().addListener((o) -> this.changeValue());
    }

    private void toggleConverter () {
        Converter converter;

        if (CONVERSE.equals("Temperatura")) {
            converter = this.temperatures;
        } else {
            converter = this.currencies;
        }

        comboBoxInput.getItems().clear();
        comboBoxOutput.getItems().clear();

        comboBoxInput.setItems(FXCollections.observableArrayList());
        comboBoxOutput.setItems(FXCollections.observableArrayList());

        converter.getAll().forEach((conversionRates -> {
            comboBoxInput.getItems().add(conversionRates.name());
            comboBoxOutput.getItems().add(conversionRates.name());
        }));

        comboBoxInput.getSelectionModel().select(comboBoxInput.getItems().get(0));
        comboBoxOutput.getSelectionModel().select(comboBoxInput.getItems().get(1));

        this.changeValue();
    }

    public void handleConverter() {
        this.changeValue();
    }

    private void changeValue () {
        String comboBoxInputValue = comboBoxInput.getValue();
        String comboBoxOutputValue = comboBoxOutput.getValue();
        String textFieldInputValue = textFieldInput.getText();

        double value = validateInputValue(textFieldInputValue);

        Converter converter;

        if (CONVERSE.equals("Temperatura")) {
            converter = this.temperatures;
        } else {
            converter = this.currencies;
        }

        Double ConverseValue = converter.converter(comboBoxInputValue, comboBoxOutputValue, value);

        textFieldOutput.setText(ConverseValue.toString());

    }
    private double validateInputValue (String str) {
        double doubleValueInput = 0.0;

        try {
            doubleValueInput = Double.parseDouble(str);
        } catch (NumberFormatException ex) {
            System.out.println("NaN");
        }

        return doubleValueInput;
    }

    public void handleNavigation(ActionEvent actionEvent) {

        Button[] navigationButtons = { buttonCurrency, buttonTemperature };

        for (Button button : navigationButtons) {
            button.getStyleClass().remove("button-active");
            if (actionEvent.getTarget().equals(button)) {
                button.getStyleClass().add("button-active");
                CONVERSE = button.getText();
                this.toggleConverter();
            }
        }
    }

}