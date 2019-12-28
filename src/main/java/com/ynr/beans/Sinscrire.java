package com.ynr.beans;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.ynr.util.InscriptionId;

@Entity
@Table(name="SINSCRIRE")
public class Sinscrire {
	
	@EmbeddedId
    private InscriptionId id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idPrisonnier")
    private Prisonnier prisonnier;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idSession")
    private Session session;
	
	@Column(name="ABSCENCE")
	private int abscence;
	@Column(name="SCORE")
	private int score;
	@Column(name="DECISION")
	private String decision;
	
	
	public Sinscrire() {
		super();
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

	public InscriptionId getId() {
		return id;
	}

	public void setId(InscriptionId id) {
		this.id = id;
	}

	public Prisonnier getPrisonnier() {
		return prisonnier;
	}

	public void setPrisonnier(Prisonnier prisonnier) {
		this.prisonnier = prisonnier;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Sinscrire(Prisonnier prisonnier, Session session, int abscence, int score, String decision) {
		super();
		this.prisonnier = prisonnier;
		this.session = session;
		this.abscence = abscence;
		this.score = score;
		this.decision = decision;
	}

	public Sinscrire(InscriptionId id, Prisonnier prisonnier, Session session, int abscence, int score,
			String decision) {
		super();
		this.id = id;
		this.prisonnier = prisonnier;
		this.session = session;
		this.abscence = abscence;
		this.score = score;
		this.decision = decision;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + abscence;
		result = prime * result + ((decision == null) ? 0 : decision.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((prisonnier == null) ? 0 : prisonnier.hashCode());
		result = prime * result + score;
		result = prime * result + ((session == null) ? 0 : session.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sinscrire other = (Sinscrire) obj;
		if (abscence != other.abscence)
			return false;
		if (decision == null) {
			if (other.decision != null)
				return false;
		} else if (!decision.equals(other.decision))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (prisonnier == null) {
			if (other.prisonnier != null)
				return false;
		} else if (!prisonnier.equals(other.prisonnier))
			return false;
		if (score != other.score)
			return false;
		if (session == null) {
			if (other.session != null)
				return false;
		} else if (!session.equals(other.session))
			return false;
		return true;
	}
	
	
}
