package com.ynr.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="SINSCRIRE")
public class Sinscrire {
	@Id
	@ManyToMany
	@JoinColumn(name="MATRICULE")
	private Prisonnier matricule;
	@Id
	@ManyToMany
	@JoinColumn(name="ID_SESSION")
	private Session id_session;
	@Column(name="ABSCENCE")
	private int abscence;
	@Column(name="SCORE")
	private int score;
	@Column(name="DECISION")
	private String decision;
	public Sinscrire() {
		super();
	}
	public Sinscrire(Prisonnier matricule, Session id_session, int abscence, int score, String decision) {
		super();
		this.matricule=matricule;
		this.id_session=id_session;
		this.abscence=abscence;
		this.score=score;
		this.decision=decision;
	}
	public Prisonnier getMatricule() {
		return matricule;
	}
	public Session getId_session() {
		return id_session;
	}
	public int getAbscence() {
		return abscence;
	}
	public int getScore() {
		return score;
	}
	public String getDecision() {
		return decision;
	}
	public void setAbscence(int abscence) {
		this.abscence=abscence;
	}
	public void setScore(int score) {
		this.score=score;
	}
	public void setDecision(String decision) {
		this.decision=decision;
	}
	public void setMatricule(Prisonnier matricule) {
		this.matricule=matricule;
	}
	public void setId_session(Session id_session) {
		this.id_session=id_session;
	}
}
