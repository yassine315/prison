package com.ynr.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="deceder")
public class Deces implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2883535832817321218L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_DECEDER")
	private int idDeces;
	
	@Column(name="NOM")
	private String nom;
	
	
	@Column(name="PRENOM")
	private String prenom;
	
	@Column(name="CIN")
	private String cin;
	
	@Column(name="RAISON_DECES")
	private String raison;
	
	@Column(name="DATE_DECES")
	private Date date;
	
	@Column(name="HEURE_DECES")
	private Date heur;

	public Deces() {
		super();
	}	
	



	public Deces(int idDeces, String nom, String prenom, String cin, String raison, Date date) {
		super();
		this.idDeces = idDeces;
		this.nom = nom;
		this.prenom = prenom;
		this.cin = cin;
		this.raison = raison;
		this.date = date;
	}







	public int getIdDeces() {
		return idDeces;
	}


	public void setIdDeces(int idDeces) {
		this.idDeces = idDeces;
	}


	public String getRaison() {
		return raison;
	}

	public void setRaison(String raison) {
		this.raison = raison;
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




	public Date getDate() {
		return date;
	}




	public void setDate(Date date) {
		this.date = date;
	}




	public Date getHeur() {
		return heur;
	}




	public void setHeur(Date heur) {
		this.heur = heur;
	}
	
	
	

}
