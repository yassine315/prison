package com.ynr.util;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class InscriptionId implements Serializable {

	private static final long serialVersionUID = 4860833063610045877L;
	
	@Column(name = "ID_SESSION")
    private int idSession;
	
	@Column(name = "MATRICULE")
    private int idPrisonnier;
 
    

	public InscriptionId(int idPrisonnier, int idSession) {
		super();
		this.idPrisonnier = idPrisonnier;
		this.idSession = idSession;
	}

	public InscriptionId() {
		super();
	}

	public int getIdPrisonnier() {
		return idPrisonnier;
	}

	public void setIdPrisonnier(int idPrisonnier) {
		this.idPrisonnier = idPrisonnier;
	}

	public int getIdSession() {
		return idSession;
	}

	public void setIdSession(int idSession) {
		this.idSession = idSession;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPrisonnier;
		result = prime * result + idSession;
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
		InscriptionId other = (InscriptionId) obj;
		if (idPrisonnier != other.idPrisonnier)
			return false;
		if (idSession != other.idSession)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InscriptionId [idSession=" + idSession + ", idPrisonnier=" + idPrisonnier + "]";
	}
	
    
    

}
