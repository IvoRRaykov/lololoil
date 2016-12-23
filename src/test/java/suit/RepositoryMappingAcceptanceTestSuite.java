package suit;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.proxiad.extranet.it.core.repository.certification.CertificationEntityMappingTest;
import com.proxiad.extranet.it.core.repository.client.ClientEntityMappingTest;
import com.proxiad.extranet.it.core.repository.contract.ContractEntityMappingTest;
import com.proxiad.extranet.it.core.repository.education.EducationEntityMappingTest;
import com.proxiad.extranet.it.core.repository.education.SchoolEntityMappingTest;
import com.proxiad.extranet.it.core.repository.employee.EmployeeEntityMappingTest;
import com.proxiad.extranet.it.core.repository.employee.EmployeeHistoryEntityMappingTest;
import com.proxiad.extranet.it.core.repository.expertise.EmployeeExpertiseRelationshipMappingTest;
import com.proxiad.extranet.it.core.repository.expertise.ExpertiseEntityMappingTest;
import com.proxiad.extranet.it.core.repository.feedback.BehaviourLabelEntityMappingTest;
import com.proxiad.extranet.it.core.repository.feedback.CompetencyEntityMappingTest;
import com.proxiad.extranet.it.core.repository.feedback.FeedbackEntityMappingTest;
import com.proxiad.extranet.it.core.repository.feedback.GoalEntityMappingTest;
import com.proxiad.extranet.it.core.repository.interest.EmployeeInterestRelationshipMappingTest;
import com.proxiad.extranet.it.core.repository.interest.InterestEntityMappingTest;
import com.proxiad.extranet.it.core.repository.language.EmployeeLanguageRelationshipMappingTest;
import com.proxiad.extranet.it.core.repository.language.LanguageEntityMappingTest;
import com.proxiad.extranet.it.core.repository.project.EmployeeProjectRelationshipMappingTest;
import com.proxiad.extranet.it.core.repository.project.ProjectEntityMappingTest;
import com.proxiad.extranet.it.core.repository.security.CredentialEntityMappingTest;
import com.proxiad.extranet.it.core.repository.security.RoleEntityMappingTest;
import com.proxiad.extranet.it.core.repository.skill.EmployeeSkillRelationshipMappingTest;
import com.proxiad.extranet.it.core.repository.skill.SkillCategoryEntityMappingTest;
import com.proxiad.extranet.it.core.repository.skill.SkillEntityMappingTest;
import com.proxiad.extranet.it.core.repository.user.UserEntityMappingTest;
import com.proxiad.extranet.it.core.repository.workplace.OfficeEntityMappingTest;
import com.proxiad.extranet.it.core.repository.workplace.WorkplaceEntityMappingTest;

import junit.framework.TestSuite;

@RunWith(Categories.class)  
@IncludeCategory(RepositoryMappingAcceptanceTestSuite.class)  
@SuiteClasses({
	CertificationEntityMappingTest.class,
	ClientEntityMappingTest.class,
	ContractEntityMappingTest.class,
	EducationEntityMappingTest.class,
	SchoolEntityMappingTest.class,
	EmployeeEntityMappingTest.class,
	EmployeeHistoryEntityMappingTest.class,
	EmployeeProjectRelationshipMappingTest.class,
	ExpertiseEntityMappingTest.class,
	BehaviourLabelEntityMappingTest.class,
	CompetencyEntityMappingTest.class,
	FeedbackEntityMappingTest.class,
	GoalEntityMappingTest.class,
	InterestEntityMappingTest.class,
	LanguageEntityMappingTest.class,
	ProjectEntityMappingTest.class,
	CredentialEntityMappingTest.class,
	RoleEntityMappingTest.class,
	SkillCategoryEntityMappingTest.class,
	SkillEntityMappingTest.class,
	UserEntityMappingTest.class,
	OfficeEntityMappingTest.class,
	WorkplaceEntityMappingTest.class,
	EmployeeSkillRelationshipMappingTest.class,
	EmployeeInterestRelationshipMappingTest.class,
	EmployeeExpertiseRelationshipMappingTest.class,
	EmployeeLanguageRelationshipMappingTest.class,
	CertificationEntityMappingTest.class
}) 
public class RepositoryMappingAcceptanceTestSuite extends TestSuite {}
