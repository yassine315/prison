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
import javafx.stage.Stage;

public class ControllerLogin implements Initializable {
	

	private Scene scene;
	private Stage primaryStage;
	
	private AnchorPane newAnchorPane;
	
	@FXML
	private AnchorPane anchorPane;
	
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
		
		scene = anchorPane.getScene(); 
		primaryStage = (Stage)scene.getWindow();
		
		FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource("Home.fxml"));
		
		try {
			newAnchorPane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		scene.setRoot(newAnchorPane);
		primaryStage.setMaximized(true);
		
	}
	


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
