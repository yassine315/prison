 package com.ynr.prison.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerVisites implements Initializable {
	
	private Scene scene ;
	private Stage stage ;
	private AnchorPane anchorPane;
	
	@FXML
	private Button nouveauVisiteur;
	
	@FXML
	private Button ancienVisiteur;
	
	@FXML
	private TextField nom;
	
	@FXML
	private TextField prenom;
	
	@FXML
	private Button rechercher;
	
	
	@FXML
	private void ajouterVisite() {
		Stage currentStage = (Stage) nouveauVisiteur.getScene().getWindow();
		
		
		FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource("AjouterVisite.fxml"));
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scene = new Scene(anchorPane);
		stage = new Stage();
		stage.setScene(scene);
		
		stage.initOwner(currentStage);
		stage.initModality(Modality.APPLICATION_MODAL); 
		stage.showAndWait();
		
	}
	@FXML
		private void rechercher() {
			
			ancienVisiteur.setDisable(false);
			nouveauVisiteur.setDisable(false);
			
		}
	
 @FXML
 private void ancienVisiteur(){
	
	
	 
		FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource("AncienVisiteur.fxml"));
		AnchorPane anchorPaneAncien = new AnchorPane();
		try {
			anchorPaneAncien= loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 Scene sceneAncien = new Scene(anchorPaneAncien);
		 Stage stageAncien= new Stage();
		 stageAncien.setScene(sceneAncien);
		 stageAncien.show();
	 
 }
@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
 nouveauVisiteur.setDisable(true);
 ancienVisiteur.setDisable(true);
}
}
