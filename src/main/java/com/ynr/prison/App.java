package com.ynr.prison;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
    	FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource("Login.fxml"));
    	AnchorPane anchorPane = new AnchorPane();
    	
    	try {
			anchorPane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	Scene scene = new Scene(anchorPane);
    	stage.setScene(scene);
    	stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}