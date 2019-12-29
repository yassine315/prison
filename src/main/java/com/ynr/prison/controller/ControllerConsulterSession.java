package com.ynr.prison.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ynr.beans.Formation;
import com.ynr.beans.Session;
import com.ynr.beans.Sinscrire;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerConsulterSession implements Initializable {
	
	private Session session;
	
	@FXML
	private Label nomSession;
	
	@FXML
	private TableView<Sinscrire> sessionTable;
	
	@FXML
	private TableColumn<Sinscrire, String> matriculeCol;
	
	@FXML
	private TableColumn<Sinscrire, String> nomCol;
	
	@FXML
	private TableColumn<Sinscrire, String> prenomCol;
	
	@FXML
	private TableColumn<Sinscrire, String> abscenceCol;
	
	@FXML
	private TableColumn<Sinscrire, String> scorCol;
	
	@FXML
	private TableColumn<Sinscrire, String> decisionCol;
	
	@FXML
	private void ajouterInscription() {
		
		Stage currentStage = (Stage) sessionTable.getScene().getWindow();
		
		FXMLLoader loader= new FXMLLoader(this.getClass().getClassLoader().getResource("AjouterInscription.fxml"));
		Region anchorPane = new AnchorPane();
		try {
			anchorPane = loader.load();
			anchorPane.getStylesheets().add(getClass().getClassLoader().getResource("FlatBee.css").toString());
			ControllerAjouterInscripetion cai = loader.getController();
			cai.setSession(session);
			cai.setSessionTable(sessionTable);
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
			nomSession.setText(session.getSession());
			
			matriculeCol.setCellValueFactory(p -> new ReadOnlyStringWrapper(""+p.getValue().getPrisonnier().getMatricule()));
			nomCol.setCellValueFactory(p -> new ReadOnlyStringWrapper(p.getValue().getPrisonnier().getNom()));
			prenomCol.setCellValueFactory(p -> new ReadOnlyStringWrapper(p.getValue().getPrisonnier().getPrenom()));
			abscenceCol.setCellValueFactory(new PropertyValueFactory<>("abscence"));
			scorCol.setCellValueFactory(new PropertyValueFactory<>("score"));
			decisionCol.setCellValueFactory(new PropertyValueFactory<>("decision"));

			ObservableList<Sinscrire> observableList = FXCollections.observableList(session.getPrisonniers());
			sessionTable.setItems(observableList);
			
		});
		
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	
}
