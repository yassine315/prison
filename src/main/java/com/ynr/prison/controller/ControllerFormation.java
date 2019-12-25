package com.ynr.prison.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerFormation implements Initializable {

	@FXML 
	private Button nouvelleFormation;

@FXML
private void ajouterFormation() {
	Stage currentStage = (Stage) nouvelleFormation.getScene().getWindow();

	FXMLLoader loader= new FXMLLoader(this.getClass().getClassLoader().getResource("AjouterFormation.fxml"));
AnchorPane anchorPane = new AnchorPane();
	try {
		anchorPane = loader.load();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

Scene	scene = new Scene(anchorPane);
Stage	stage = new Stage();
	stage.setScene(scene);
	
	stage.initOwner(currentStage);
	stage.initModality(Modality.APPLICATION_MODAL); 
	stage.showAndWait();

}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
