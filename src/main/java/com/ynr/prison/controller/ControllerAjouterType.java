package com.ynr.prison.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


import com.ynr.beans.Type;
import com.ynr.prison.nitification.Notification;
import com.ynr.prison.nitification.Notifications;
import com.ynr.prison.nitification.TrayNotification;
import com.ynr.util.HibernateUtil;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControllerAjouterType implements Initializable{
	SessionFactory sessionFactory;
	@FXML 
	private TextField inTypeFormation;
	@FXML
	private TextField inResponsableType;
	@FXML
	private TextArea inDescriptionType;
	public void ajouterType() {
		try {
			String typeFormation= inTypeFormation.getText();
			String responsableType= inResponsableType.getText();
			String descriptionType= inDescriptionType.getText();
			Type type=new Type(typeFormation, responsableType, descriptionType);
			Session sessionn = sessionFactory.openSession();
			sessionn.beginTransaction();
			sessionn.save(type);
			sessionn.persist(type);
			sessionn.getTransaction().commit();
			sessionn.close();
			
			String title = "L'operation est effectuée";
	        String message = "Le Type a été ajouté correctement";
	        Notification notification = Notifications.SUCCESS;
	        TrayNotification tray = new TrayNotification(title, message, notification);
	        tray.showAndDismiss(new Duration(2000));
	        Stage stage = (Stage)inTypeFormation.getScene().getWindow();
			stage.close();
		}
		catch(Exception e) {
			e.printStackTrace();;
			String title = "L'operation est échouée";
	        String message = "La Type n'a pas été ajouté ";
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
