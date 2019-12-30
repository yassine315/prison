package com.ynr.prison.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


import com.ynr.beans.User;
import com.ynr.beans.Visite;
import com.ynr.util.HibernateUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerUsers implements Initializable{
	ObservableList<User> observableList;
	SessionFactory sessionFactory;

	@FXML
	private TableView<User> usersTable;
	
	@FXML
	private TableColumn<Visite,String> emailCol;
	
	@FXML
	private TableColumn<Visite,String> nomCol;
	
	@FXML
	private TableColumn<Visite,String> prenomCol;
	
	@FXML
	private TableColumn<Visite,String> loginCol;
	
	@FXML
	private TableColumn<Visite,String> mpCol;
	
	
	@FXML
	private void ajouterUser() {
		
		Stage currentStage = (Stage)usersTable.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource("AjouterUser.fxml"));
		Region anchorPane = new Region();
		try {
			anchorPane = loader.load();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(anchorPane);
		Stage stage = new Stage();
		stage.setTitle("Ajouter Utilisateur");
		stage.setScene(scene);
		
		stage.initOwner(currentStage);
		stage.initModality(Modality.APPLICATION_MODAL); 
		stage.showAndWait();
		
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		sessionFactory = HibernateUtil.getSessionFactory();
		 
		 Session session = sessionFactory.openSession();
		 session.beginTransaction();
		 observableList = FXCollections.observableList( session.createQuery("FROM User", User.class).getResultList());
		 session.getTransaction().commit();
		 session.close();
		 
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		loginCol.setCellValueFactory(new PropertyValueFactory<>("login"));
		mpCol.setCellValueFactory(new PropertyValueFactory<>("password"));


		
		 usersTable.setItems(observableList);

	        
	        

	}


}
