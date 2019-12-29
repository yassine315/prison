package com.ynr.prison.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ynr.beans.Deces;
import com.ynr.beans.Prisonnier;
import com.ynr.beans.Visite;
import com.ynr.util.ComboboxNiama;
import com.ynr.util.HibernateUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerDeces implements Initializable {
	ObservableList<Deces> observableList;
	SessionFactory sessionFactory;
	
	@FXML
	private TextField inMatricule;
	
	@FXML
	private TableView<Deces> decesTable;
	
	@FXML
	private TableColumn<Visite,String> matriculeCol;
	
	@FXML
	private TableColumn<Visite,String> dateCol;
	
	@FXML
	private TableColumn<Visite,String> nomCol;
	
	@FXML
	private TableColumn<Visite,String> prenomCol;
	
	@FXML
	private TableColumn<Visite,String> cinCol;
	
	@FXML
	private TableColumn<Visite,String> raisonCol;
	
	
	@FXML
	private void ajouterDeces() {
		
		Stage currentStage = (Stage)decesTable.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource("AjouterDeces.fxml"));
		Region anchorPane = new Region();
		try {
			anchorPane = loader.load();
			anchorPane.getStylesheets().add(getClass().getClassLoader().getResource("FlatBee.css").toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(anchorPane);
		Stage stage = new Stage();
		stage.setTitle("Ajouter Déces");
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
		 observableList = FXCollections.observableList( session.createQuery("FROM Deces", Deces.class).getResultList());
		 session.getTransaction().commit();
		 session.close();
		 
		dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		raisonCol.setCellValueFactory(new PropertyValueFactory<>("raison"));
		cinCol.setCellValueFactory(new PropertyValueFactory<>("cin"));
		matriculeCol.setCellValueFactory(new PropertyValueFactory<>("idDeces"));

		
		 decesTable.setItems(observableList);

		 
		 

		 FilteredList<Deces> filteredData = new FilteredList<>(observableList, p -> true);
	        
	        // 2. Set the filter Predicate whenever the filter changes.
	        inMatricule.textProperty().addListener((observableList, oldValue, newValue) -> {
	            filteredData.setPredicate(deces -> {
	                // If filter text is empty, display all persons.
	                if (newValue == null || newValue.isEmpty()) {
	                    return true;
	                }
	                
	                // Compare first name and last name of every person with filter text.
	                String lowerCaseFilter = newValue.toLowerCase();
	                
	                if ((""+deces.getIdDeces()).toLowerCase().contains(lowerCaseFilter)) {
	                    return true; // Filter matches first name.
	                } 
	                return false; // Does not match.
	            });
	        });
	        
	        // 3. Wrap the FilteredList in a SortedList. 
	        SortedList<Deces> sortedData = new SortedList<>(filteredData);
	        
	        // 4. Bind the SortedList comparator to the TableView comparator.
	        sortedData.comparatorProperty().bind(decesTable.comparatorProperty());
	        
	        // 5. Add sorted (and filtered) data to the table.
	        decesTable.setItems(sortedData);

	}

}
