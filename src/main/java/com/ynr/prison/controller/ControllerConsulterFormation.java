package com.ynr.prison.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

public class ControllerConsulterFormation implements Initializable {
	@FXML
	private AnchorPane container;
	@FXML
private void ajouterSession() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource("AjouerSession.fxml"));
		Region newAnchorPane= new Region();
		try {
			newAnchorPane=loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		container.getChildren().clear();
		container.getChildren().add(newAnchorPane);
		newAnchorPane.prefHeightProperty().bind(container.heightProperty());
		newAnchorPane.prefWidthProperty().bind(container.widthProperty());
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
