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

import com.ynr.beans.Prisonnier;
import com.ynr.util.HibernateUtil;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
	private TableColumn<Prisonnier,String> naissanceCol;
	
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
		naissanceCol.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
		peroideCol.setCellValueFactory(new PropertyValueFactory<>("periode"));
		dateEntrerCol.setCellValueFactory(new PropertyValueFactory<>("dateEntrer"));
		niveauCol.setCellValueFactory(new PropertyValueFactory<>("niveauEtude"));
		evaluationCol.setCellValueFactory(new PropertyValueFactory<>("evaluation"));
		detenuCol.setCellValueFactory(new PropertyValueFactory<>("matricule"));
		causeCol.setCellValueFactory(p -> new ReadOnlyStringWrapper(p.getValue().getCause().getNom()));
		
		ObservableList<Prisonnier> observableList = FXCollections.observableList(prisonniers);
		prisonnierTable.setItems(observableList);
		
	}

}
