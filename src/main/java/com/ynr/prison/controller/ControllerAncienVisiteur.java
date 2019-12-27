package com.ynr.prison.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ynr.beans.Prisonnier;
import com.ynr.beans.Visite;
import com.ynr.beans.Visiteur;
import com.ynr.prison.nitification.Notification;
import com.ynr.prison.nitification.Notifications;
import com.ynr.prison.nitification.TrayNotification;
import com.ynr.util.ComboboxNiama;
import com.ynr.util.HibernateUtil;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControllerAncienVisiteur implements Initializable {
	SessionFactory sessionFactory;
	private Prisonnier prisonnier;
	private Map<String,Visiteur> visiteurs ;
	private TableView<Visite> tableVisite ;
	
	@FXML
	private ComboBox<String> cmb;
	
	@FXML
	private ComboBox<String> cmbN;
	
	@FXML
	private void ajouterVisite() {
		try {
		String key = cmb.getValue();
		int nbrVisiteur = 1;		//nombre de visiteur par defaut et 1
		nbrVisiteur = Integer.parseInt(cmbN.getValue());
		Visiteur visiteur = visiteurs.get(key);
		Visite newVisite = new Visite(LocalDate.now(), LocalTime.now(), prisonnier, nbrVisiteur, visiteur);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(newVisite);
		session.getTransaction().commit();
		session.close();
		prisonnier.getVisites().add(newVisite);
		
		String title = "L'operation est effectuée";
        String message = "La Visite a été ajouté correctement";
        Notification notification = Notifications.SUCCESS;
        TrayNotification tray = new TrayNotification(title, message, notification);
        tray.showAndDismiss(new Duration(2000));
        
		tableVisite.refresh();
		}catch(Exception e ) {
			e.printStackTrace();
			String title = "L'operation est échouée";
	        String message = "La Visite n'a pas été ajouté ";
	        Notification notification = Notifications.ERROR;
	        TrayNotification tray = new TrayNotification(title, message, notification);
	        tray.showAndDismiss(new Duration(2000));
			
		}
		
		Stage stage = (Stage)cmbN.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		 sessionFactory = HibernateUtil.getSessionFactory();

		Platform.runLater(() -> {
			visiteurs = new HashMap<String,Visiteur>();
			for(Visite v : prisonnier.getVisites()) {
				visiteurs.put(v.getVisiteur().getNom()+" "+v.getVisiteur().getPrenom()+" "+v.getVisiteur().getCin(),v.getVisiteur());
			}
			String[] LISTA = new String[visiteurs.size()];
			int i = 0;
			for(Visiteur v : visiteurs.values()) {
				LISTA[i] = v.getNom()+" "+v.getPrenom()+" "+v.getCin();
				i++;
			}
			
			cmb.setTooltip(new Tooltip());
			cmb.getItems().addAll(LISTA);
			new ComboboxNiama<String>(cmb);
		

	    });
		
		cmbN.setTooltip(new Tooltip());
		cmbN.getItems().addAll(new String[] {"2","3","4"});
		new ComboboxNiama<String>(cmb);
		
		
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
