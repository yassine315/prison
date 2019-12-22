 package com.ynr.prison.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ynr.util.ComboboxNiama;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerVisites implements Initializable {
	
	private Scene scene ;
	private Stage stage ;
	private AnchorPane anchorPane;
	
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
	private void ajouterVisite() {
		Stage currentStage = (Stage) nouveauVisiteur.getScene().getWindow();
		
		
		FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource("AjouterVisite.fxml"));
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scene = new Scene(anchorPane);
		stage = new Stage();
		stage.setScene(scene);
		
		stage.initOwner(currentStage);
		stage.initModality(Modality.APPLICATION_MODAL); 
		stage.showAndWait();
		
	}
	@FXML
		private void rechercher() {
			
			ancienVisiteur.setDisable(false);
			nouveauVisiteur.setDisable(false);
			
		}
	
 @FXML
 private void ancienVisiteur(){
	
	
	 
		FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource("AncienVisiteur.fxml"));
		AnchorPane anchorPaneAncien = new AnchorPane();
		try {
			anchorPaneAncien= loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 Scene sceneAncien = new Scene(anchorPaneAncien);
		 Stage stageAncien= new Stage();
		 stageAncien.setScene(sceneAncien);
		 stageAncien.show();
	 
 }
 private static final String[] LISTA = { "Abacate", "Abacaxi", "Ameixa", "Amora", "Araticum", "Atemoia", "Avocado",
			"Banana prata", "Caju", "Cana descascada", "Caqui", "Caqui Fuyu", "Carambola", "Cereja", "Coco verde",
			"Figo", "Figo da Índia", "Framboesa", "Goiaba", "Graviola", "Jabuticaba", "Jambo", "Jambo rosa", "Jambolão",
			"Kino (Kiwano)", "Kiwi", "Laranja Bahia", "Laranja para suco", "Laranja seleta", "Laranja serra d’água",
			"Laranjinha kinkan", "Lichia", "Lima da pérsia", "Limão galego", "Limão Taiti", "Maçã argentina",
			"Maçã Fuji", "Maçã gala", "Maçã verde", "Mamão formosa", "Mamão Havaí", "Manga espada", "Manga Haden",
			"Manga Palmer", "Manga Tommy", "Manga Ubá", "Mangostim", "Maracujá doce", "Maracujá para suco", "Melancia",
			"Melancia sem semente", "Melão", "Melão Net", "Melão Orange", "Melão pele de sapo", "Melão redinha",
			"Mexerica carioca", "Mexerica Murcote", "Mexerica Ponkan", "Mirtilo", "Morango", "Nectarina",
			"Nêspera ou ameixa amarela", "Noni", "Pera asiática", "Pera portuguesa", "Pêssego", "Physalis", "Pinha",
			"Pitaia", "Romã", "Tamarilo", "Tamarindo", "Uva red globe", "Uva rosada", "Uva Rubi", "Uva sem semente",
			"Abobora moranga", "Abobrinha italiana", "Abobrinha menina", "Alho", "Alho descascado",
			"Batata baroa ou cenoura amarela", "Batata bolinha", "Batata doce", "Batata inglesa", "Batata yacon",
			"Berinjela", "Beterraba", "Cebola bolinha", "Cebola comum", "Cebola roxa", "Cenoura", "Cenoura baby",
			"Couve flor", "Ervilha", "Fava", "Gengibre", "Inhame", "Jiló", "Massa de alho", "Maxixe", "Milho",
			"Pimenta biquinho fresca", "Pimenta de bode fresca", "Pimentão amarelo", "Pimentão verde",
			"Pimentão vermelho", "Quiabo", "Repolho", "Repolho roxo", "Tomate cereja", "Tomate salada",
			"Tomate sem acidez", "Tomate uva", "Vagem", "Agrião", "Alcachofra", "Alface", "Alface americana",
			"Almeirão", "Brócolis", "Broto de alfafa", "Broto de bambu", "Broto de feijão", "Cebolinha", "Coentro",
			"Couve", "Espinafre", "Hortelã", "Mostarda", "Rúcula", "Salsa", "Ovos brancos", "Ovos de codorna",
			"Ovos vermelhos" };
 @FXML
	private ComboBox<String> cmb1;
@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
 nouveauVisiteur.setDisable(true);
 ancienVisiteur.setDisable(true);
 
 cmb1.setTooltip(new Tooltip());
	cmb1.getItems().addAll(LISTA);
	new ComboboxNiama<String>(cmb1);
 
 
 
 
 
}
}
