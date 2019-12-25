
package com.ynr.prison.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class ControllerHome implements Initializable {

	@FXML
	private AnchorPane container;
	
	@FXML
	private void formationDeBase() {
		
		FXMLLoader loader= new FXMLLoader(this.getClass().getClassLoader().getResource("Formation.fxml"));
		
		AnchorPane newAnchorPane = new AnchorPane();
		try {
			newAnchorPane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double width = container.getWidth();
		container.getChildren().setAll(newAnchorPane) ;
		container.setMinHeight(width);
	
		
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub


	}

}
