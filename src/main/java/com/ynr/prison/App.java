package com.ynr.prison;

import java.io.IOException;


import org.hibernate.SessionFactory;

import com.ynr.util.HibernateUtil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * JavaFX App
 * 
 * 
 * module com.example {
    requires java.persistence;
    requires hibernate.core;
    opens com.example to hibernate.core;
}
 */



public class App extends Application {
	
	SessionFactory sessionFactory ;

    @Override
    public void start(Stage stage) {
    	
    	FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource("Login.fxml"));
    	AnchorPane anchorPane = new AnchorPane();
    	
    	try {
			anchorPane = loader.load();
			anchorPane.getStylesheets().add(getClass().getClassLoader().getResource("FlatBee.css").toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	Scene scene = new Scene(anchorPane);
    	stage.setScene(scene);
    	stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("prison_logo.jpg")));
    	stage.show();
    
    }

    public static void main(String[] args) {
        launch();
    }

}