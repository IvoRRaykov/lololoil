package com.proxiad.extranet.core.model.skill;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.math.NumberUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proxiad.extranet.core.model.employee.Employee;

/**
 * Skill entity keeps information about the acquired skill to some {@link Employee}
 * 
 * @author Mihail Merkov
 */
@JsonIgnoreProperties({"employeeSkills"})
@Entity
@Table(name = "SKILL")
public class Skill implements Serializable {
	
	private static final long serialVersionUID = 3683655049791775933L;

	@Id
	@Column(name = "ID_SKILL")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "TITLE")
	private String title;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_SKILL_CATEGORY", nullable = false)
	private SkillCategory category;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.skill", cascade = CascadeType.ALL)
	private Set<EmployeeSkill> employeeSkills = new HashSet<EmployeeSkill>(NumberUtils.INTEGER_ZERO);
	
	
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
	 * Get the title of the skill
	 * @return {@link String}
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Set the title of the skill
	 * @return {@link String}
	 */
	public void setTitle(final String title) {
		this.title = title;
	}
	
	/**
	 * Get the category {@link SkillCategory} for which belongs this skill
	 * @return {@link SkillCategory}
	 */
	public SkillCategory getCategory() {
		return category;
	}
	
	/**
	 * Set the category {@link SkillCategory} for which belongs this skill
	 * @param category {@link SkillCategory}
	 */
	public void setCategory(final SkillCategory category) {
		this.category = category;
	}	
	
	/**
	 * Get a {@link Set} from {@link Employee} which have this skill
	 * @return {@link Set} from {@link Employee}
	 */
	public Set<EmployeeSkill> getEmployeeSkills() {
		return employeeSkills;
	}
	
	/**
	 * Set a {@link Set} from {@link Employee} which have this skill
	 * @param employeeSkills
	 */
	public void setEmployeeSkills(final Set<EmployeeSkill> employeeSkills) {
		this.employeeSkills = employeeSkills;
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
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
		Skill other = (Skill) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
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
