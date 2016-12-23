package com.proxiad.extranet.api;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proxiad.extranet.core.model.certification.Certification;
import com.proxiad.extranet.core.model.education.Education;
import com.proxiad.extranet.core.model.employee.Employee;
import com.proxiad.extranet.core.model.employee.beans.EmployeeDTO;
import com.proxiad.extranet.core.model.expertise.Expertise;
import com.proxiad.extranet.core.model.interest.Interest;
import com.proxiad.extranet.core.model.language.EmployeeLanguage;
import com.proxiad.extranet.core.model.language.Language;
import com.proxiad.extranet.core.model.skill.Skill;
import com.proxiad.extranet.core.service.employee.EmployeeService;

/**
 * REST API for access to {@link Employee} entity
 *
 * @author Mihail Merkov
 */
@RestController
@RequestMapping(path = "/api/employee")
public class EmployeeAPI {

	/*---------------------------------------------------- SERVICES --------------------------------------------------*/
	@Autowired
	private EmployeeService employeeService;
	
	/*------------------------------------------------------ API -----------------------------------------------------*/
	/**
	 * Fetch {@link List} from {@link EmployeeDTO} with main data only. Without any non required and security data. 
	 * @param withMainDataOnly {@link Boolean} which to indicate, that we would like to filter only the main data
	 */
	@GetMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<List<EmployeeDTO>> listActiveEmployeesWithMainDataOnly(@RequestParam boolean withMainDataOnly) {
		return new ResponseEntity<List<EmployeeDTO>>(employeeService.listActiveEmployeesWithMainDataOnly(), HttpStatus.OK);
	}
	
	/**
	 * Get specific {@link Employee} by its <code>id</code>
	 * @param id
	 */
	@GetMapping(path="/{id}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<EmployeeDTO> getEmployeeBy(@PathVariable final Long id) {
		EmployeeDTO employee = employeeService.getEmployeeById(id);
		
		if (employee == null) {
			return new ResponseEntity<EmployeeDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<EmployeeDTO>(employee, HttpStatus.OK);
	}

	/**
	 * Get specific profile picture of some {@link Employee} by its id
	 * @param id the id of some {@link Employee}
	 * @return byte[] representation of the image
     */
	@GetMapping(path="/{id}/profilePicture", produces=MediaType.IMAGE_JPEG_VALUE)
	ResponseEntity<byte[]> listActiveEmployeesWithMainDataOnly(@PathVariable final Long id) {
		return new ResponseEntity<byte[]>(employeeService.getProfilePictureByEmployeeId(id), HttpStatus.OK);
	}
	
	/**
	 * List all {@link Skill}s by specific {@link Employee}
	 * @param id the id of some {@link Employee}
     */
	@GetMapping(path="/{id}/skills", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<Set<Skill>> listEmployeeSkills(@PathVariable final Long id) {
		return new ResponseEntity<Set<Skill>>(employeeService.listSkillsWhichBelongsToEmployee(id), HttpStatus.OK);
	}
	
	/**
	 * List all {@link Interests}s by specific {@link Employee}
	 * @param id the id of some {@link Employee}
     */
	@GetMapping(path="/{id}/interests", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<Set<Interest>> listEmployeeInterests(@PathVariable final Long id) {
		return new ResponseEntity<Set<Interest>>(employeeService.listInterestsWhichBelongsToEmployee(id), HttpStatus.OK);
	}
	
	/**
	 * List all {@link Expertise}s by specific {@link Employee}
	 * @param id the id of some {@link Employee}
     */
	@GetMapping(path="/{id}/expertises", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<Set<Expertise>> listEmployeeExpertises(@PathVariable final Long id) {
		return new ResponseEntity<Set<Expertise>>(employeeService.listExpertisesWhichBelongsToEmployee(id), HttpStatus.OK);
	}
	
	/**
	 * List all {@link Language}s who a specific {@link Employee} knows
	 * @param id the id of some {@link Employee}
     */
	@GetMapping(path="/{id}/languages", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<Set<EmployeeLanguage>> listEmployeeLanguages(@PathVariable final Long id) {
		return new ResponseEntity<Set<EmployeeLanguage>>(employeeService.listLanguagesWhoEmployeeKnows(id), HttpStatus.OK);
	}
	
	/**
	 * List all {@link Education}s by specific {@link Employee}
	 * @param id the id of some {@link Employee}
     */
	@GetMapping(path="/{id}/educations", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<Set<Education>> listEmployeeEducations(@PathVariable final Long id) {
		return new ResponseEntity<Set<Education>>(employeeService.listEducationsOfEmployee(id), HttpStatus.OK);
	}
	
	/**
	 * List all {@link Certification}s by specific {@link Employee}
	 * @param id the id of some {@link Employee}
     */
	@GetMapping(path="/{id}/certifications", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<Set<Certification>> listEmployeeCertifications(@PathVariable final Long id) {
		return new ResponseEntity<Set<Certification>>(employeeService.listCertificationsWhichBelongsToEmployee(id), HttpStatus.OK);
	}
}
 