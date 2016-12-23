package com.proxiad.extranet.core.model.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * UserDetails entity represents a descriptive information about an {@link User}
 * 
 * @author Mihail Merkov
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "USER_DETAILS")
public class UserDetails implements Serializable {
	
	private static final long serialVersionUID = -3034827140960603395L;
	
	@Id
	@Column(name = "ID_USER_DETAILS")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "SKYPE")
	private String skype;
	
	@Column(name = "PHONE")
	private String phone;
	
	@Column(name = "PHONE2")
	private String phone2;
	
	@Column(name = "SHOW_PHONE", nullable=false)
	private boolean showPhone;
	
	@Column(name = "BIRTH_DATE")
	private Date birthdate;
	
	@Column(name = "SHOW_BIRTH_DATE", nullable=false)
	private boolean showBirthdate;
	
	@Column(name = "WEBSITE")
	private String website;
	
	@Column(name = "BLOG")
	private String blog;
	
	@Column(name = "LINKED_IN")
	private String linkedIn;
	
	@Column(name = "FACEBOOK")
	private String facebook;
	
	@Column(name = "TWITTER")
	private String twitter;
	
	@Column(name = "FAX")
	private String fax;
	
	@Column(name = "QUICK_DESCRIPTION")
	private String quickDescription;
	
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
	 * Get the email
	 * @return {@link String}
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Set the email
	 * @param email {@link String}
	 */
	public void setEmail(final String email) {
		this.email = email;
	}
	
	/**
	 * Get the skype
	 * @return {@link String}
	 */
	public String getSkype() {
		return skype;
	}
	
	/**
	 * Set the skype
	 * @param skype {@link String}
	 */
	public void setSkype(final String skype) {
		this.skype = skype;
	}
	
	/**
	 * Get the phone
	 * @return {@link String}
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Set the phone
	 * @param phone {@link String}
	 */
	public void setPhone(final String phone) {
		this.phone = phone;
	}
	
	/**
	 * Get the phone2
	 * @return {@link String}
	 */
	public String getPhone2() {
		return phone2;
	}
	
	/**
	 * Set the phone2
	 * @param phone2 {@link String}
	 */
	public void setPhone2(final String phone2) {
		this.phone2 = phone2;
	}
	
	/**
	 * Get the flag, which indicate whether to be displayed the phones or not
	 * @return boolean
	 */
	public boolean isShowPhone() {
		return showPhone;
	}
	
	/**
	 * Get the flag, which indicate whether to be displayed the phones or not
	 * @param showPhone boolean
	 */
	public void setShowPhone(boolean showPhone) {
		this.showPhone = showPhone;
	}
	
	/**
	 * Get the birthdate
	 * @return {@link Date}
	 */
	public Date getBirthdate() {
		return birthdate;
	}
	
	/**
	 * Set a birthdate
	 * @param birthdate {@link Date}
	 */
	public void setBirthdate(final Date birthdate) {
		this.birthdate = birthdate;
	}
	
	/**
	 * Get a flag, which indicate whether the be displayed a birthdate day or not
	 * @return boolean
	 */
	public boolean isShowBirthdate() {
		return showBirthdate;
	}
	
	/**
	 * Set a flag, which indicate whether the be displayed a birthdate day or not
	 * @param showBirthdate boolean
	 */
	public void setShowBirthdate(boolean showBirthdate) {
		this.showBirthdate = showBirthdate;
	}
	
	/**
	 * Get a website
	 * @return {@link String}
	 */
	public String getWebsite() {
		return website;
	}
	
	/**
	 * Set a website
	 * @param website {@link String}
	 */
	public void setWebsite(final String website) {
		this.website = website;
	}
	
	/**
	 * Get a blog
	 * @return {@link String}
	 */
	public String getBlog() {
		return blog;
	}
	
	/**
	 * Set a blog
	 * @param blog {@link String}
	 */
	public void setBlog(final String blog) {
		this.blog = blog;
	}
	
	/**
	 * Get a linkedIn
	 * @return {@link String}
	 */
	public String getLinkedIn() {
		return linkedIn;
	}
	
	/**
	 * Set a linkedIn
	 * @param linkedIn {@link String}
	 */
	public void setLinkedIn(final String linkedIn) {
		this.linkedIn = linkedIn;
	}
	
	/**
	 * Get a facebook
	 * @return {@link String}
	 */
	public String getFacebook() {
		return facebook;
	}
	
	/**
	 * Set a facebook
	 * @param facebook {@link String}
	 */
	public void setFacebook(final String facebook) {
		this.facebook = facebook;
	}
	
	/**
	 * Get a twitter
	 * @return {@link String}
	 */
	public String getTwitter() {
		return twitter;
	}
	
	/**
	 * Set a twitter
	 * @param twitter {@link String}
	 */
	public void setTwitter(final String twitter) {
		this.twitter = twitter;
	}
	
	/**
	 * Get a fax
	 * @return {@link String}
	 */
	public String getFax() {
		return fax;
	}
	
	/**
	 * Set a fax
	 * @param fax {@link String}
	 */
	public void setFax(final String fax) {
		this.fax = fax;
	}

	
	/**
	 * Get a quick description
	 * @return quickDescription {@link String}
	 */
	public String getQuickDescription() {
		return quickDescription;
	}

	/**
	 * Set a quick description
	 * @param quickDescription {@link String}
	 */
	public void setQuickDescription(final String quickDescription) {
		this.quickDescription = quickDescription;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthdate == null) ? 0 : birthdate.hashCode());
		result = prime * result + ((blog == null) ? 0 : blog.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((facebook == null) ? 0 : facebook.hashCode());
		result = prime * result + ((fax == null) ? 0 : fax.hashCode());
		result = prime * result + ((linkedIn == null) ? 0 : linkedIn.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((phone2 == null) ? 0 : phone2.hashCode());
		result = prime * result + (showBirthdate ? 1231 : 1237);
		result = prime * result + (showPhone ? 1231 : 1237);
		result = prime * result + ((skype == null) ? 0 : skype.hashCode());
		result = prime * result + ((twitter == null) ? 0 : twitter.hashCode());
		result = prime * result + ((website == null) ? 0 : website.hashCode());
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
		UserDetails other = (UserDetails) obj;
		if (birthdate == null) {
			if (other.birthdate != null)
				return false;
		} else if (!birthdate.equals(other.birthdate))
			return false;
		if (blog == null) {
			if (other.blog != null)
				return false;
		} else if (!blog.equals(other.blog))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (facebook == null) {
			if (other.facebook != null)
				return false;
		} else if (!facebook.equals(other.facebook))
			return false;
		if (fax == null) {
			if (other.fax != null)
				return false;
		} else if (!fax.equals(other.fax))
			return false;
		if (linkedIn == null) {
			if (other.linkedIn != null)
				return false;
		} else if (!linkedIn.equals(other.linkedIn))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (phone2 == null) {
			if (other.phone2 != null)
				return false;
		} else if (!phone2.equals(other.phone2))
			return false;
		if (showBirthdate != other.showBirthdate)
			return false;
		if (showPhone != other.showPhone)
			return false;
		if (skype == null) {
			if (other.skype != null)
				return false;
		} else if (!skype.equals(other.skype))
			return false;
		if (twitter == null) {
			if (other.twitter != null)
				return false;
		} else if (!twitter.equals(other.twitter))
			return false;
		if (website == null) {
			if (other.website != null)
				return false;
		} else if (!website.equals(other.website))
			return false;
		return true;
	}
}
