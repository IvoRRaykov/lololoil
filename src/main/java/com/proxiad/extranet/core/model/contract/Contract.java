package com.proxiad.extranet.core.model.contract;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.proxiad.extranet.core.model.client.Client;
import com.proxiad.extranet.core.model.employee.Employee;

/**
 * Contract entity will keep information about the contract of an {@link Employee}
 * for specific {@link Client}
 * 
 * @author Mihail Merkov
 */
@Entity
@Table(name = "CONTRACT")
public class Contract {
	
	@Id
	@Column(name = "ID_CONTRACT")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_EMPLOYEE", nullable = false)
	private Employee employee;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CLIENT", nullable = false)
	private Client client;
	
	@Column(name = "NUMBER")
	private String number;
	
	@Column(name = "START_DATE")
	private Date startDate;
	
	@Column(name = "END_DATE")
	private Date endDate;
	
	@Column(name = "DAILY_RATE")
	private Double dailyRate;
	
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
	 * Get the {@link Employee} for which is this contract
	 * @return {@link Employee}
	 */
	public Employee getEmployee() {
		return employee;
	}
	
	/**
	 * Set the {@link Employee} for which is this contract
	 * @param employee {@link Employee}
	 */
	public void setEmployee(final Employee employee) {
		this.employee = employee;
	}
	
	/**
	 * Get the {@link Client} for which is this contract
	 * @return {@link Client}
	 */
	public Client getClient() {
		return client;
	}
	
	/**
	 * Set the {@link Client} for which is this contract
	 * @param employee {@link Client}
	 */
	public void setClient(final Client client) {
		this.client = client;
	}
	
	/**
	 * Get the contract number
	 * @return {@link String}
	 */
	public String getNumber() {
		return number;
	}
	
	/**
	 * Set the contract number
	 * @param number {@link String}
	 */
	public void setNumber(final String number) {
		this.number = number;
	}
	
	/**
	 * Get the start date of the contract
	 * @return {@link Date}
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	/**
	 * Set the start date of the contract
	 * @param startDate
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * Get the end date of the contract
	 * @return {@link Date}
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * Set the start date of the contract
	 * @return {@link Date}
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * Get the daily rate
	 * @return {@link Double}
	 */
	public Double getDailyRate() {
		return dailyRate;
	}
	
	/**
	 * Set the daily rate
	 * @param dailyRate {@link Double}
	 */
	public void setDailyRate(final Double dailyRate) {
		this.dailyRate = dailyRate;
	}
}
