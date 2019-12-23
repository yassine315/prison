package com.ynr.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="PRISONNIER")
public class Prisonnier {
	
	@Id
	@Column(name="CIN")
	private String cinPrisonnier;
	
	@ManyToOne
    @JoinColumn(name="ID_CAUSE", nullable=false)
	private Cause cause;
	
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

	public Prisonnier() {
		super();
	}
	
	
	
	public Prisonnier(String cinPrisonnier, Cause cause, String nom, String prenom, int age, int periode,
			Date dateEntrer, int niveauEtude, boolean detenu) {
		super();
		this.cinPrisonnier = cinPrisonnier;
		this.cause = cause;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.periode = periode;
		this.dateEntrer = dateEntrer;
		this.niveauEtude = niveauEtude;
		this.detenu = detenu;
	}



	public Prisonnier(String cinPrisonnier, Cause cause, String nom, String prenom, int age, int periode,
			Date dateEntrer, int niveauEtude, int evaluation, boolean detenu) {
		super();
		this.cinPrisonnier = cinPrisonnier;
		this.cause = cause;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.periode = periode;
		this.dateEntrer = dateEntrer;
		this.niveauEtude = niveauEtude;
		this.evaluation = evaluation;
		this.detenu = detenu;
	}

	public String getCinPrisonnier() {
		return cinPrisonnier;
	}

	public void setCinPrisonnier(String cinPrisonnier) {
		this.cinPrisonnier = cinPrisonnier;
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

	
	
	
	public Cause getCause() {
		return cause;
	}

	public void setCause(Cause cause) {
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


}
