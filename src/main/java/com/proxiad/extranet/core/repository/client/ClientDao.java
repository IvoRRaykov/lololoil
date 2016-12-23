package com.proxiad.extranet.core.repository.client;

import com.proxiad.extranet.core.model.client.Client;
import com.proxiad.extranet.core.repository.helpers.CrudDao;

/**
 * Repository access interface for {@link Client} entity
 * 
 * @author Mihail Merkov
 */
public interface ClientDao extends CrudDao<Client, Long> {}
