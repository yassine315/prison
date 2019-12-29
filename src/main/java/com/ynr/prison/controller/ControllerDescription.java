package com.ynr.prison.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import com.ynr.beans.Type;
import com.ynr.util.HibernateUtil;;

public class ControllerDescription implements Initializable {
	
	private SessionFactory sessionFactory;
	private List<Type> types;
	
	@FXML
	private TableView<Type> typeTable;
	
	@FXML
	private TableColumn<Type,String> typeCol;
	
	@FXML
	private TableColumn<Type,String> responsableCol;
	
	@FXML
	private TableColumn<Type,String> descriptionCol;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		types = session.createQuery("FROM Type", Type.class).getResultList();
		
		typeCol.setCellValueFactory(new PropertyValueFactory<>("nomType"));
		responsableCol.setCellValueFactory(new PropertyValueFactory<>("responsableType"));
		descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
		
		ObservableList<Type> observableList = FXCollections.observableList(types);
		
		typeTable.setItems(observableList);

	}

}
