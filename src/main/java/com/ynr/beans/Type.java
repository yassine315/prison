package com.ynr.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="TYPE")
public class Type {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_TYPE")
	private int idType;
	
	@Column(name="NOM_TYPE")
	private String nomType;
	
	@Column(name="RESPONSABLE_TYPE")
	private String responsableType;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@OneToMany(mappedBy="type",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Formation> formations;
	
	public Type() {
		super();
	}

	public Type(String nomType, String responsableType, String description, List<Formation> formations) {
		super();
		this.nomType = nomType;
		this.responsableType = responsableType;
		this.description = description;
		this.formations = formations;
	}
	public Type(String nomType, String responsableType, String description) {
		super();
		this.nomType = nomType;
		this.responsableType = responsableType;
		this.description = description;
		this.formations = formations;
	}

	public int getIdType() {
		return idType;
	}

	public void setIdType(int idType) {
		this.idType = idType;
	}

	public String getNomType() {
		return nomType;
	}

	public void setNomType(String nomType) {
		this.nomType = nomType;
	}

	public String getResponsableType() {
		return responsableType;
	}

	public void setResponsableType(String responsableType) {
		this.responsableType = responsableType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Formation> getFormations() {
		return formations;
	}

	public void setFormations(List<Formation> formations) {
		this.formations = formations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((formations == null) ? 0 : formations.hashCode());
		result = prime * result + idType;
		result = prime * result + ((nomType == null) ? 0 : nomType.hashCode());
		result = prime * result + ((responsableType == null) ? 0 : responsableType.hashCode());
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
		Type other = (Type) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (formations == null) {
			if (other.formations != null)
				return false;
		} else if (!formations.equals(other.formations))
			return false;
		if (idType != other.idType)
			return false;
		if (nomType == null) {
			if (other.nomType != null)
				return false;
		} else if (!nomType.equals(other.nomType))
			return false;
		if (responsableType == null) {
			if (other.responsableType != null)
				return false;
		} else if (!responsableType.equals(other.responsableType))
			return false;
		return true;
	}
	
	
}
