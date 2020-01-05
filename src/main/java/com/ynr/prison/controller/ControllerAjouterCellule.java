package com.ynr.prison.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ynr.beans.Cellule;
import com.ynr.beans.Type;
import com.ynr.prison.nitification.Notification;
import com.ynr.prison.nitification.Notifications;
import com.ynr.prison.nitification.TrayNotification;
import com.ynr.util.HibernateUtil;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControllerAjouterCellule implements Initializable{
	SessionFactory sessionFactory;
	private TableView<com.ynr.beans.Cellule> celluleTable;
	@FXML 
	private TextField inNomCel;
	@FXML
	private TextField inTypeCel;
	
	public void ajouterCel() {
		try {
			String typeCel= inTypeCel.getText();
			String nomCel= inNomCel.getText();
			Cellule cellule=new Cellule(nomCel, typeCel);
			Session sessionn = sessionFactory.openSession();
			sessionn.beginTransaction();
			sessionn.save(cellule);
			sessionn.persist(cellule);
			sessionn.getTransaction().commit();
			sessionn.close();
			
			String title = "L'operation est effectuée";
	        String message = "Le Cellule a été ajouté correctement";
	        Notification notification = Notifications.SUCCESS;
	        TrayNotification tray = new TrayNotification(title, message, notification);
	        tray.showAndDismiss(new Duration(2000));
	        Stage stage = (Stage)inNomCel.getScene().getWindow();
			stage.close();
			
			
		}
		catch(Exception e) {
			e.printStackTrace();;
			String title = "L'operation est échouée";
	        String message = "La Cellule n'a pas été ajouté ";
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
	public TableView<com.ynr.beans.Cellule> getCelluleTable() {
		return celluleTable;
	}
	public void setCelluleTable(TableView<com.ynr.beans.Cellule> celluleTable) {
		this.celluleTable = celluleTable;
	}
	

}
