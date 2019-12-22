package com.ynr.prison.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerAjouteVisite {
	
	@FXML
	private AnchorPane anchorPaneAV;
	
	@FXML
	private void annullerVisite() {
		
		anchorPaneAV.getChildren().clear();
		Stage stage = (Stage)anchorPaneAV.getScene().getWindow();
		stage.close();
		
	}
	
}
