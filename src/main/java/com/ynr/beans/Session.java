package com.ynr.beans;

import java.io.Serializable;
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

import org.hibernate.annotations.NaturalIdCache;

@Entity
@Table(name="SESSION")
public class Session implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4293478737666812987L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_SESSION")
	private int idSession;
	
	@OneToMany(mappedBy = "session",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Sinscrire> prisonniers;	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_FORMATION")
	private Formation formation;
	
	@Column(name="SESSION")
	private String session;
	
	@Column(name="FORMATEUR")
	private String formateur;
	
	@Column(name="DATE_DEBUT")
	private Date dateDebut;
	
	@Column(name="DATE_FIN")
	private Date dateFin;
	
	@Column(name="NB_INSCRITS")
	private int nbInscrits;
	
	@Column(name="NB_ADMIS")
	private int nbAdmis;
	
	@Column(name="NB_EXCLU")
	private int nbExclu;

	public Session() {
		super();
	}

	public void addPrisonnier(Prisonnier prisonnier) {
		Sinscrire sinscrire = new Sinscrire(prisonnier, this);
		prisonniers.add(sinscrire);
		prisonnier.getSessions().add(sinscrire);
	}
	
	
	
	public Session(Formation formation, String session, String formateur, Date dateDebut, Date dateFin) {
		super();
		this.formation = formation;
		this.session = session;
		this.formateur = formateur;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	public int getIdSession() {
		return idSession;
	}

	public void setIdSession(int idSession) {
		this.idSession = idSession;
	}

	public List<Sinscrire> getPrisonniers() {
		return prisonniers;
	}

	public void setPrisonniers(List<Sinscrire> prisonniers) {
		this.prisonniers = prisonniers;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getFormateur() {
		return formateur;
	}

	public void setFormateur(String formateur) {
		this.formateur = formateur;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public int getNbInscrits() {
		return nbInscrits;
	}

	public void setNbInscrits(int nbInscrits) {
		this.nbInscrits = nbInscrits;
	}

	public int getNbAdmis() {
		return nbAdmis;
	}

	public void setNbAdmis(int nbAdmis) {
		this.nbAdmis = nbAdmis;
	}

	public int getNbExclu() {
		return nbExclu;
	}

	public void setNbExclu(int nbExclu) {
		this.nbExclu = nbExclu;
	}
	
	
}
