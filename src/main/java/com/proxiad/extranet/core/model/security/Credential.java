package com.proxiad.extranet.core.model.security;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.proxiad.extranet.core.model.user.User;

/**
 * Credential entity keeps the credentials for different {@link User}s in the system
 * 
 * @author Mihail Merkov
 */
@Entity
@Table(name = "CREDENTIAL")
public class Credential implements Serializable {
	
	private static final long serialVersionUID = -824995616878620502L;

	@Id
	@Column(name = "ID_CREDENTIAL")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "LOGIN")
	private String login;
	
	@Column(name = "PASSWORD")
	private String password;
	
	/**
	 * Get the id of the entity
	 * @return {@link Long}
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set the id of the entity
	 * @param id {@link Long}
	 */
	public void setId(final Long id) {
		this.id = id;
	}
	
	/**
	 * Get the login
	 * @return {@link String}
	 */
	public String getLogin() {
		return login;
	}
	
	/**
	 * Set the login
	 * @param login {@link String}
	 */
	public void setLogin(final String login) {
		this.login = login;
	}
	
	/**
	 * Get the password
	 * @return {@link String}
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Set the password
	 * @param password {@link String}
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Credential other = (Credential) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
}
