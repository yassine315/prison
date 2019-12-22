package com.ynr.prison.controller;

import java.io.File;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ynr.beans.Prisonnier;
import com.ynr.util.HibernateUtil;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ControllerAjouterPrisonnier implements Initializable {
	
	File image;
	SessionFactory sessionFactory;
	
	@FXML
	private TextField inNom;
	
	@FXML
	private TextField inPrenom;
	
	@FXML
	private TextField inAge;
	
	@FXML
	private TextField inPeriode;
	
	@FXML
	private DatePicker inDateEntrer;
	
	@FXML
	private TextField inNbrEtude;
	
	@FXML
	private TextField inCause;
	
	@FXML
	private Button addImage;
	
	@FXML
	private void ajouterPrisonnier() {
		
		String nom  = inNom.getText();
		String prenom = inPrenom.getText();
		int age = Integer.parseInt(inAge.getText());
		int periode =  Integer.parseInt(inPeriode.getText());
		
		//LocalDate localDate = inDateEntrer.getValue();
		//Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
		//Date dateEntrer = Date.from(instant);
		int nbrEtude = Integer.parseInt(inNbrEtude.getText());
		String cause = inCause.getText();
		
		Prisonnier p = new Prisonnier(111111,0,nom,prenom, age, periode, new Date(), nbrEtude, cause, true );
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(p);
		session.getTransaction().commit();
		session.close();
		
	}
	
	@FXML
	private void annuller() {
		
		
		Stage stage = (Stage)addImage.getScene().getWindow();
		stage.close();
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		sessionFactory = HibernateUtil.getSessionFactory();
		
		FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
		FileChooser fc = new FileChooser();
	    fc.getExtensionFilters().add(imageFilter);
	    
	    addImage.setOnAction(e -> {
            image = fc.showOpenDialog(new Stage());
        });
	}

}
