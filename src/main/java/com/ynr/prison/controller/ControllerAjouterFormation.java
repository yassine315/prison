package com.ynr.prison.controller;


import org.hibernate.SessionFactory;
import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.Session;

import javafx.fxml.Initializable;
import com.ynr.beans.Type;
import com.ynr.beans.Formation;
import com.ynr.prison.nitification.Notification;
import com.ynr.prison.nitification.Notifications;
import com.ynr.prison.nitification.TrayNotification;
import com.ynr.util.HibernateUtil;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControllerAjouterFormation implements Initializable{

	SessionFactory sessionFactory;
	@FXML
	private TextField inNomFormation;
	@FXML
	private TextField inResponsableFormation;
	@FXML
	private TextField inDureeFormation;
	private Type typeTarget;
	
	public void ajouterFormation() {
	try {
		String nomFormation= inNomFormation.getText();
		String responsableFormation= inResponsableFormation.getText();
		int dureeFormation=1;
		dureeFormation = Integer.parseInt(inDureeFormation.getText());
		Formation formation=new Formation(typeTarget, nomFormation, responsableFormation, dureeFormation);
		Session sessionn = sessionFactory.openSession();
		sessionn.beginTransaction();
		sessionn.save(formation);
		sessionn.persist(formation);
		sessionn.getTransaction().commit();
		sessionn.close();
		typeTarget.getFormations().add(formation);
		
		String title = "L'operation est effectuée";
        String message = "La Formation a été ajouté correctement";
        Notification notification = Notifications.SUCCESS;
        TrayNotification tray = new TrayNotification(title, message, notification);
        tray.showAndDismiss(new Duration(2000));
        Stage stage = (Stage)inNomFormation.getScene().getWindow();
		stage.close();
		
	}
	catch(Exception e) {
		e.printStackTrace();;
		String title = "L'operation est échouée";
        String message = "La Formation n'a pas été ajouté ";
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
	public Type getTypeTargett() {
		return typeTarget;
	}
	public void setTypeTargett(Type typeTarget) {
		this.typeTarget = typeTarget;
	}

}
