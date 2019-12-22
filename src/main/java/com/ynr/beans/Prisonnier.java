package com.ynr.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="prisonnier")
public class Prisonnier {
	
	@Id
	@Column(name="ID_PRISONNIER")
	private int idPrisonnier;
	
	@Column(name="ID_CAUSE")
	private int idCause;
	
	@Column(name="NOM")
	private String nom;
	
	@Column(name="PRENOM")
	private String prenom;
	
	@Column(name="AGE")
	private int age ;
	
	@Column(name="PERIODE")
	private int periode;
	
	@Column(name="DATE_ENTRER")
	private Date dateEntrer;
	
	@Column(name="NIVEAU_ETUDE")
	private int niveauEtude;
	
	@Column(name="EVALUATION")
	private int evaluation ;
	
	@Column(name="DETENU")
	private boolean detenu;
	
	@Column(name="CAUSE")
	private String cause;
	

	public int getIdPrisonnier() {
		return idPrisonnier;
	}

	public void setIdPrisonnier(int idPrisonnier) {
		this.idPrisonnier = idPrisonnier;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getPeriode() {
		return periode;
	}

	public void setPeriode(int periode) {
		this.periode = periode;
	}

	public Date getDateEntrer() {
		return dateEntrer;
	}

	public void setDateEntrer(Date dateEntrer) {
		this.dateEntrer = dateEntrer;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}


	
	
	public int getNiveauEtude() {
		return niveauEtude;
	}

	public void setNiveauEtude(int niveauEtude) {
		this.niveauEtude = niveauEtude;
	}

	public boolean isDetenu() {
		return detenu;
	}

	public void setDetenu(boolean detenu) {
		this.detenu = detenu;
	}

	public int getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}

	public Prisonnier(int idPrisonnier, int idCause, String nom, String prenom, int age, int periode, Date dateEntrer,
			int niveauEtude, String cause, boolean detenu) {
		super();
		this.idCause = idCause;
		this.idPrisonnier = idPrisonnier;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.periode = periode;
		this.dateEntrer = dateEntrer;
		this.niveauEtude = niveauEtude;
		this.cause = cause;
	}
	
	public Prisonnier( String nom, String prenom, int age, int periode, Date dateEntrer,
			int niveauEtude, String cause, boolean detenu) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.periode = periode;
		this.dateEntrer = dateEntrer;
		this.niveauEtude = niveauEtude;
		this.cause = cause;
	}

	public Prisonnier() {
		super();
	}
	
	

}
