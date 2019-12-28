package com.ynr.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="FORMATION")

public class Formation {
@Id
@Column(name="ID_FORMATION")
private int id_formation;
@OneToMany
@JoinColumn(name="ID_TYPE")
private Type id_type;
@Column(name="NOM_FORMATION")
private String nom_formation;
@Column(name="RESPONSABLE_FORMATION")
private String responsable_formation;
@Column(name="DUREE_FORMATION")
private int duree_formation;
public Formation() {
	super();
}
public Formation(int id_formation, Type id_type, String nom_formation, String responsable_formation, int duree_formation) {
	super();
	this.id_formation=id_formation;
	this.id_type=id_type;
	this.nom_formation=nom_formation;
	this.responsable_formation=responsable_formation;
	this.duree_formation=duree_formation;
	
}
public int getId_formation() {
	return id_formation;
}
public Type getId_type() {
	return id_type;
}
public String getNom_formation() {
	return nom_formation;
}
public String getResponsable_formation() {
	return responsable_formation;
}
public int getDuree_formation() {
	return duree_formation;
}
public void setId_formation(int id_formation) {
	this.id_formation=id_formation;
}
public void setId_type(Type id_type) {
	this.id_type=id_type;
}
public void setNom_formation(String nom_formation) {
	this.nom_formation=nom_formation;
}
public void setResponsable_formation(String responsable_formation) {
	this.responsable_formation=responsable_formation;
}
public void setDuree_formation(int duree_formation) {
	this.duree_formation=duree_formation;
}
}
