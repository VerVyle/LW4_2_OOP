package com.vervyle.lw5_oop.controllers;

import com.vervyle.lw5_oop.model.ValueModel;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {

    @FXML
    private Slider slider_a;
    @FXML
    private Slider slider_b;
    @FXML
    private Slider slider_c;
    @FXML
    private Spinner<Integer> spinner_a;
    @FXML
    private Spinner<Integer> spinner_b;
    @FXML
    private Spinner<Integer> spinner_c;
    @FXML
    private TextField text_a;
    @FXML
    private TextField text_b;
    @FXML
    private TextField text_c;

    private ValueModel model;

    private void initTextFields() {
        text_a.setText(model.getValueA().toString());
        text_b.setText(model.getValueB().toString());
        text_c.setText(model.getValueC().toString());

        text_a.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER))
                model.updateValueA(Integer.parseInt(text_a.getText()));
        });
        text_b.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER))
                model.updateValueB(Integer.parseInt(text_b.getText()));
        });
        text_c.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER))
                model.updateValueC(Integer.parseInt(text_c.getText()));
        });

        text_a.focusedProperty().addListener(observable -> model.updateValueA(Integer.parseInt(text_a.getText())));
        text_b.focusedProperty().addListener(observable -> model.updateValueB(Integer.parseInt(text_b.getText())));
        text_c.focusedProperty().addListener(observable -> model.updateValueC(Integer.parseInt(text_c.getText())));
    }

    private void initSpinners() {
        SpinnerValueFactory<Integer> valueFactoryA = new
                SpinnerValueFactory.IntegerSpinnerValueFactory(ValueModel.MIN_VALUE, ValueModel.MAX_VALUE);
        valueFactoryA.setValue(model.getValueA());
        spinner_a.setValueFactory(valueFactoryA);

        SpinnerValueFactory<Integer> valueFactoryB = new
                SpinnerValueFactory.IntegerSpinnerValueFactory(ValueModel.MIN_VALUE, ValueModel.MAX_VALUE);
        valueFactoryB.setValue(model.getValueB());
        spinner_b.setValueFactory(valueFactoryB);

        SpinnerValueFactory<Integer> valueFactoryC = new
                SpinnerValueFactory.IntegerSpinnerValueFactory(ValueModel.MIN_VALUE, ValueModel.MAX_VALUE);
        valueFactoryC.setValue(model.getValueC());
        spinner_c.setValueFactory(valueFactoryC);

        spinner_a.setOnMouseClicked(mouseEvent -> model.updateValueA(spinner_a.getValue()));
        spinner_b.setOnMouseClicked(mouseEvent -> model.updateValueB(spinner_b.getValue()));
        spinner_c.setOnMouseClicked(mouseEvent -> model.updateValueC(spinner_c.getValue()));
    }

    private void initSliders() {
        slider_a.setValue(model.getValueA());
        slider_b.setValue(model.getValueB());
        slider_c.setValue(model.getValueC());

        slider_a.setOnMouseReleased(mouseEvent -> model.updateValueA((int) slider_a.getValue()));
        slider_b.setOnMouseReleased(mouseEvent -> model.updateValueB((int) slider_b.getValue()));
        slider_c.setOnMouseReleased(mouseEvent -> model.updateValueC((int) slider_c.getValue()));
    }

    public void onExit(WindowEvent windowEvent) {
        try {
            model.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initModel() {
        model = new ValueModel();

        model.addListenerToA(observable -> text_a.setText(model.getValueA().toString()));
        model.addListenerToA(observable -> spinner_a.getValueFactory().setValue(model.getValueA()));
        model.addListenerToA(observable -> slider_a.setValue(model.getValueA()));

        model.addListenerToB(observable -> text_b.setText(model.getValueB().toString()));
        model.addListenerToB(observable -> spinner_b.getValueFactory().setValue(model.getValueB()));
        model.addListenerToB(observable -> slider_b.setValue(model.getValueB()));

        model.addListenerToC(observable -> text_c.setText(model.getValueC().toString()));
        model.addListenerToC(observable -> spinner_c.getValueFactory().setValue(model.getValueC()));
        model.addListenerToC(observable -> slider_c.setValue(model.getValueC()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initModel();
        initTextFields();
        initSpinners();
        initSliders();
    }
}