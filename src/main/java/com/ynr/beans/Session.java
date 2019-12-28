package com.ynr.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="SESSION")
public class Session {
	@Id
	@Column(name="ID_SESSION")
	private int id_session;
	@ManyToOne
	@JoinColumn(name="ID_FORMATION")
	private Formation id_formation;
	@Column(name="SESSION")
	private String session;
	@Column(name="FORMATEUR")
	private String formateur;
	@Column(name="DATE_DEBUT")
	private Date date_debut;
	@Column(name="DATE_FIN")
	private Date date_fin;
	@Column(name="NB_INSCRITS")
	private int nb_inscrits;
	@Column(name="NB_ADMIS")
	private int nb_admis;
	@Column(name="NB_EXCLU")
	private int nb_exclu;
public Session() {
	super();
}
public Session(int id_session, Formation id_formation, String session, String formateur, Date date_debut, Date date_fin, int nb_inscrits, int nb_admis, int nb_exclu) {
	super();
	this.id_session=id_session;
	this.id_formation=id_formation;
	this.session=session;
	this.formateur=formateur;
	this.date_debut=date_debut;
	this.date_fin=date_fin;
	this.nb_inscrits=nb_inscrits;
	this.nb_admis=nb_admis;
	this.nb_exclu=nb_exclu;
}
public int getId_session() {
	return id_session;
}
public Formation getId_formation() {
	return id_formation;
}
public String getSession() {
	return session;
}
public String getFormateur() {
	return formateur;
}
public Date getDate_debut() {
	return date_debut;
}
public Date getDate_fin() {
	return date_fin;
}
public int getNb_inscrits() {
	return nb_inscrits;
}
public int getNb_admis() {
	return nb_admis;
}
public int getNb_exclu() {
	return nb_exclu;
}
public void setId_session(int id_session) {
	this.id_session=id_session;
}
public void setId_formation(Formation id_formation) {
	this.id_formation=id_formation;
}
public void setFormateur(String formateur) {
	this.formateur=formateur;
}
public void setDate_debut(Date date_debut) {
	this.date_debut=date_debut;
}
public void setDate_fin(Date date_fin) {
	this.date_fin=date_fin;
}
public void setNb_inscrits(int nb_inscrits) {
	this.nb_inscrits=nb_inscrits;
}
public void setNb_admis(int nb_admis) {
	this.nb_admis=nb_admis;
}
public void setNb_exclu(int nb_exclu) {
	this.nb_exclu=nb_exclu;
}
}
