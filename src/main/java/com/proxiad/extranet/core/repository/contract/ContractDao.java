package com.proxiad.extranet.core.repository.contract;

import com.proxiad.extranet.core.model.contract.Contract;
import com.proxiad.extranet.core.repository.helpers.CrudDao;

/**
 * Repository access interface for {@link Contract} entity
 * 
 * @author Mihail Merkov
 */
public interface ContractDao extends CrudDao<Contract, Long> {}
