package com.ynr.beans;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="visite")
public class Visite {
    @Id
  	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_VISITE")
    private int idVisite;
    
    @Column(name="Date")
	private LocalDate date;
    
    @Column(name="HEURE")
    private LocalTime heure;
     
    @ManyToOne
    @JoinColumn(name="MATRICULE")
    private Prisonnier prisonnier;
    
    @Column(name="NBRVISITEUR")
	private int nbrVisiteur;
    
    @ManyToOne
    @JoinColumn(name="ID_VISITEUR")
	private Visiteur visiteur;
	
	public Visite() {
		
	}

	public Visite(LocalDate date, int nbrVisiteur, Visiteur visiteur) {
		super();
		
		this.date = date;
		this.nbrVisiteur = nbrVisiteur;
		this.visiteur = visiteur;
	}

	

	public Visite(LocalDate date, LocalTime heure, Prisonnier prisonnier, int nbrVisiteur, Visiteur visiteur) {
		super();
		this.date = date;
		this.heure = heure;
		this.prisonnier = prisonnier;
		this.nbrVisiteur = nbrVisiteur;
		this.visiteur = visiteur;
		
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getNbrVisiteur() {
		return nbrVisiteur;
	}

	public void setNbrVisiteur(int nbrVisiteur) {
		this.nbrVisiteur = nbrVisiteur;
	}

	public Visiteur getVisiteur() {
		return visiteur;
	}

	public void setVisiteur(Visiteur visiteur) {
		this.visiteur = visiteur;
	}

	public int getIdVisite() {
		return idVisite;
	}

	public void setIdVisite(int idVisite) {
		this.idVisite = idVisite;
	}

	public LocalTime getHeure() {
		return heure;
	}

	public void setHeure(LocalTime heure) {
		this.heure = heure;
	}

	public Prisonnier getPrisonnier() {
		return prisonnier;
	}

	public void setPrisonnier(Prisonnier prisonnier) {
		this.prisonnier = prisonnier;
	}

}
