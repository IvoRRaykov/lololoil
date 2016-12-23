package com.proxiad.extranet.core.repository.audit;

import java.io.Serializable;

import com.proxiad.extranet.core.model.audit.AuditableEntity;
import com.proxiad.extranet.core.repository.helpers.CrudDao;

/**
 * Common interface for the repository access to audit entities
 * 
 * @author Mihail Merkov
 *
 * @param <AUDIT> the entity type, which will be audit
 * @param <TARGET> the target entity for which will be made the audit 
 */
public interface AuditDao<AUDIT extends AuditableEntity<TARGET>, TARGET extends Serializable> extends CrudDao<AUDIT, Long> {}
