package com.ynr.prison.controller;

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
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControllerAjouterUser implements Initializable{
	SessionFactory sessionFactory;
	@FXML 
	private TextField inEmailUtil;
	@FXML
	private TextField inNomUtil;
	@FXML
	private TextField inPrenomUtil;
	@FXML
	private TextField inLoginUtil;
	@FXML
	private PasswordField inMpUtil;
	public void ajouter() {
		String nomUtil= inNomUtil.getText();
		String prenomUtil= inPrenomUtil.getText();
		String emailUtil= inEmailUtil.getText();
		String loginUtil= inLoginUtil.getText();
		String mpUtil= inMpUtil.getText();
		User user=new User(nomUtil, prenomUtil, emailUtil, loginUtil, mpUtil);
		try {
			Session sessionn = sessionFactory.openSession();
			sessionn.beginTransaction();
			sessionn.save(user);
			sessionn.persist(user);
			sessionn.getTransaction().commit();
			sessionn.close();
			
			String title = "L'operation est effectuée";
	        String message = "L'utilisateur a été ajouté correctement";
	        Notification notification = Notifications.SUCCESS;
	        TrayNotification tray = new TrayNotification(title, message, notification);
	        tray.showAndDismiss(new Duration(2000));
	        Stage stage = (Stage)inLoginUtil.getScene().getWindow();
			stage.close();
		}
		catch(Exception e) {
			e.printStackTrace();;
			String title = "L'operation est échouée";
	        String message = "L'utilisateur n'a pas été ajouté ";
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
