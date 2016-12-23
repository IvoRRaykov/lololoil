package com.proxiad.extranet.core.repository.audit;

import java.io.Serializable;

import com.proxiad.extranet.core.model.audit.AuditableEntity;
import com.proxiad.extranet.core.repository.helpers.GenericHibernateDao;


/**
 * Common implementation for the repository access to audit entities
 * 
 * @author Mihail Merkov
 *
 * @param <AUDIT> the entity type, which will be audit
 * @param <TARGET> the target entity for which will be made the audit 
 */
public class AuditDaoHibernate<AUDIT extends AuditableEntity<TARGET>, TARGET extends Serializable> extends GenericHibernateDao<AUDIT, Long> implements AuditDao<AUDIT, TARGET> {}