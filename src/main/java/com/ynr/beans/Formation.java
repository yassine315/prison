package com.ynr.beans;

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
@Table(name="FORMATION")	
public class Formation {
				
	@Id
  	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_FORMATION")
	private int id_formation;
				
	@ManyToOne
	@JoinColumn(name="ID_TYPE" ,  nullable=false)
	private Type type;
	
	@Column(name="NOM_FORMATION")
	private String nomFormation;
	
	@Column(name="RESPONSABLE_FORMATION")
	private String responsableFormation;
	
	@Column(name="DUREE_FORMATION")
	private int dureeFormation;
				
	@OneToMany(mappedBy="formation",cascade = CascadeType.ALL, fetch = FetchType.LAZY)

	private List<Session> sessions;
				
	public Formation() {
		super();
	}

	public Formation(int id_formation, Type type, String nomFormation, String responsableFormation, int dureeFormation,
			List<Session> sessions) {
		super();
		this.id_formation = id_formation;
		this.type = type;
		this.nomFormation = nomFormation;
		this.responsableFormation = responsableFormation;
		this.dureeFormation = dureeFormation;
		this.sessions = sessions;
	}

	public Formation(Type type, String nomFormation, String responsableFormation, int dureeFormation,
			List<Session> sessions) {
		super();
		this.type = type;
		this.nomFormation = nomFormation;
		this.responsableFormation = responsableFormation;
		this.dureeFormation = dureeFormation;
		this.sessions = sessions;
	}
	public Formation(Type type, String nomFormation, String responsableFormation, int dureeFormation) {
		super();
		this.type = type;
		this.nomFormation = nomFormation;
		this.responsableFormation = responsableFormation;
		this.dureeFormation = dureeFormation;

	}

	public int getId_formation() {
		return id_formation;
	}

	public void setId_formation(int id_formation) {
		this.id_formation = id_formation;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getNomFormation() {
		return nomFormation;
	}

	public void setNomFormation(String nomFormation) {
		this.nomFormation = nomFormation;
	}

	public String getResponsableFormation() {
		return responsableFormation;
	}

	public void setResponsableFormation(String responsableFormation) {
		this.responsableFormation = responsableFormation;
	}

	public int getDureeFormation() {
		return dureeFormation;
	}

	public void setDureeFormation(int dureeFormation) {
		this.dureeFormation = dureeFormation;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dureeFormation;
		result = prime * result + id_formation;
		result = prime * result + ((nomFormation == null) ? 0 : nomFormation.hashCode());
		result = prime * result + ((responsableFormation == null) ? 0 : responsableFormation.hashCode());
		result = prime * result + ((sessions == null) ? 0 : sessions.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Formation other = (Formation) obj;
		if (dureeFormation != other.dureeFormation)
			return false;
		if (id_formation != other.id_formation)
			return false;
		if (nomFormation == null) {
			if (other.nomFormation != null)
				return false;
		} else if (!nomFormation.equals(other.nomFormation))
			return false;
		if (responsableFormation == null) {
			if (other.responsableFormation != null)
				return false;
		} else if (!responsableFormation.equals(other.responsableFormation))
			return false;
		if (sessions == null) {
			if (other.sessions != null)
				return false;
		} else if (!sessions.equals(other.sessions))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	
}
