package com.ynr.prison.controller;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.ynr.beans.Cause;
import com.ynr.beans.Prisonnier;
import com.ynr.prison.nitification.Notification;
import com.ynr.prison.nitification.Notifications;
import com.ynr.prison.nitification.TrayNotification;
import com.ynr.util.ComboboxNiama;
import com.ynr.util.HibernateUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

public class ControllerLiberer implements Initializable {
	
	private SessionFactory sessionFactory;
	private List<Prisonnier> liberers ;
	private Map<String,Prisonnier> prisonniers;
	
	@FXML
	private ComboBox<String> comboBoxPrisonnier;
	
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

	
	
	
	@FXML
	private void liberer() {
		
		String cin = comboBoxPrisonnier.getValue();
		Prisonnier liberer = prisonniers.get(cin);
		liberer.setDetenu(false);
		try {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(liberer);;
		session.getTransaction().commit();
		prisonniers.remove(cin);
		liberers.add(liberer);
		comboBoxPrisonnier.setItems(FXCollections.observableArrayList(prisonniers.keySet().toArray(new String[prisonniers.size()])));
		prisonnierTable.refresh();
		
		String title = "L'operation est effectue";
        String message = "Votre prisonnier a été liberer correctement";
        Notification notification = Notifications.SUCCESS;
        TrayNotification tray = new TrayNotification(title, message, notification);
        tray.showAndDismiss(new Duration(2000));
		}catch(Exception e) {
			e.printStackTrace();
			String title = "L'operation est échoer";
	        String message = "Votre prisonnier n'a pas été liberer ";
	        Notification notification = Notifications.ERROR;
	        TrayNotification tray = new TrayNotification(title, message, notification);
	        tray.showAndDismiss(new Duration(2000));
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		//la selection des encient prisonnier
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Prisonnier> criteriaQuery = criteriaBuilder.createQuery(Prisonnier.class);
		Root<Prisonnier> root = criteriaQuery.from(Prisonnier.class);
		criteriaQuery.where(criteriaBuilder.isFalse(root.get("detenu").as(boolean.class)));
		criteriaQuery.select(root);
		Query<Prisonnier> query = session.createQuery(criteriaQuery);
		
		//la selection des prisonnier
		CriteriaBuilder criteriaBuilderP = session.getCriteriaBuilder();
		CriteriaQuery<Prisonnier> criteriaQueryP = criteriaBuilderP.createQuery(Prisonnier.class);
		Root<Prisonnier> rootP = criteriaQueryP.from(Prisonnier.class);
		criteriaQuery.where(criteriaBuilder.isTrue(rootP.get("detenu").as(boolean.class)));
		criteriaQuery.select(rootP);
		Query<Prisonnier> queryP = session.createQuery(criteriaQuery);
		prisonniers = queryP.list().stream().collect(Collectors.toMap(Prisonnier::getCinPrisonnier, e ->e ));
		
		liberers = query.list();
		session.getTransaction().commit();
		session.close();
		
		nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		ageCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		peroideCol.setCellValueFactory(new PropertyValueFactory<>("periode"));
		dateEntrerCol.setCellValueFactory(new PropertyValueFactory<>("dateEntrer"));
		niveauCol.setCellValueFactory(new PropertyValueFactory<>("niveauEtude"));
		evaluationCol.setCellValueFactory(new PropertyValueFactory<>("evaluation"));
		detenuCol.setCellValueFactory(new PropertyValueFactory<>("detenu"));
		causeCol.setCellValueFactory(new PropertyValueFactory<>("cause"));
		
		ObservableList<Prisonnier> observableList = FXCollections.observableList(liberers);
		prisonnierTable.setItems(observableList);
		
		/**
		 * configurer comboBox
		 */
		comboBoxPrisonnier.setTooltip(new Tooltip());
		comboBoxPrisonnier.getItems().addAll(prisonniers.keySet().toArray(new String[prisonniers.size()]));
		new ComboboxNiama<String>(comboBoxPrisonnier);
		
	}

}
