package com.ynr.prison.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControllerLogin implements Initializable {
	
	@FXML
	private Button buttonLogin;
	
	@FXML
	private TextField userName;
	
	@FXML
	private TextField user;
	
	@FXML
	private TextField password;
	
	@FXML
	private void sauthentifier() {
		userName.setText("yeeeees");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
