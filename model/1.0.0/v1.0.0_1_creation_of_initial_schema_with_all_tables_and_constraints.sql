SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema proxiad-extranet
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `proxiad-extranet` ;

-- -----------------------------------------------------
-- Schema proxiad-extranet
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `proxiad-extranet` DEFAULT CHARACTER SET utf8 ;
USE `proxiad-extranet` ;

-- -----------------------------------------------------
-- Table `proxiad-extranet`.`CREDENTIAL`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proxiad-extranet`.`CREDENTIAL` (
  `ID_CREDENTIAL` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `LOGIN` VARCHAR(90) NOT NULL COMMENT 'Login for the particular User',
  `PASSWORD` VARCHAR(80) NOT NULL COMMENT 'Password for particular User',
  PRIMARY KEY (`ID_CREDENTIAL`),
  INDEX `CREDENTIAL_INX_1` USING BTREE (`LOGIN` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proxiad-extranet`.`USER_DETAILS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proxiad-extranet`.`USER_DETAILS` (
  `ID_USER_DETAILS` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `EMAIL` VARCHAR(45) NULL COMMENT 'The Email for specific User',
  `SKYPE` VARCHAR(45) NULL COMMENT 'The Skype name of specific User',
  `BIRTH_DATE` DATE NULL COMMENT 'Date of birth for specific User',
  `SHOW_BIRTH_DATE` TINYINT(1) NULL DEFAULT 1 COMMENT 'Flag, which indicate whether to be shown the birthdate or not',
  `PHONE` VARCHAR(45) NULL COMMENT 'The phone of specific User',
  `PHONE2` VARCHAR(45) NULL COMMENT 'The phone of specific User',
  `SHOW_PHONE` TINYINT(1) NULL DEFAULT 1 COMMENT 'Flag, which indicate whether to be shown the phone or not',
  `WEBSITE` VARCHAR(120) NULL COMMENT 'The website of specific user',
  `BLOG` VARCHAR(120) NULL COMMENT 'The blog of specific User',
  `LINKED_IN` VARCHAR(120) NULL COMMENT 'The Linked in of specific user',
  `FACEBOOK` VARCHAR(120) NULL COMMENT 'The Facebook of specific user',
  `TWITTER` VARCHAR(120) NULL COMMENT 'The Twitter of specific user',
  `FAX` VARCHAR(120) NULL COMMENT 'The fax of specific user',
  `QUICK_DESCRIPTION` VARCHAR(500) NULL,
  PRIMARY KEY (`ID_USER_DETAILS`),
  INDEX `USER_DETAILS_INX_1` USING BTREE (`EMAIL` ASC),
  INDEX `USER_DETAILS_INX_2` USING BTREE (`BIRTH_DATE` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proxiad-extranet`.`USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proxiad-extranet`.`USER` (
  `ID_USER` BIGINT(11) NOT NULL AUTO_INCREMENT COMMENT 'The primary key',
  `ID_USER_DETAILS` BIGINT(11) NOT NULL COMMENT 'Reference to USER_DETAILS',
  `ID_CREDENTIAL` BIGINT(11) NOT NULL COMMENT 'Referencial to credentials of an User',
  `SEX` CHAR(1) NOT NULL COMMENT 'The sex of specific User',
  `FIRST_NAME` VARCHAR(50) NOT NULL COMMENT 'The first name of English for specific User',
  `LAST_NAME` VARCHAR(50) NOT NULL COMMENT 'The last name of English for specific User',
  `PROFILE_PICTURE_PATH` VARCHAR(200) NULL COMMENT 'The path to Profile picture',
  `ACTIVE` TINYINT(1) NOT NULL DEFAULT 1 COMMENT 'Flag, which indicate whether the User is active, or does not work anymore into the company',
  `CREATED_ON` DATE NULL COMMENT 'Date of creation of this specific user',
  `CREATED_BY` BIGINT(11) NULL COMMENT 'The manager which creates this user',
  PRIMARY KEY (`ID_USER`),
  INDEX `USER_INX_1` (`ACTIVE` ASC),
  CONSTRAINT `USER_FK_1`
    FOREIGN KEY (`CREATED_BY`)
    REFERENCES `proxiad-extranet`.`USER` (`ID_USER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `USER_FK_2`
    FOREIGN KEY (`ID_CREDENTIAL`)
    REFERENCES `proxiad-extranet`.`CREDENTIAL` (`ID_CREDENTIAL`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `USER_FK_3`
    FOREIGN KEY (`ID_USER_DETAILS`)
    REFERENCES `proxiad-extranet`.`USER_DETAILS` (`ID_USER_DETAILS`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proxiad-extranet`.`ROLE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proxiad-extranet`.`ROLE` (
  `ID_ROLE` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NOT NULL COMMENT 'Name of the ROLE',
  `DESCRIPTION` VARCHAR(120) NOT NULL COMMENT 'Description of the ROLE',
  PRIMARY KEY (`ID_ROLE`),
  UNIQUE INDEX `ROLE_INX_1` USING BTREE (`NAME` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proxiad-extranet`.`USER_ROLE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proxiad-extranet`.`USER_ROLE` (
  `ID_USER` BIGINT(11) NOT NULL,
  `ID_ROLE` BIGINT(11) NOT NULL,
  PRIMARY KEY (`ID_USER`, `ID_ROLE`),
  CONSTRAINT `USER_ROLE_FK_1`
    FOREIGN KEY (`ID_USER`)
    REFERENCES `proxiad-extranet`.`USER` (`ID_USER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `USER_ROLE_FK_2`
    FOREIGN KEY (`ID_ROLE`)
    REFERENCES `proxiad-extranet`.`ROLE` (`ID_ROLE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proxiad-extranet`.`OFFICE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proxiad-extranet`.`OFFICE` (
  `ID_OFFICE` BIGINT(11) NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
  `CITY` VARCHAR(45) NOT NULL COMMENT 'Name of the city for this office',
  `POST_CODE` VARCHAR(45) NOT NULL COMMENT 'Post code of the city where is deployed this office',
  PRIMARY KEY (`ID_OFFICE`),
  INDEX `OFFICE_INX_1` USING BTREE (`CITY` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proxiad-extranet`.`CLIENT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proxiad-extranet`.`CLIENT` (
  `ID_CLIENT` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NOT NULL,
  `CODE_CEGID_1` VARCHAR(50) NULL,
  `CODE_CEGID_2` VARCHAR(10) NULL,
  `CODE_CEGID_3` VARCHAR(10) NULL,
  `SPECIAL` TINYINT(1) NULL,
  `ACTIVE` TINYINT(1) NULL,
  `SERVICE_CENTER` TINYINT(1) NULL,
  `GENERATE_CRA` TINYINT(1) NULL,
  PRIMARY KEY (`ID_CLIENT`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proxiad-extranet`.`PROJECT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proxiad-extranet`.`PROJECT` (
  `ID_PROJECT` BIGINT(11) NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
  `NAME` VARCHAR(45) NOT NULL COMMENT 'The name of the project',
  `DESCRIPTION` VARCHAR(200) NOT NULL COMMENT 'The short description about the project',
  `ID_CLIENT` BIGINT(11) NULL COMMENT 'The client reference is exists',
  `BUSINESS_DOMAIN` VARCHAR(80) NOT NULL COMMENT 'The business domain of the project',
  PRIMARY KEY (`ID_PROJECT`),
  UNIQUE INDEX `PROJECT_INX_1` USING BTREE (`NAME` ASC),
  CONSTRAINT `PROJECT_FK_1`
    FOREIGN KEY (`ID_CLIENT`)
    REFERENCES `proxiad-extranet`.`CLIENT` (`ID_CLIENT`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proxiad-extranet`.`WORKPLACE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proxiad-extranet`.`WORKPLACE` (
  `ID_WORKPLACE` BIGINT(11) NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
  `FLOOR` VARCHAR(45) NOT NULL COMMENT 'The floor where is deployed the workspace',
  `ROOM` VARCHAR(45) NOT NULL COMMENT 'The room desciption',
  `ID_OFFICE` BIGINT(11) NOT NULL COMMENT 'The office where is deployed this workspace',
  PRIMARY KEY (`ID_WORKPLACE`),
  CONSTRAINT `WORKPLACE_FK_1`
    FOREIGN KEY (`ID_OFFICE`)
    REFERENCES `proxiad-extranet`.`OFFICE` (`ID_OFFICE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proxiad-extranet`.`EMPLOYEE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proxiad-extranet`.`EMPLOYEE` (
  `ID_USER` BIGINT(11) NOT NULL COMMENT 'The id of an Employee, it is the inhered from an User',
  `DATE_OF_JOINING` DATE NOT NULL COMMENT 'The date of joining of the company',
  `DATE_OF_LEAVING` DATE NULL COMMENT 'The date of leaving of the company',
  `ID_WORKPLACE` BIGINT(11) NOT NULL COMMENT 'The workplace of specific employee',
  `POSITION` VARCHAR(150) NOT NULL COMMENT 'The current position of the Employee in Company',
  PRIMARY KEY (`ID_USER`),
  INDEX `EMPLOYEE_INX_1` USING BTREE (`DATE_OF_JOINING` ASC),
  CONSTRAINT `EMPLOYEE_FK_1`
    FOREIGN KEY (`ID_USER`)
    REFERENCES `proxiad-extranet`.`USER` (`ID_USER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `EMPLOYEE_FK_2`
    FOREIGN KEY (`ID_WORKPLACE`)
    REFERENCES `proxiad-extranet`.`WORKPLACE` (`ID_WORKPLACE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = euckr;


-- -----------------------------------------------------
-- Table `proxiad-extranet`.`EMPLOYEE_HISTORY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proxiad-extranet`.`EMPLOYEE_HISTORY` (
  `ID_EMPLOYEE_HISTORY` BIGINT(11) NOT NULL AUTO_INCREMENT COMMENT 'The primary key',
  `MODIFIED_ON` DATETIME NOT NULL COMMENT 'Date of modification of specific field',
  `MODIFIED_BY` BIGINT(11) NOT NULL COMMENT 'Modification of specific field by the admin',
  `NAME_OF_FIELD` VARCHAR(45) NOT NULL COMMENT 'Name of the modified field',
  `OLD_VALUE` VARCHAR(45) NULL COMMENT 'The old value of this field',
  `NEW_VALUE` VARCHAR(45) NULL COMMENT 'The new value of this field',
  `TARGET_EMPLOYEE` BIGINT(11) NOT NULL COMMENT 'The target employee for which is done this audit',
  PRIMARY KEY (`ID_EMPLOYEE_HISTORY`),
  CONSTRAINT `EMPLOYEE_AUDIT_FK_1`
    FOREIGN KEY (`TARGET_EMPLOYEE`)
    REFERENCES `proxiad-extranet`.`EMPLOYEE` (`ID_USER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `EMPLOYEE_AUDIT_FK_2`
    FOREIGN KEY (`MODIFIED_BY`)
    REFERENCES `proxiad-extranet`.`USER` (`ID_USER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proxiad-extranet`.`EMPLOYEE_PROJECT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proxiad-extranet`.`EMPLOYEE_PROJECT` (
  `ID_PROJECT` BIGINT(11) NOT NULL,
  `ID_EMPLOYEE` BIGINT(11) NOT NULL,
  `START_ON` DATE NOT NULL COMMENT 'The start date of the period when the employee is worked in this project',
  `END_ON` DATE NULL COMMENT 'The end date of the period when the employee is worked in this project',
  `CURRENT_PROJECT` TINYINT(1) NOT NULL DEFAULT 0 COMMENT 'Flag, which indicate, whether this is the current project of the employee',
  `RESPONSIBILITIES` VARCHAR(300) NULL COMMENT 'The responsibilities of the employee of this project',
  PRIMARY KEY (`ID_PROJECT`, `ID_EMPLOYEE`),
  CONSTRAINT `PROJECT_EMPLOYEE_FK_1`
    FOREIGN KEY (`ID_PROJECT`)
    REFERENCES `proxiad-extranet`.`PROJECT` (`ID_PROJECT`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `PROJECT_EMPLOYEE_FK_2`
    FOREIGN KEY (`ID_EMPLOYEE`)
    REFERENCES `proxiad-extranet`.`EMPLOYEE` (`ID_USER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proxiad-extranet`.`SKILL_CATEGORY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proxiad-extranet`.`SKILL_CATEGORY` (
  `ID_SKILL_CATEGORY` BIGINT(11) NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
  `TITLE` VARCHAR(45) NULL COMMENT 'Title of the Specific category for which belongs some skill',
  PRIMARY KEY (`ID_SKILL_CATEGORY`),
  UNIQUE INDEX `SKILL_CATEGORY_INX_1` USING BTREE (`TITLE` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proxiad-extranet`.`SKILL`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proxiad-extranet`.`SKILL` (
  `ID_SKILL` BIGINT(11) NOT NULL AUTO_INCREMENT COMMENT 'The Primary key',
  `TITLE` VARCHAR(45) NOT NULL COMMENT 'The title of the skill',
  `ID_SKILL_CATEGORY` BIGINT(11) NOT NULL COMMENT 'Relation to which category belongs this skill',
  PRIMARY KEY (`ID_SKILL`),
  UNIQUE INDEX `SKILL_INX_1` USING BTREE (`TITLE` ASC),
  CONSTRAINT `SKILL_FK_1`
    FOREIGN KEY (`ID_SKILL_CATEGORY`)
    REFERENCES `proxiad-extranet`.`SKILL_CATEGORY` (`ID_SKILL_CATEGORY`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proxiad-extranet`.`EMPLOYEE_SKILL`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proxiad-extranet`.`EMPLOYEE_SKILL` (
  `ID_EMPLOYEE` BIGINT(11) NOT NULL,
  `ID_SKILL` BIGINT(11) NOT NULL,
  `DATE` DATE NOT NULL COMMENT 'The date of adding of this skill',
  PRIMARY KEY (`ID_EMPLOYEE`, `ID_SKILL`),
  CONSTRAINT `EMPLOYEE_SKILL_FK_1`
    FOREIGN KEY (`ID_EMPLOYEE`)
    REFERENCES `proxiad-extranet`.`EMPLOYEE` (`ID_USER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `EMPLOYEE_SKILL_FK_2`
    FOREIGN KEY (`ID_SKILL`)
    REFERENCES `proxiad-extranet`.`SKILL` (`ID_SKILL`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proxiad-extranet`.`INTEREST`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proxiad-extranet`.`INTEREST` (
  `ID_INTEREST` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `TITLE` VARCHAR(50) NOT NULL COMMENT 'The tile of desired interest',
  PRIMARY KEY (`ID_INTEREST`),
  UNIQUE INDEX `INTERESTS_INX_1` (`TITLE` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proxiad-extranet`.`EMPLOYEE_INTEREST`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proxiad-extranet`.`EMPLOYEE_INTEREST` (
  `ID_EMPLOYEE` BIGINT(11) NOT NULL,
  `ID_INTEREST` BIGINT(11) NOT NULL,
  `DATE` DATE NOT NULL COMMENT 'The date of adding of this interest',
  PRIMARY KEY (`ID_EMPLOYEE`, `ID_INTEREST`),
  CONSTRAINT `EMPLOYEE_INTERESTS_FK_1`
    FOREIGN KEY (`ID_EMPLOYEE`)
    REFERENCES `proxiad-extranet`.`EMPLOYEE` (`ID_USER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `EMPLOYEE_INTERESTS_FK_2`
    FOREIGN KEY (`ID_INTEREST`)
    REFERENCES `proxiad-extranet`.`INTEREST` (`ID_INTEREST`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proxiad-extranet`.`CONTRACT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proxiad-extranet`.`CONTRACT` (
  `ID_CONTRACT` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `START_DATE` DATE NULL,
  `END_DATE` DATE NULL,
  `NUMBER` VARCHAR(20) NULL,
  `DAILY_RATE` DOUBLE NULL,
  `ID_EMPLOYEE` BIGINT(11) NOT NULL,
  `ID_CLIENT` BIGINT(11) NOT NULL,
  PRIMARY KEY (`ID_CONTRACT`),
  CONSTRAINT `CONTRACT_FK_1`
    FOREIGN KEY (`ID_CLIENT`)
    REFERENCES `proxiad-extranet`.`CLIENT` (`ID_CLIENT`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `CONTRACT_FK_2`
    FOREIGN KEY (`ID_EMPLOYEE`)
    REFERENCES `proxiad-extranet`.`EMPLOYEE` (`ID_USER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proxiad-extranet`.`SCHOOL`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proxiad-extranet`.`SCHOOL` (
  `ID_SCHOOL` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(200) NOT NULL COMMENT 'The name of the school',
  PRIMARY KEY (`ID_SCHOOL`),
  UNIQUE INDEX `NAME_UNIQUE` (`NAME` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proxiad-extranet`.`EDUCATION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proxiad-extranet`.`EDUCATION` (
  `ID_EDUCATION` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `DEGREE` VARCHAR(100) NOT NULL COMMENT 'The degree which is achieved from this education',
  `FIELD_OF_STUDY` VARCHAR(100) NULL COMMENT 'The field of study',
  `DESCRIPTION` VARCHAR(300) NULL COMMENT 'The description of the profession',
  `START_DATE` DATE NOT NULL COMMENT 'The date when this education has been started',
  `END_DATE` DATE NULL COMMENT 'The end date of education if exists',
  `ID_SCHOOL` BIGINT(11) NOT NULL COMMENT 'Reference to the School',
  `ID_EMPLOYEE` BIGINT(11) NOT NULL COMMENT 'Reference to the Employee',
  PRIMARY KEY (`ID_EDUCATION`),
  CONSTRAINT `EDUCATION_FK_1`
    FOREIGN KEY (`ID_SCHOOL`)
    REFERENCES `proxiad-extranet`.`SCHOOL` (`ID_SCHOOL`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `EDUCATION_FK_2`
    FOREIGN KEY (`ID_EMPLOYEE`)
    REFERENCES `proxiad-extranet`.`EMPLOYEE` (`ID_USER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proxiad-extranet`.`EXPERTISE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proxiad-extranet`.`EXPERTISE` (
  `ID_EXPERTISE` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` VARCHAR(80) NOT NULL COMMENT 'The description of expertise',
  PRIMARY KEY (`ID_EXPERTISE`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proxiad-extranet`.`EMPLOYEE_EXPERTISE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proxiad-extranet`.`EMPLOYEE_EXPERTISE` (
  `ID_EMPLOYEE` BIGINT(11) NOT NULL,
  `ID_EXPERTISE` BIGINT(11) NOT NULL,
  `DATE` DATE NULL COMMENT 'The date of adding of this expertise',
  PRIMARY KEY (`ID_EMPLOYEE`, `ID_EXPERTISE`),
  CONSTRAINT `EMPLOYEE_EXPERTISE_FK_1`
    FOREIGN KEY (`ID_EMPLOYEE`)
    REFERENCES `proxiad-extranet`.`EMPLOYEE` (`ID_USER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `EMPLOYEE_EXPERTISE_FK_2`
    FOREIGN KEY (`ID_EXPERTISE`)
    REFERENCES `proxiad-extranet`.`EXPERTISE` (`ID_EXPERTISE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proxiad-extranet`.`FEEDBACK`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proxiad-extranet`.`FEEDBACK` (
  `ID_FEEDBACK` BIGINT(11) NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
  `ID_MANAGER` BIGINT(11) NOT NULL COMMENT 'The manager responsible for this feedback',
  `GENERAL_IMPRESSIONS` VARCHAR(500) NULL COMMENT 'Comment for general impressions about the employee based on this feedback',
  `START_DATE` DATE NOT NULL COMMENT 'The start date interval for the feedback',
  `END_DATE` DATE NOT NULL COMMENT 'The end date interval for the feedback',
  `ID_TARGET_EMPLOYEE` BIGINT(11) NOT NULL COMMENT 'The target employee for which will be added this feedback',
  PRIMARY KEY (`ID_FEEDBACK`),
  CONSTRAINT `ID_FEEDBACK`
    FOREIGN KEY (`ID_MANAGER`)
    REFERENCES `proxiad-extranet`.`EMPLOYEE` (`ID_USER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FEEDBACK_FK_1`
    FOREIGN KEY (`ID_TARGET_EMPLOYEE`)
    REFERENCES `proxiad-extranet`.`EMPLOYEE` (`ID_USER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proxiad-extranet`.`BEHAVIOUR_LABEL`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proxiad-extranet`.`BEHAVIOUR_LABEL` (
  `ID_BEHAVIOUR_LABEL` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `LABEL` VARCHAR(200) NOT NULL COMMENT 'The label of behaviour',
  `DEFAULT_LABEL` TINYINT(1) NOT NULL COMMENT 'Flach, which represents whether this behaviour lavel, must be displayed as defaut competencies or not',
  PRIMARY KEY (`ID_BEHAVIOUR_LABEL`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proxiad-extranet`.`COMPETENCY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proxiad-extranet`.`COMPETENCY` (
  `ID_COMPETENCY` BIGINT(11) NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
  `ID_BEHAVIOUR_LABEL` BIGINT(11) NOT NULL COMMENT 'The references to the behaviour label',
  `SELF_APPRAISAL_RATING` INT NULL COMMENT 'The self appraisal rating of the employee for specific competence',
  `MANAGER_RATING` INT NULL COMMENT 'The manager rating of the employee for specific competence',
  `SELF_APPRAISAL_COMMENT` VARCHAR(50) NULL COMMENT 'The self appraisal comment for specific competence',
  `MANAGER_COMMENT` VARCHAR(50) NULL COMMENT 'The manager comment for specific competence',
  `ADDITIONAL_COMMENT` VARCHAR(50) NULL COMMENT 'Additional comment for specific competence',
  `ID_FEEDBACK` BIGINT(11) NOT NULL,
  PRIMARY KEY (`ID_COMPETENCY`),
  CONSTRAINT `COMPETENCY_FK_1`
    FOREIGN KEY (`ID_BEHAVIOUR_LABEL`)
    REFERENCES `proxiad-extranet`.`BEHAVIOUR_LABEL` (`ID_BEHAVIOUR_LABEL`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `COMPETENCY_FK_2`
    FOREIGN KEY (`ID_FEEDBACK`)
    REFERENCES `proxiad-extranet`.`FEEDBACK` (`ID_FEEDBACK`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proxiad-extranet`.`LANGUAGE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proxiad-extranet`.`LANGUAGE` (
  `ID_LANGUAGE` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(50) NOT NULL COMMENT 'The language name',
  `FLAG_CSS` VARCHAR(50) NOT NULL COMMENT 'The specific CSS class name for the flag of country',
  PRIMARY KEY (`ID_LANGUAGE`),
  UNIQUE INDEX `LANGUAGE_INX_1` USING BTREE (`NAME` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proxiad-extranet`.`EMPLOYEE_LANGUAGE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proxiad-extranet`.`EMPLOYEE_LANGUAGE` (
  `ID_EMPLOYEE` BIGINT(11) NOT NULL,
  `ID_LANGUAGE` BIGINT(11) NOT NULL,
  `LEVEL` VARCHAR(45) NOT NULL COMMENT 'Level of proficiency',
  PRIMARY KEY (`ID_EMPLOYEE`, `ID_LANGUAGE`),
  CONSTRAINT `EMPLOYEE_LANGUAGE_FK_1`
    FOREIGN KEY (`ID_EMPLOYEE`)
    REFERENCES `proxiad-extranet`.`EMPLOYEE` (`ID_USER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `EMPLOYEE_LANGUAGE_FK_2`
    FOREIGN KEY (`ID_LANGUAGE`)
    REFERENCES `proxiad-extranet`.`LANGUAGE` (`ID_LANGUAGE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proxiad-extranet`.`CERTIFICATION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proxiad-extranet`.`CERTIFICATION` (
  `ID_CERTIFICATION` BIGINT(11) NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
  `NAME` VARCHAR(100) NOT NULL COMMENT 'The name of the certificate',
  PRIMARY KEY (`ID_CERTIFICATION`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proxiad-extranet`.`EMPLOYEE_CERTIFICATION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proxiad-extranet`.`EMPLOYEE_CERTIFICATION` (
  `ID_EMPLOYEE` BIGINT(11) NOT NULL,
  `ID_CERTIFICATION` BIGINT(11) NOT NULL,
  `DATE` DATE NOT NULL,
  PRIMARY KEY (`ID_EMPLOYEE`, `ID_CERTIFICATION`),
  CONSTRAINT `EMPLOYEE_CERTIFICATION_FK_1`
    FOREIGN KEY (`ID_EMPLOYEE`)
    REFERENCES `proxiad-extranet`.`EMPLOYEE` (`ID_USER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `EMPLOYEE_CERTIFICATION_FK_2`
    FOREIGN KEY (`ID_CERTIFICATION`)
    REFERENCES `proxiad-extranet`.`CERTIFICATION` (`ID_CERTIFICATION`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proxiad-extranet`.`GOAL`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proxiad-extranet`.`GOAL` (
  `ID_GOAL` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `TITLE` VARCHAR(200) NOT NULL COMMENT 'The title of goal',
  PRIMARY KEY (`ID_GOAL`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin7;


-- -----------------------------------------------------
-- Table `proxiad-extranet`.`FEEDBACK_GOAL`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proxiad-extranet`.`FEEDBACK_GOAL` (
  `ID_FEEDBACK` BIGINT(11) NOT NULL,
  `ID_GOAL` BIGINT(11) NOT NULL,
  PRIMARY KEY (`ID_FEEDBACK`, `ID_GOAL`),
  CONSTRAINT `FEEDBACK_GOAL_FK_1`
    FOREIGN KEY (`ID_FEEDBACK`)
    REFERENCES `proxiad-extranet`.`FEEDBACK` (`ID_FEEDBACK`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FEEDBACK_GOAL_FK_2`
    FOREIGN KEY (`ID_GOAL`)
    REFERENCES `proxiad-extranet`.`GOAL` (`ID_GOAL`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proxiad-extranet`.`PARAM`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proxiad-extranet`.`PARAM` (
  `ID_PARAM` BIGINT(11) NOT NULL AUTO_INCREMENT COMMENT 'Primary key for the table',
  `PARAM_NAME` VARCHAR(60) NOT NULL COMMENT 'The name of a parameter',
  `PARAM_VALUE` VARCHAR(200) NOT NULL COMMENT 'The value of a parameter',
  `DESCRIPTION` VARCHAR(200) NOT NULL COMMENT 'The short descirption of the param',
  PRIMARY KEY (`ID_PARAM`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;