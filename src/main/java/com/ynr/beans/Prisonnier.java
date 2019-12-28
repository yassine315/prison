package com.ynr.beans;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="PRISONNIER")
public class Prisonnier {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="MATRICULE")
	private int matricule;
	
	@Column(name="CIN")
	private String cinPrisonnier;
	
	@ManyToOne
    @JoinColumn(name="ID_CAUSE", nullable=false)
	private Cause cause;
	
	
	@OneToMany(mappedBy="prisonnier",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Visite> visites;
	
	@Column(name="NOM")
	private String nom;
	
	@Column(name="PRENOM")
	private String prenom;
	
	@Column(name="DN")
	private Date dateNaissance ;
	
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
	
	@Column(name="PHOTO")
	private Blob photo;

	@OneToMany( mappedBy = "prisonnier", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Sinscrire> sessions;  
	
	public Prisonnier(String cinPrisonnier, Cause cause, String nom, String prenom, Date dateNaissance, int periode,
			Date dateEntrer, int niveauEtude, boolean detenu, Blob blobImage) {
		super();
		this.cinPrisonnier = cinPrisonnier;
		this.cause = cause;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.periode = periode;
		this.dateEntrer = dateEntrer;
		this.niveauEtude = niveauEtude;
		this.detenu = detenu;
		this.photo = blobImage;
	}



	public Prisonnier(String cinPrisonnier, Cause cause, String nom, String prenom, Date dateNaissance, int periode,
			Date dateEntrer, int niveauEtude, int evaluation, boolean detenu) {
		super();
		this.cinPrisonnier = cinPrisonnier;
		this.cause = cause;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.periode = periode;
		this.dateEntrer = dateEntrer;
		this.niveauEtude = niveauEtude;
		this.evaluation = evaluation;
		this.detenu = detenu;
	}
	
	

	
	
	public Prisonnier() {
		super();
	}



	


	public Blob getPhoto() {
		return photo;
	}



	public void setPhoto(Blob photo) {
		this.photo = photo;
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

	public Date getdateNaissance() {
		return dateNaissance;
	}

	public void setdateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
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


	public int getMatricule() {
		return matricule;
	}



	public void setMatricule(int matricule) {
		this.matricule = matricule;
	}



	public List<Visite> getVisites() {
		return visites;
	}



	public void setVisites(List<Visite> visites) {
		this.visites = visites;
	}



	public Date getDateNaissance() {
		return dateNaissance;
	}



	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	
	public String getNomComplet() {
		return nom+" "+prenom;
	}



	public List<Sinscrire> getSessions() {
		return sessions;
	}



	public void setSessions(List<Sinscrire> sessions) {
		this.sessions = sessions;
	}

	

}
