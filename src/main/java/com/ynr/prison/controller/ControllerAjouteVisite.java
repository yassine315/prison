package com.ynr.prison.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ynr.beans.Prisonnier;
import com.ynr.beans.Visite;
import com.ynr.beans.Visiteur;
import com.ynr.prison.nitification.Notification;
import com.ynr.prison.nitification.Notifications;
import com.ynr.prison.nitification.TrayNotification;
import com.ynr.util.HibernateUtil;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControllerAjouteVisite implements Initializable {
	
	SessionFactory sessionFactory;
	Prisonnier prisonnier;
	private TableView<Visite> tableVisite ;
	
	@FXML
	private AnchorPane anchorPaneAV;
	
	@FXML
	private TextField inNon;
	
	@FXML
	private TextField inPrenom;
	
	@FXML
	private TextField inLien;
	
	@FXML
	private TextField inCin;
	
	@FXML
	private ComboBox<String> nbrVisiteur;
	
	
	@FXML
	private void ajouterVisiteur() {
		try {
		String nom = inNon.getText();
		String prenom = inPrenom.getText();
		String lien = inLien.getText();
		String cin = inCin.getText();
		int nbrv = 1;
		nbrv = Integer.parseInt(nbrVisiteur.getValue());
		Visiteur visiteur=new Visiteur(nom,prenom,lien,cin);
		Visite newVisite = new Visite(LocalDate.now(), LocalTime.now(), prisonnier, nbrv, visiteur);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(visiteur);
		session.persist(visiteur);
		session.save(newVisite);
		session.persist(newVisite);
		session.getTransaction().commit();
		session.close();
		prisonnier.getVisites().add(newVisite);
		tableVisite.refresh();
		
		String title = "L'operation est effectuée";
        String message = "La Visite a été ajouté correctement";
        Notification notification = Notifications.SUCCESS;
        TrayNotification tray = new TrayNotification(title, message, notification);
        tray.showAndDismiss(new Duration(2000));
		}catch(Exception e) {
			e.printStackTrace();;
			String title = "L'operation est échouée";
	        String message = "La Visite n'a pas été ajouté ";
	        Notification notification = Notifications.ERROR;
	        TrayNotification tray = new TrayNotification(title, message, notification);
	        tray.showAndDismiss(new Duration(2000));
		}
		Stage stage = (Stage)nbrVisiteur.getScene().getWindow();
		stage.close();
	}
	
	
	@FXML
	private void annullerVisite() {
		anchorPaneAV.getChildren().clear();
		Stage stage = (Stage)anchorPaneAV.getScene().getWindow();
		stage.close();
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		sessionFactory = HibernateUtil.getSessionFactory();
		nbrVisiteur.setTooltip(new Tooltip());
		nbrVisiteur.getItems().addAll(new String[] {"2","3","4"});
		
	}


	public Prisonnier getPrisonnier() {
		return prisonnier;
	}


	public void setPrisonnier(Prisonnier prisonnier) {
		this.prisonnier = prisonnier;
	}


	public TableView<Visite> getTableVisite() {
		return tableVisite;
	}


	public void setTableVisite(TableView<Visite> tableVisite) {
		this.tableVisite = tableVisite;
	}
	
	
}
