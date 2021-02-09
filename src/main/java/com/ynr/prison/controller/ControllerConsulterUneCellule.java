package com.ynr.prison.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ynr.beans.Cellule;
import com.ynr.beans.Prisonnier;
import com.ynr.util.HibernateUtil;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class ControllerConsulterUneCellule implements Initializable {
	
	private Cellule cellule;

	@FXML
	private Label nomCellule;
	
	@FXML
	private TableView<Prisonnier> prisonnierTable;
	
	@FXML
	private TableColumn<Prisonnier, String> matriculeCol;
	
	@FXML
	private TableColumn<Prisonnier, String> nomCol;
	
	@FXML
	private TableColumn<Prisonnier, String> prenomCol;
	

	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Platform.runLater(()->{
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			nomCellule.setText(cellule.getNom());
			matriculeCol.setCellValueFactory(p -> new ReadOnlyStringWrapper(""+p.getValue().getMatricule()));
			nomCol.setCellValueFactory(p -> new ReadOnlyStringWrapper(p.getValue().getNom()));
			prenomCol.setCellValueFactory(p -> new ReadOnlyStringWrapper(p.getValue().getPrenom()));
			ObservableList<Prisonnier> observableList = FXCollections.observableList(cellule.getPrisonniers());
			prisonnierTable.setItems(observableList);
			
		});
		

	}


	public Cellule getCellule() {
		return cellule;
	}


	public void setCellule(Cellule cellule) {
		this.cellule=cellule;
	}

}
