 package com.ynr.prison.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ynr.beans.Cause;
import com.ynr.beans.Prisonnier;
import com.ynr.prison.nitification.Notification;
import com.ynr.prison.nitification.Notifications;
import com.ynr.prison.nitification.TrayNotification;
import com.ynr.util.ComboboxNiama;
import com.ynr.util.HibernateUtil;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControllerAjouterPrisonnier implements Initializable {
	
	private File image;
	private SessionFactory sessionFactory;
	private Session session;
	private Map<String, Cause> causes;
	
	@FXML
	private TextField inCin;
	
	@FXML
	private TextField inNom;
	
	@FXML
	private TextField inPrenom;
	
	@FXML
	private DatePicker inNaissance;
	
	@FXML
	private TextField inPeriode;
	
	@FXML
	private DatePicker inDateEntrer;
	
	@FXML
	private TextField inNbrEtude;
	
	@FXML
	private ComboBox<String> inCause;
	
	@FXML
	private Button addImage;
	
	@FXML
	private void ajouterPrisonnier() {
		String cin = inCin.getText();
		String nom  = inNom.getText();
		String prenom = inPrenom.getText();
		LocalDate localDateN = inNaissance.getValue();
		Instant instantN = Instant.from(localDateN.atStartOfDay(ZoneId.systemDefault()));
		Date naissance = Date.from(instantN);
		
		int periode =  Integer.parseInt(inPeriode.getText());
		
		LocalDate localDate = inDateEntrer.getValue();
		Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
		Date dateEntrer = Date.from(instant);
		
		int nbrEtude = Integer.parseInt(inNbrEtude.getText());
		Cause cause = causes.get(inCause.getValue());
		
		//traitment d'image
		
		Blob blobImage = null;
		try {
			byte[] fileContent = Files.readAllBytes(image.toPath());
			blobImage = new SerialBlob(fileContent);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Prisonnier prisonnier = new Prisonnier( cin, cause, nom, prenom, naissance, periode,
				dateEntrer, nbrEtude,  true, blobImage);
			
		try {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(prisonnier);
		session.getTransaction().commit();
		session.close();
		Stage stage = (Stage)addImage.getScene().getWindow();
		stage.close();
		
		/**
		 * notifier la reussite
		 */
		String title = "L'operation est effectuée";
        String message = "Votre prisonnier a été ajouté correctement";
        Notification notification = Notifications.SUCCESS;
        TrayNotification tray = new TrayNotification(title, message, notification);
        tray.showAndDismiss(new Duration(2000));
		}catch(Exception e){
			String title = "L'operation est échouée";
	        String message = "Votre prisonnier n'a pas été ajouté ";
	        Notification notification = Notifications.ERROR;
	        TrayNotification tray = new TrayNotification(title, message, notification);
	        tray.showAndDismiss(new Duration(2000));
		}
	}
	
	@FXML
	private void annuller() {
		
		Stage stage = (Stage)addImage.getScene().getWindow();
		stage.close();
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		/**
		 * initialise la ssesion 
		 */
		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		causes = session.createQuery("FROM Cause", Cause.class).getResultList().stream().collect(Collectors.toMap(Cause::getNom, e ->e ));
		session.getTransaction().commit();
		
		/**
		 * configurer comboBox
		 */
		inCause.setTooltip(new Tooltip());
		inCause.getItems().addAll(causes.keySet().toArray(new String[causes.size()]));
		new ComboboxNiama<String>(inCause);
		
		FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png","*.jpeg");
		FileChooser fc = new FileChooser();
	    fc.getExtensionFilters().add(imageFilter);
	    
	    addImage.setOnAction(e -> {
            image = fc.showOpenDialog(new Stage());
        });
	}

}
