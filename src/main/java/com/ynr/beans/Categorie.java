package com.ynr.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="CATEGORIE")
public class Categorie implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6520150263233788130L;

	@Id
	@Column(name="ID_CATEGORIE")
	private int idCategorie;
	
	@Column(name="NOM_CATEGORIE")
	private String nom;

	public Categorie() {
		super();
	}

	public Categorie(int idCategorie, String nom) {
		super();
		this.idCategorie = idCategorie;
		this.nom = nom;
		
	}

	public int getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Cause [nom=" + nom + "]";
	}
	
	

}
