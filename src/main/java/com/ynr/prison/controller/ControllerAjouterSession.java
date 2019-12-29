package com.ynr.prison.controller;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ynr.beans.Formation;
import com.ynr.prison.nitification.Notification;
import com.ynr.prison.nitification.Notifications;
import com.ynr.prison.nitification.TrayNotification;
import com.ynr.util.HibernateUtil;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControllerAjouterSession implements Initializable {
	private SessionFactory sessionFactory;
	private Formation formationTarget;
	private TableView<com.ynr.beans.Session> sessionTable;
	
	@FXML
	private TextField inSession;
	
	@FXML
	private TextField formateur;
	
	@FXML
	private DatePicker dateDebut;

	@FXML
	private DatePicker dateFin;
	
	@FXML
	private void ajouterSession() {
		
		try {
		String formateurS = formateur.getText();
		String sessionS = inSession.getText();
		LocalDate localDateD = dateDebut.getValue();
		Instant debutN = Instant.from(localDateD.atStartOfDay(ZoneId.systemDefault()));
		Date debut = Date.from(debutN);
		
		LocalDate localDateF = dateDebut.getValue();
		Instant debutF = Instant.from(localDateF.atStartOfDay(ZoneId.systemDefault()));
		Date fin = Date.from(debutF);
		
		sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		com.ynr.beans.Session s = new com.ynr.beans.Session(formationTarget, sessionS, formateurS,debut, fin);
		session.save(s);
		formationTarget = session.find(Formation.class, formationTarget.getId_formation());
		session.persist(formationTarget);
		session.getTransaction().commit();
		session.close();
		String title = "L'operation est effectuée";
        String message = "Votre Session a été ajouté correctement";
        Notification notification = Notifications.SUCCESS;
        TrayNotification tray = new TrayNotification(title, message, notification);
        tray.showAndDismiss(new Duration(2000));
        Stage stage = ((Stage)dateFin.getScene().getWindow());
        stage.close();
        sessionTable.refresh();
        
		}catch(Exception e) {
			e.printStackTrace();
			String title = "L'operation est échouée";
	        String message = "Votre Session n'a pas été ajouté ";
	        Notification notification = Notifications.ERROR;
	        TrayNotification tray = new TrayNotification(title, message, notification);
	        tray.showAndDismiss(new Duration(2000));
		}
		Stage s = (Stage)dateFin.getScene().getWindow();
		s.close();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public Formation getFormationTarget() {
		return formationTarget;
	}

	public void setFormationTarget(Formation formationTarget) {
		this.formationTarget = formationTarget;
	}

	public TableView<com.ynr.beans.Session> getSessionTable() {
		return sessionTable;
	}

	public void setSessionTable(TableView<com.ynr.beans.Session> sessionTable) {
		this.sessionTable = sessionTable;
	}

	
	
	
}
