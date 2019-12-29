package com.ynr.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="CAUSES")
public class Cause implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6520150263233788130L;

	@Id
	@Column(name="ID_CAUSE")
	private int idCause;
	
	@Column(name="NOM")
	private String nom;
	
	@Column(name="EVALUATION")
	private int evaluation;

	
	public Cause() {
		super();
	}

	public Cause(int idCause, String nom, int evaluation) {
		super();
		this.idCause = idCause;
		this.nom = nom;
		this.evaluation = evaluation;
	}

	public int getIdCause() {
		return idCause;
	}

	public void setIdCause(int idCause) {
		this.idCause = idCause;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}

	@Override
	public String toString() {
		return "Cause [nom=" + nom + ", evaluation=" + evaluation + "]";
	}
	
	

}
