package com.proxiad.extranet.core.repository.contract;

import org.springframework.stereotype.Repository;

import com.proxiad.extranet.core.model.contract.Contract;
import com.proxiad.extranet.core.repository.helpers.GenericHibernateDao;

/**
 * Repository access implementation for {@link Contract} entity
 * 
 * @author Mihail Merkov
 */
@Repository
public class ContractDaoHibernate extends GenericHibernateDao<Contract, Long> implements ContractDao {}
