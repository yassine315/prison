package com.ynr.beans;

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
@NaturalIdCache
public class Session {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_SESSION")
	private int idSession;
	
	@OneToMany(mappedBy = "session",cascade = CascadeType.ALL, orphanRemoval = true )
	private List<Sinscrire> prisonniers;	
	
	@ManyToOne
	@JoinColumn(name="ID_FORMATION")
	private Formation formation;
	
	
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
	
	
}
