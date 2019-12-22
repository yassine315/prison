package com.ynr.prison.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerHome implements Initializable {
	
	@FXML
	private AnchorPane container;
	
	
	@FXML
	private void nouveauFormation() {
		
	}
	
	@FXML
	private void passageExamens() {
		
	}
	
	@FXML
	private void formationNSS() {
		
	}
	
	@FXML
	private void formationProfessionnelle() {
		
	}
	
	@FXML
	private void formationBase() {
		
	}
	
	@FXML
	private void prisonnier() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource("Prisonnier.fxml"));
		
		Region newContainer = new Region();
		
		try {
			newContainer =(Region) loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		container.getChildren().clear();
		container.getChildren().add(newContainer);
		
		newContainer.prefHeightProperty().bind(container.heightProperty());
		newContainer.prefWidthProperty().bind(container.widthProperty());
		
	}
	
	@FXML
	private void ajouterPrisonnier() {
		Stage currentStage = (Stage) container.getScene().getWindow();
		
		
		FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource("AjouterPrisonnier.fxml"));
		AnchorPane anchorPane = new AnchorPane();
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(anchorPane);
		Stage stage = new Stage();
		stage.setScene(scene);
		
		stage.initOwner(currentStage);
		stage.initModality(Modality.APPLICATION_MODAL); 
		stage.showAndWait();
		
	}
	
	@FXML
	private void visite() {
		
	FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource("Visites.fxml"));
	Region newContainer = new Region();
	
	try {
		newContainer = (Region) loader.load();
	}
	catch (IOException e){
		e.printStackTrace();
	}
	container.getChildren().clear();
	container.getChildren().add(newContainer);
	newContainer.prefHeightProperty().bind(container.heightProperty());
	newContainer.prefWidthProperty().bind(container.widthProperty());
	}
	
	@FXML
	private void deces() {
		
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
