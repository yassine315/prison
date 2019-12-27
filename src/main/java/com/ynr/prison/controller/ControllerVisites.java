 package com.ynr.prison.controller;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ynr.beans.Prisonnier;
import com.ynr.beans.Visite;
import com.ynr.util.ComboboxNiama;
import com.ynr.util.HibernateUtil;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerVisites implements Initializable {
	
	SessionFactory sessionFactory;
	Stage currentStage;
	Prisonnier target;
	
	private AnchorPane anchorPane;
	
	private Map< String, Prisonnier>  prisonniers; 
	
	@FXML
	private ComboBox<String> cmb1;
	
	@FXML
	private Button nouveauVisiteur;
	
	@FXML
	private Button ancienVisiteur;
	
	@FXML
	private TextField nom;
	
	@FXML
	private TextField prenom;
	
	@FXML
	private Button rechercher;
	
	@FXML
	private TableView<Visite> tableVisite;
	
	@FXML
	private TableColumn<Visite,String> dateCol;
	
	@FXML
	private TableColumn<Visite,String> nomCol;
	
	@FXML
	private TableColumn<Visite,String> prenomCol;
	
	@FXML
	private TableColumn<Visite,String> lienCol;
	
	@FXML
	private TableColumn<Visite,String> cinCol;
	
	@FXML
	private TableColumn<Visite,String> heureCol;
	 
	@FXML
	private TableColumn<Visite,String> nbrVisiteursCol;
	 
	@FXML
	private void ajouterVisite() {
		
		
		FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource("AjouterVisite.fxml"));
		try {
			anchorPane = loader.load();
			ControllerAjouteVisite cav = loader.getController();
			cav.setPrisonnier(target);
			cav.setTableVisite(tableVisite);
		} catch (Exception e) {
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
		private void rechercher() {
			currentStage = (Stage) nouveauVisiteur.getScene().getWindow();
			String nomComplet = cmb1.getValue();
			target = prisonniers.get(nomComplet);
			ancienVisiteur.setDisable(false);
			nouveauVisiteur.setDisable(false);
			
			dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
			nomCol.setCellValueFactory( p -> new ReadOnlyStringWrapper(p.getValue().getVisiteur().getNom()));
			prenomCol.setCellValueFactory( p -> new ReadOnlyStringWrapper(p.getValue().getVisiteur().getPrenom()));
			lienCol.setCellValueFactory( p -> new ReadOnlyStringWrapper(p.getValue().getVisiteur().getLienparente()));
			cinCol.setCellValueFactory( p -> new ReadOnlyStringWrapper(p.getValue().getVisiteur().getCin()));
			heureCol.setCellValueFactory(new PropertyValueFactory<>("heure"));
			nbrVisiteursCol.setCellValueFactory(new PropertyValueFactory<>("nbrVisiteur"));
			 
			 
			 
			ObservableList<Visite> observableList = FXCollections.observableList(target.getVisites());
			 tableVisite.setItems(observableList);
			
		}
	
 @FXML
 private void ancienVisiteur(){
	
	 
		FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource("AncienVisiteur.fxml"));
		Region anchorPaneAncien = new Region();
		try {
			anchorPaneAncien= loader.load();
			ControllerAncienVisiteur cav = loader.getController();
			cav.setPrisonnier(target);
			cav.setTableVisite(tableVisite);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		Scene sceneAncien = new Scene(anchorPaneAncien);
		Stage stageAncien= new Stage();
		stageAncien.setScene(sceneAncien);
		stageAncien.initOwner(currentStage);
		stageAncien.initModality(Modality.APPLICATION_MODAL); 
		stageAncien.showAndWait();
		
 }
 
@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	

	 nouveauVisiteur.setDisable(true);
	 ancienVisiteur.setDisable(true);
	 
	 
	 sessionFactory = HibernateUtil.getSessionFactory();
	 
	 Session session = sessionFactory.openSession();
	 session.beginTransaction();
	 prisonniers =  session.createQuery("FROM Prisonnier", Prisonnier.class).getResultList().stream().collect(Collectors.toMap(e -> ((Prisonnier)e).getNomComplet(), e -> e));
	 session.getTransaction().commit();
	 session.close();
	 
	 cmb1.setTooltip(new Tooltip());
	 cmb1.getItems().addAll(prisonniers.keySet().toArray(new String[prisonniers.size()]));
	 
	 new ComboboxNiama<String>(cmb1);
	 /*
	 Session session = sessionFactory.openSession();
	 session.beginTransaction();
	 listVisite = session.createQuery("FROM Visite").getResultList();
	 
	 session.getTransaction().commit();
	 session.close();
	 
	 dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
	 nomCol.setCellValueFactory( p -> new ReadOnlyStringWrapper(p.getValue().getVisiteur().getNom()));
	 prenomCol.setCellValueFactory( p -> new ReadOnlyStringWrapper(p.getValue().getVisiteur().getPrenom()));
	 lienCol.setCellValueFactory( p -> new ReadOnlyStringWrapper(p.getValue().getVisiteur().getLienparente()));
	 cinCol.setCellValueFactory( p -> new ReadOnlyStringWrapper(p.getValue().getVisiteur().getCin()));
	 heureCol.setCellValueFactory(new PropertyValueFactory<>("heure"));
	 nbrVisiteursCol.setCellValueFactory(new PropertyValueFactory<>("nbrVisiteur"));
	 
	 
	 
	 ObservableList<Visite> observableList = FXCollections.observableList(listVisite);
	 tableVisite.setItems(observableList);
	 */
}
}
