package com.ynr.prison;

import java.io.IOException;

import org.hibernate.SessionFactory;

import com.ynr.util.HibernateUtil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
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
<<<<<<< HEAD
    	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
=======
>>>>>>> cbb684cf5bf74c130334bfde402d01d56c7345a2
    	
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