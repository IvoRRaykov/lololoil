package com.proxiad.extranet.core.model.skill;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * SkillCategory entity categories the {@link Skill}s
 * 
 * @author Mihail Merkov
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "skills"})
@Entity
@Table(name = "SKILL_CATEGORY")
public class SkillCategory implements Serializable {
	
	private static final long serialVersionUID = -2683837675736838409L;

	@Id
	@Column(name = "ID_SKILL_CATEGORY")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "TITLE")
	private String title;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private Set<Skill> skills;
	
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
	 * Get the tile
	 * @return {@link String}
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Set the tile
	 * @param title
	 */
	public void setTitle(final String title) {
		this.title = title;
	}
	
	/**
	 * Get a {@link Set} from {@link Skill}s which belongs to this Category
	 * @return {@link Set} from {@link Skill}
	 */
	public Set<Skill> getSkills() {
		return skills;
	}
	
	/**
	 * Set a {@link Set} from {@link Skill}s which belongs to this Category
	 * @param skills {@link Set} from {@link Skill}
	 */
	public void setSkills(final Set<Skill> skills) {
		this.skills = skills;
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
		SkillCategory other = (SkillCategory) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (skills == null) {
			if (other.skills != null)
				return false;
		} else if (!skills.equals(other.skills))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}
