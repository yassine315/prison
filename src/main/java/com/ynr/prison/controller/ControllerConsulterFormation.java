package com.ynr.prison.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.SessionFactory;

import com.ynr.beans.Formation;
import com.ynr.beans.Session;
import com.ynr.prison.nitification.Notification;
import com.ynr.prison.nitification.Notifications;
import com.ynr.prison.nitification.TrayNotification;
import com.ynr.util.HibernateUtil;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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

public class ControllerConsulterFormation implements Initializable {
	
	private Formation formation;
	
	@FXML
	private AnchorPane container;
	
	@FXML
	private Label nomFormation;
	
	@FXML
	private TableView<Session> sessionTable;
	
	@FXML
	private TableColumn<Session,String> sessionCol;
	
	@FXML
	private TableColumn<Session,String> formateurCol;
	
	@FXML
	private TableColumn<Session,String> nbrInscritCol;
	
	@FXML
	private TableColumn<Session,String> nbrAdmiCol;
	
	@FXML
	private TableColumn<Session,String> nbrExcluCol;
	
	
	@FXML
	private void ajouterSession() {
			Stage currentStage = (Stage) sessionTable.getScene().getWindow();

			FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource("AjouterSession.fxml"));
			Region newAnchorPane= new Region();
			try {
				newAnchorPane=loader.load();
				ControllerAjouterSession cas = loader.getController();
				cas.setFormationTarget(formation);
				cas.setSessionTable(sessionTable);;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Scene scene = new Scene(newAnchorPane);
    		Stage stage = new Stage();
    		stage.setScene(scene);
    		
    		stage.initOwner(currentStage);
    		stage.initModality(Modality.APPLICATION_MODAL); 
    		stage.showAndWait();
		}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		Platform.runLater(()->{
			
			nomFormation.setText(formation.getNomFormation());
			
			sessionCol.setCellValueFactory(new PropertyValueFactory<>("session"));
			formateurCol.setCellValueFactory(new PropertyValueFactory<>("formateur"));
			nbrInscritCol.setCellValueFactory(new PropertyValueFactory<>("nbInscrits"));
			nbrAdmiCol.setCellValueFactory(new PropertyValueFactory<>("nbAdmis"));
			nbrExcluCol.setCellValueFactory(new PropertyValueFactory<>("nbExclu"));

			ObservableList<Session> observableList = FXCollections.observableList(formation.getSessions());
			sessionTable.setItems(observableList);
		});
		
		sessionTable.setRowFactory(tv -> {
		    TableRow<Session> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY  && event.getClickCount() == 2) {
		            Session prRow = row.getItem();
					try {
			            Stage currentStage = (Stage) sessionTable.getScene().getWindow();
			            FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource("ConsulterSession.fxml"));
			    		Region pane = new Region();
			            try {
			    			pane = loader.load();
			    			pane.getStylesheets().add(getClass().getClassLoader().getResource("FlatBee.css").toString());
			    			ControllerConsulterSession ccf = loader.getController();
			    			ccf.setSession(prRow);
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
				        String message = "detail : répéter ulterieurment ";
				        Notification notification = Notifications.ERROR;
				        TrayNotification tray = new TrayNotification(title, message, notification);
				        tray.showAndDismiss(new Duration(2000));
					}
					
		            
		        }
		    });
		    return row ;
		});


	}


	public Formation getFormation() {
		return formation;
	}


	public void setFormation(Formation formation) {
		this.formation = formation;
	}

}
