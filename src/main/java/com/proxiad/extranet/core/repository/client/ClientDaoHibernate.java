package com.proxiad.extranet.core.repository.client;

import org.springframework.stereotype.Repository;

import com.proxiad.extranet.core.model.client.Client;
import com.proxiad.extranet.core.repository.helpers.GenericHibernateDao;

/**
 * Repository access implementation for {@link Client} entity
 * 
 * @author Mihail Merkov
 */
@Repository
public class ClientDaoHibernate extends GenericHibernateDao<Client, Long> implements ClientDao {}