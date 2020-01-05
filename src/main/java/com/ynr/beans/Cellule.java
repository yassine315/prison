package com.ynr.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="CELLULE")
public class Cellule implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6520150263233788130L;

	@Id
	@Column(name="ID_CELLULE")
	private int idCellule;
	
	@Column(name="NOM_CELLULE")
	private String nom;
	@Column(name="TYPE_CELLULE")
	private String type;
	
	@OneToMany(mappedBy="cellule",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	
	private List<Prisonnier> prisonniers;

	public Cellule() {
		super();
	}

	public Cellule(String nom, String type) {
		super();
		this.nom = nom;
		this.type = type;
	}

	public Cellule(int idCellule, String nom, String type) {
		super();
		this.idCellule = idCellule;
		this.nom = nom;
		this.type=type;
		
	}

	public List<Prisonnier> getPrisonniers() {
		return prisonniers;
	}

	public void setPrisonniers(List<Prisonnier> prisonniers) {
		this.prisonniers = prisonniers;
	}

	public Cellule(int idCellule, String nom, String type, List<Prisonnier> prisonniers) {
		super();
		this.idCellule = idCellule;
		this.nom = nom;
		this.type = type;
		this.prisonniers = prisonniers;
	}

	public int getIdCellule() {
		return idCellule;
	}

	public void setIdCellule(int idCellule) {
		this.idCellule = idCellule;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

}
