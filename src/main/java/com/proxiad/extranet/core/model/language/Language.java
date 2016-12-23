package com.proxiad.extranet.core.model.language;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.math.NumberUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proxiad.extranet.core.model.employee.Employee;
import com.proxiad.extranet.core.model.expertise.EmployeeExpertise;

/**
 * This entitty keeps information about the different languages where some {@link Employee} can speek
 * @author Mihail Merkov *
 */
@JsonIgnoreProperties({"employeeLanguages"})
@Entity
@Table(name = "LANGUAGE")
public class Language implements Serializable {
	
	private static final long serialVersionUID = 6623560568992064751L;

	@Id
	@Column(name = "ID_LANGUAGE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "FLAG_CSS")
	private String flagCss;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.language", cascade = CascadeType.ALL)
	private Set<EmployeeLanguage> employeeLanguages = new HashSet<EmployeeLanguage>(NumberUtils.INTEGER_ZERO);
	
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
	 * Get the name of the language
	 * @return {@link String}
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the name of the language
	 * @param name {@link String}
	 */
	public void setName(final String name) {
		this.name = name;
	}
	
	/**
	 * Get the CSS flag of the country associated with this language
	 * @return {@link String}
	 */
	public String getFlagCss() {
		return flagCss;
	}
	
	/**
	 * Set the CSS flag of the country associated with this language
	 * @param flagCss {@link String}
	 */
	public void setFlagCss(String flagCss) {
		this.flagCss = flagCss;
	}
	
	/**
	 * Get the {@link Set} from {@link EmployeeExpertise} employees who speak this language
	 * @return {@link Set} from {@link EmployeeExpertise}
	 */
	public Set<EmployeeLanguage> getEmployeeLanguages() {
		return employeeLanguages;
	}
	
	/**
	 * Set the {@link Set} from {@link EmployeeExpertise} employees who speak this language
	 * @param employeeLanguages {@link Set} from {@link EmployeeExpertise}
	 */
	public void setEmployeeLanguages(final Set<EmployeeLanguage> employeeLanguages) {
		this.employeeLanguages = employeeLanguages;
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flagCss == null) ? 0 : flagCss.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Language other = (Language) obj;
		if (flagCss == null) {
			if (other.flagCss != null)
				return false;
		} else if (!flagCss.equals(other.flagCss))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
