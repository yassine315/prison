package com.ynr.prison.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.ynr.beans.Prisonnier;
import com.ynr.prison.nitification.Notification;
import com.ynr.prison.nitification.Notifications;
import com.ynr.prison.nitification.TrayNotification;
import com.ynr.util.HibernateUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControllerPrisonnier implements Initializable {
	
	private SessionFactory sessionFactory;
	
	private List<Prisonnier> prisonniers;
	
	@FXML 
	private TableView<Prisonnier> prisonnierTable;
	
	@FXML
	private TableColumn<Prisonnier,String> nomCol;
	
	@FXML
	private TableColumn<Prisonnier,String> prenomCol;
	
	@FXML
	private TableColumn<Prisonnier,String> ageCol;
	
	@FXML
	private TableColumn<Prisonnier,String> peroideCol;
	
	@FXML
	private TableColumn<Prisonnier,String> dateEntrerCol;
	
	@FXML
	private TableColumn<Prisonnier,String> niveauCol;
	
	@FXML
	private TableColumn<Prisonnier,String> evaluationCol;
	
	@FXML
	private TableColumn<Prisonnier,String> detenuCol;
	
	@FXML
	private TableColumn<Prisonnier,String> causeCol;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		//la creation de l'objet criteria
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Prisonnier> criteriaQuery = criteriaBuilder.createQuery(Prisonnier.class);
		
		Root<Prisonnier> root = criteriaQuery.from(Prisonnier.class);
		criteriaQuery.where(criteriaBuilder.isTrue(root.get("detenu").as(boolean.class)));
		criteriaQuery.select(root);
		
		Query<Prisonnier> query = session.createQuery(criteriaQuery);
		prisonniers = query.list();
		session.getTransaction().commit();
		session.close();
		
		nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
		peroideCol.setCellValueFactory(new PropertyValueFactory<>("periode"));
		dateEntrerCol.setCellValueFactory(new PropertyValueFactory<>("dateEntrer"));
		niveauCol.setCellValueFactory(new PropertyValueFactory<>("niveauEtude"));
		evaluationCol.setCellValueFactory(new PropertyValueFactory<>("evaluation"));
		detenuCol.setCellValueFactory(new PropertyValueFactory<>("detenu"));
		causeCol.setCellValueFactory(new PropertyValueFactory<>("cause"));
		
		prisonnierTable.setRowFactory(tv -> {
		    TableRow<Prisonnier> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY  && event.getClickCount() == 2) {
		            Prisonnier prRow = row.getItem();
		            InputStream in = null;
					try {
						in = prRow.getPhoto().getBinaryStream();
						Image imageDetail = new Image(in);
			            ImageView imageView = new ImageView(imageDetail);
			            Stage currentStage = (Stage) prisonnierTable.getScene().getWindow();
			    		Pane pane = new AnchorPane(imageView);
			    		Scene scene = new Scene(pane);
			    		Stage stage = new Stage();
			    		stage.setScene(scene);
			    		
			    		stage.initOwner(currentStage);
			    		stage.initModality(Modality.APPLICATION_MODAL); 
			    		stage.showAndWait();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						
						String title = "L'image n'a pas pu afficher";
				        String message = "erreur : affecter à ce prisonnier une image ";
				        Notification notification = Notifications.ERROR;
				        TrayNotification tray = new TrayNotification(title, message, notification);
				        tray.showAndDismiss(new Duration(2000));
					}
					
		            
		        }
		    });
		    return row ;
		});
		
		ObservableList<Prisonnier> observableList = FXCollections.observableList(prisonniers);
		prisonnierTable.setItems(observableList);
		
	}

}
