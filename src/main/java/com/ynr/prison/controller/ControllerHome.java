
package com.ynr.prison.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ynr.beans.Type;

import com.ynr.prison.nitification.Notification;
import com.ynr.prison.nitification.Notifications;
import com.ynr.prison.nitification.TrayNotification;
import com.ynr.util.HibernateUtil;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerHome implements Initializable {
	
	private SessionFactory sessionFactory;
	private Type typeTarget;
	
	@FXML
	private AnchorPane container;
	
	@FXML
	private Menu menuFormation;
	
	@FXML
	private void nouveauType() {
		
		Stage currentStage = (Stage) container.getScene().getWindow();

		FXMLLoader loader= new FXMLLoader(this.getClass().getClassLoader().getResource("AjouterType.fxml"));
	Region anchorPane = new Region();
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	Scene	scene = new Scene(anchorPane);
	Stage	stage = new Stage();
		stage.setScene(scene);
		
		stage.initOwner(currentStage);
		stage.initModality(Modality.APPLICATION_MODAL); 
		stage.showAndWait();
		
		
		
		

		
	}
	
	@FXML
	private void description() {
		FXMLLoader loader= new FXMLLoader(this.getClass().getClassLoader().getResource("description.fxml"));
		
		Region newContainer = new Region();
		try {
			newContainer = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		container.getChildren().clear();
		container.getChildren().add(newContainer);
		newContainer.prefHeightProperty().bind(container.heightProperty());
		newContainer.prefWidthProperty().bind(container.widthProperty());
	}
	
	@FXML
	private void formationNSS() {
		
	}
	
	@FXML
	private void formationProfessionnelle() {
		
	}
	
	
	@FXML
	private void prisonnier() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource("Prisonnier.fxml"));
		
		Region newContainer = new Region();
		
		try {
			newContainer =(Region) loader.load();
			newContainer.getStylesheets().add(getClass().getClassLoader().getResource("FlatBee.css").toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		container.getChildren().clear();
		container.getChildren().add(newContainer);
		
		newContainer.prefHeightProperty().bind(container.heightProperty());
		newContainer.prefWidthProperty().bind(container.widthProperty());
		
	}
	
	@FXML
	private void ajouterPrisonnier() {
		Stage currentStage = (Stage) container.getScene().getWindow();
		
		
		FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource("AjouterPrisonnier.fxml"));
		AnchorPane anchorPane = new AnchorPane();
		try {
			anchorPane = loader.load();
			anchorPane.getStylesheets().add(getClass().getClassLoader().getResource("FlatBee.css").toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(anchorPane);
		Stage stage = new Stage();
		stage.setScene(scene);
		
		stage.initOwner(currentStage);
		stage.initModality(Modality.APPLICATION_MODAL); 
		stage.showAndWait();
	}
	
	@FXML
	private void libererPrisonnier() {
		
FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource("LibererPrisonnier.fxml"));
		
		Region newContainer = new Region();
		
		try {
			newContainer =(Region) loader.load();
			newContainer.getStylesheets().add(getClass().getClassLoader().getResource("FlatBee.css").toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		container.getChildren().clear();
		container.getChildren().add(newContainer);
		
		newContainer.prefHeightProperty().bind(container.heightProperty());
		newContainer.prefWidthProperty().bind(container.widthProperty());
		
		
	}
	
	@FXML
	private void visite() {
		
	FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource("Visites.fxml"));
	Region newContainer = new Region();
	
	try {
		newContainer = (Region) loader.load();
		newContainer.getStylesheets().add(getClass().getClassLoader().getResource("FlatBee.css").toString());

	}
	catch (IOException e){
		e.printStackTrace();
	}
	container.getChildren().clear();
	container.getChildren().add(newContainer);
	newContainer.prefHeightProperty().bind(container.heightProperty());
	newContainer.prefWidthProperty().bind(container.widthProperty());
	}
	
	@FXML
	private void deces() {
		
	}
	
	@FXML
	private void formationDeBase() {
		
		FXMLLoader loader= new FXMLLoader(this.getClass().getClassLoader().getResource("Formation.fxml"));
		
		Region newContainer = new Region();
		try {
			newContainer = loader.load();
			ControllerFormation cav = loader.getController();
			cav.setTypeTarget(typeTarget);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		container.getChildren().clear();
		container.getChildren().add(newContainer);
		newContainer.prefHeightProperty().bind(container.heightProperty());
		newContainer.prefWidthProperty().bind(container.widthProperty());
	
		
		
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Type> types = session.createQuery("FROM Type",Type.class).getResultList();
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		for(Type t : types) {
			MenuItem menuItem = new MenuItem(t.getNomType());
			menuItem.setOnAction(e->{
				typeTarget = t;
				formationDeBase();
				
			});
			menuItems.add(menuItem);
		}
		menuFormation.getItems().addAll(menuItems);
		this.prisonnier();

	}

}










