package com.ynr.prison.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ynr.beans.User;
import com.ynr.prison.nitification.Notification;
import com.ynr.prison.nitification.Notifications;
import com.ynr.prison.nitification.TrayNotification;
import com.ynr.util.HibernateUtil;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControllerLogin implements Initializable {
	
	SessionFactory sessionFactory;

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
	private PasswordField password;
	
	@FXML
	private void sauthentifier() {
		String email = new String();
		email = userName.getText();
		String motPass = password.getText();
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		User userPrison = session.find(User.class, email);
		session.getTransaction().commit();
		session.close();

		if(userPrison.getPassword().equals(motPass)) {
			
			scene = anchorPane.getScene(); 
			primaryStage = (Stage)scene.getWindow();
			
			
			FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource("Home.fxml"));
			
			try {
				newAnchorPane = loader.load();
				newAnchorPane.getStylesheets().add(getClass().getClassLoader().getResource("FlatBee.css").toString());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				String title = "L'operation est échouée";
		        String message = "Erreur de l'authentification";
		        Notification notification = Notifications.ERROR;
		        TrayNotification tray = new TrayNotification(title, message, notification);
		        tray.showAndDismiss(new Duration(2000));
			}
			anchorPane.getChildren().clear();
			scene.setRoot(newAnchorPane);
			primaryStage.setMaximized(true);
			
		}else {
			String title = "L'operation est échouée";
	        String message = "Erreur de l'authentification";
	        Notification notification = Notifications.ERROR;
	        TrayNotification tray = new TrayNotification(title, message, notification);
	        tray.showAndDismiss(new Duration(2000));
		}
	}
	


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		sessionFactory = HibernateUtil.getSessionFactory();
		
	}

}
