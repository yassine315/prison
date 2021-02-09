package com.ynr.prison.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.ynr.beans.Cellule;
import com.ynr.beans.Deces;
import com.ynr.beans.Formation;
import com.ynr.beans.Prisonnier;
import com.ynr.beans.Type;
import com.ynr.prison.nitification.Notification;
import com.ynr.prison.nitification.Notifications;
import com.ynr.prison.nitification.TrayNotification;
import com.ynr.util.HibernateUtil;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
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
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControllerCellules implements Initializable{

	private SessionFactory sessionFactory;
	private List<Cellule> cellules;
	private Cellule cellule;

	@FXML
	private TableColumn<Cellule,String> nomCol;
	
	@FXML
	private TableColumn<Cellule,String> typeCol;

	@FXML
	private TableView<Cellule> cellulesTable;
	
	@FXML
	private void ajouterCellule() {
		
		Stage currentStage = (Stage)cellulesTable.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource("AjouterCellule.fxml"));
		Region anchorPane = new Region();
		try {
			anchorPane = loader.load();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(anchorPane);
		Stage stage = new Stage();
		stage.setTitle("Ajouter Cellule");
		stage.setScene(scene);
		
		stage.initOwner(currentStage);
		stage.initModality(Modality.APPLICATION_MODAL); 
		stage.showAndWait();
		
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Platform.runLater(()->{
		sessionFactory = HibernateUtil.getSessionFactory();
		 
		 Session session = sessionFactory.openSession();
		 session.beginTransaction();
		 ObservableList<Cellule> observableList = FXCollections.observableList( session.createQuery("FROM Cellule", Cellule.class).getResultList());
		 session.getTransaction().commit();
	nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
	typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

	 cellulesTable.setItems(observableList);

		});
		
		cellulesTable.setRowFactory(tv -> {
		    TableRow<Cellule> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY  && event.getClickCount() == 2) {
		            Cellule prRow = row.getItem();
					try {
			            Stage currentStage = (Stage) cellulesTable.getScene().getWindow();
			            FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource("ConsulterUneCellule.fxml"));
			    		Region pane = new Region();
			            try {
			    			pane = loader.load();
			    			pane.getStylesheets().add(getClass().getClassLoader().getResource("FlatBee.css").toString());
			    			ControllerConsulterUneCellule ccf = loader.getController();
			    			ccf.setCellule(prRow);
			            } catch (Exception e) {
			    			// TODO Auto-generated catch block
			    			e.printStackTrace();
			    		}
			            
			    		Scene scene = new Scene(pane);
			    		Stage stage = new Stage();
			    		stage.setScene(scene);
			    		
			    		stage.initOwner(currentStage);
			    		stage.initModality(Modality.APPLICATION_MODAL); 
			    		stage.showAndWait();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						
						String title = "Erreur";
				        String message = "erreur : répéter ulterieurment ";
				        Notification notification = Notifications.ERROR;
				        TrayNotification tray = new TrayNotification(title, message, notification);
				        tray.showAndDismiss(new Duration(2000));
					}
					
		            
		        }
		    });
		    return row ;
		});

	}


}
