package com.ynr.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="TYPE")
public class Type {
	@Id
	@Column(name="ID_TYPE")
	private int id_type;
	
	@Column(name="NOM_TYPE")
	private String nom_type;
	@Column(name="RESPONSABLE_TYPE")
	private String responsable_type;
	@Column(name="DESCRIPTION")
	private String description;
	public Type() {
		super();
	}
	public Type(int id_type, String nom_type, String responsable_type, String description) {
		super();
		this.id_type=id_type;
		this.nom_type=nom_type;
		this.responsable_type=responsable_type;
		this.description=description;
	}
	public String getNom_type() {
		return nom_type;
	}
	public String getResponsable_type() {
		return responsable_type;
	}
	public String getDescription() {
		return description;
	}
	public int getId_type() {
		return id_type;
	}
	public void setId_type(int id_type) {
		this.id_type=id_type;
	}
	public void setNom_type(String nom_type) {
		this.nom_type=nom_type;
	}
	public void setResponsable_type(String responsable_type) {
		this.responsable_type=responsable_type;
	}
	public void setDescription(String description) {
		this.description=description;
	}
}
