package com.proxiad.extranet.core.model.interest;

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

/**
 * Interest entity keeps information about the specific interest of some {@link Employee}</br>
 * Example: <code>Football | Reading | Swimming</code>
 *  
 * @author Mihail Merkov
 */
@JsonIgnoreProperties({"employeeInterests"})
@Entity
@Table(name = "INTEREST")
public class Interest implements Serializable {
	
	private static final long serialVersionUID = -6788150358658550081L;

	@Id
	@Column(name = "ID_INTEREST")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "TITLE")
	private String title;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.interest", cascade = CascadeType.ALL)
	private Set<EmployeeInterest> employeeInterests = new HashSet<EmployeeInterest>(NumberUtils.INTEGER_ZERO);
	
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
	 * Get the title of the interest
	 * @return {@link String}
	 */
	public String getTitle() {
		return title;
	}	
	
	/**
	 * Get the title of the interest
	 * @param title {@link String}
	 */
	public void setTitle(final String title) {
		this.title = title;
	}
	
	/**
	 * Get a {@link Set} from {@link EmployeeInterest} employees for this interest
	 * @return {@link Set} from {@link EmployeeInterest}
	 */
	public Set<EmployeeInterest> getEmployeeInterests() {
		return employeeInterests;
	}
	
	/**
	 * Set a {@link Set} from {@link EmployeeInterest} employees for this interest
	 * @param employeeInterests {@link Set} from {@link EmployeeInterest}
	 */
	public void setEmployeeInterests(final Set<EmployeeInterest> employeeInterests) {
		this.employeeInterests = employeeInterests;
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Interest other = (Interest) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}
