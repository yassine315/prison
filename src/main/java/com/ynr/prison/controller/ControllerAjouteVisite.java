package com.ynr.prison.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class ControllerAjouteVisite {
	
	@FXML
	private AnchorPane anchorPaneAV;
	
	@FXML
	private void annullerVisite() {
		
		anchorPaneAV.getChildren().clear();
		anchorPaneAV.getScene().getWindow();
		
		
	}
	
}
