package com.ynr.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="VISITEUR")
public class Visiteur implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5024977542203995969L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_VISITEUR")
    private int idVisiteur;
	
	@Column(name="NOM")
	private String nom;
	
	@Column(name="PRENOM")
	private String prenom;
	
	@Column(name="CIN")
	private String cin;
	
	@Column(name="LIEN_DE_PARENTE")
	private String lienparente;	
	
	@OneToMany(mappedBy="visiteur",cascade=CascadeType.ALL)
	private List<Visite> visites;
	
	public Visiteur() {
		
	}

	public Visiteur(String nom, String prenom, String cin, String lienparente) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.cin = cin;
		this.lienparente = lienparente;
		this.idVisiteur = 0;
	}

	public int getIdVisiteur() {
		return idVisiteur;
	}

	public void setIdVisiteur(int idVisiteur) {
		this.idVisiteur = idVisiteur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getLienparente() {
		return lienparente;
	}

	public void setLienparente(String lienparente) {
		this.lienparente = lienparente;
	}

	public List<Visite> getVisites() {
		return visites;
	}

	public void setVisites(List<Visite> visites) {
		this.visites = visites;
	}
	
}
