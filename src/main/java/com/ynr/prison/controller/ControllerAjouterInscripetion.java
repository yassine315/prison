package com.ynr.prison.controller;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ynr.beans.Prisonnier;
import com.ynr.beans.Sinscrire;
import com.ynr.prison.nitification.Notification;
import com.ynr.prison.nitification.Notifications;
import com.ynr.prison.nitification.TrayNotification;
import com.ynr.util.ComboboxNiama;
import com.ynr.util.HibernateUtil;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControllerAjouterInscripetion implements Initializable {
	
	private com.ynr.beans.Session sessionTarget;
	private SessionFactory sessionFactory;
	private Map<String,Prisonnier> prisonniers;
	private TableView<Sinscrire> sessionTable;
	
	@FXML
	private ComboBox<String> cmb;
	
	@FXML
	private void ajouterInscrit() {
		Stage stage = (Stage)cmb.getScene().getWindow();
		try {
		System.out.println(sessionTarget.getPrisonniers().size());
		String key = cmb.getValue();
		Prisonnier prisonnier = prisonniers.get(key);
		Session session = sessionFactory.openSession();
		sessionTarget.addPrisonnier(prisonnier);

		session.beginTransaction();
		com.ynr.beans.Session s = session.find(com.ynr.beans.Session.class, sessionTarget.getIdSession());
		session.persist(s);
		//session.persist(sessionTarget);
		session.getTransaction().commit();
		session.close();
		
		sessionTable.refresh();
		stage.close();
		String title = "L'operation est effectuée";
        String message = "L'inscription a été ajouté correctement";
        Notification notification = Notifications.SUCCESS;
        TrayNotification tray = new TrayNotification(title, message, notification);
        tray.showAndDismiss(new Duration(2000));
		}catch( Exception e) {
			e.printStackTrace();;
			String title = "L'operation est échouée";
	        String message = "L'inscription n'a pas été ajouté ";
	        Notification notification = Notifications.ERROR;
	        TrayNotification tray = new TrayNotification(title, message, notification);
	        tray.showAndDismiss(new Duration(2000));
	        stage.close();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		 prisonniers =  session.createQuery("FROM Prisonnier", Prisonnier.class).getResultList().stream().collect(Collectors.toMap(e ->""+((Prisonnier)e).getMatricule()+" "+((Prisonnier)e).getNomComplet(), e -> e));

		 cmb.setTooltip(new Tooltip());
		 cmb.getItems().addAll(prisonniers.keySet().toArray(new String[prisonniers.size()]));
		 
		 new ComboboxNiama<String>(cmb);
	}

	public com.ynr.beans.Session getSession() {
		return sessionTarget;
	}

	public void setSession(com.ynr.beans.Session session) {
		this.sessionTarget = session;
	}

	public TableView<Sinscrire> getSessionTable() {
		return sessionTable;
	}

	public void setSessionTable(TableView<Sinscrire> sessionTable) {
		this.sessionTable = sessionTable;
	}
	
	

	
}
