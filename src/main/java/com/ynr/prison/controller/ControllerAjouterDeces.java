package com.ynr.prison.controller;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ynr.beans.Deces;
import com.ynr.beans.Prisonnier;
import com.ynr.util.ComboboxNiama;
import com.ynr.util.HibernateUtil;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class ControllerAjouterDeces implements Initializable {
	
	private SessionFactory sessionFactory;
	private Map<String, Prisonnier> prisonniers;
	
	@FXML
	private ComboBox<String> cmb1;
	
	@FXML
	private TextField inRaison;
	
	@FXML
	private DatePicker date;
	
	@FXML
	private void ajouterDeces() {
		
		String key = cmb1.getValue();
		Prisonnier prisonnier = prisonniers.get(key);
		String raison = inRaison.getText();
		LocalDate localDateD = date.getValue();
		Instant debutN = Instant.from(localDateD.atStartOfDay(ZoneId.systemDefault()));
		Date dateD = Date.from(debutN);
		
		Deces deces = new Deces(prisonnier.getMatricule(), prisonnier.getNom(), prisonnier.getPrenom(), prisonnier.getCinPrisonnier(), raison, dateD);
		
		sessionFactory = HibernateUtil.getSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
		session.save(deces);
		session.remove(prisonnier);
		session.getTransaction().commit();
		session.close();
		
		Stage stage = (Stage)cmb1.getScene().getWindow();
		stage.close();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		sessionFactory = HibernateUtil.getSessionFactory();
		 
		 Session session = sessionFactory.openSession();
		 session.beginTransaction();
		 prisonniers =  session.createQuery("FROM Prisonnier", Prisonnier.class).getResultList().stream().collect(Collectors.toMap(e -> ((Prisonnier)e).getMatricule()+((Prisonnier)e).getNomComplet(), e -> e));
		 session.getTransaction().commit();
		 session.close();
		 
		 cmb1.setTooltip(new Tooltip());
		 cmb1.getItems().addAll(prisonniers.keySet().toArray(new String[prisonniers.size()]));
		 
		 new ComboboxNiama<String>(cmb1);
	}

}
