package com.ynr.prison.controller;

import com.ynr.beans.Formation;
import com.ynr.beans.Prisonnier;
import com.ynr.beans.Type;
import com.ynr.prison.nitification.Notification;
import com.ynr.prison.nitification.Notifications;
import com.ynr.prison.nitification.TrayNotification;
import com.ynr.util.HibernateUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControllerFormation implements Initializable {
	
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private Type typeTarget;
	
	 @FXML
	 private TableView<Formation> formationTable;
	 
	 @FXML
	 private TableColumn<Formation,String> nomFormationCol;
	 
	 @FXML
	 private TableColumn<Formation,String> dureFormationCol;
	 
	 @FXML
	 private TableColumn<Formation,String> responsableCol;
	
	 @FXML
	 private Label titreFormation;

	
	@FXML 
	private Button nouvelleFormation;

	@FXML
private void ajouterFormation() {

	Stage currentStage = (Stage) nouvelleFormation.getScene().getWindow();

	FXMLLoader loader= new FXMLLoader(this.getClass().getClassLoader().getResource("AjouterFormation.fxml"));
AnchorPane anchorPane = new AnchorPane();
	try {
		anchorPane = loader.load();
		ControllerAjouterFormation cv =loader.getController();
		cv.setTypeTargett(typeTarget);
	
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
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		Platform.runLater(()->{
			titreFormation.setText(typeTarget.getNomType());
			
			nomFormationCol.setCellValueFactory(new PropertyValueFactory<>("nomFormation"));
			dureFormationCol.setCellValueFactory(new PropertyValueFactory<>("dureeFormation"));
			responsableCol.setCellValueFactory(new PropertyValueFactory<>("responsableFormation"));
			ObservableList<Formation> observableList = FXCollections.observableList(typeTarget.getFormations());
			formationTable.setItems(observableList);
		});
		
		formationTable.setRowFactory(tv -> {
		    TableRow<Formation> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY  && event.getClickCount() == 2) {
		            Formation prRow = row.getItem();
					try {
			            Stage currentStage = (Stage) formationTable.getScene().getWindow();
			            FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource("ConsulterFormation.fxml"));
			    		Region pane = new Region();
			            try {
			    			pane = loader.load();
			    			pane.getStylesheets().add(getClass().getClassLoader().getResource("FlatBee.css").toString());
			    			ControllerConsulterFormation ccf = loader.getController();
			    			ccf.setFormation(prRow);
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
	public Type getTypeTarget() {
		return typeTarget;
	}
	public void setTypeTarget(Type typeTarget) {
		this.typeTarget = typeTarget;
	}

}
