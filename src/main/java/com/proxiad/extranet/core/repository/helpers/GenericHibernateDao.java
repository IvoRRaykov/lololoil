package com.proxiad.extranet.core.repository.helpers;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;

/**
 * This is helper class for managing CRUD operation over different entities
 *
 * @param <T> the entity for which is this DAO implementations
 * @param <I> the identifier of the persistent instance
 */
public class GenericHibernateDao<T, I extends Serializable> implements CrudDao<T, I> {
    protected final static int UNIQUE_RESULT = 1;
    protected final static int FIRST_ELEMENT = 0;
    private final static String DELIMITER = ".";

    private HibernateTemplate hibernateTemplate;
    
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public void setSessionFactory(final SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    public EntityManager getEntityManager() {
		return entityManager;
	}
    
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * Helper method which returns either the object or null
	 * @param criteria
	 * @return
	 */
	protected <E> E getEntityOrNull(CriteriaQuery<E> criteria) {
		List<E> result = entityManager.createQuery(criteria).getResultList();
		
		if (result.isEmpty()) {
			return null;
		}
		
		return result.get(FIRST_ELEMENT);
	}
	
	/**
	 * Helper method which returns {@link List} from <E>
	 * @param criteria
	 */
	protected <E> List<E> getResultList(CriteriaQuery<E> criteria) {
		return entityManager.createQuery(criteria).getResultList();
	}
	

	/**
	 * Helper method which returns {@link Set} from <E>
	 * @param criteria
	 */
	protected <E> Set<E> getResultSet(CriteriaQuery<E> criteria) {
		return new LinkedHashSet<E>(getResultList(criteria));
	}

	/**
     * @param daoHibernateClass a DAO implementation class
     * @return the entity interface handled by the DAO
     */
    private static Class<?> getEntityInterface(Class<?> daoHibernateClass) {
        Type superclass = daoHibernateClass.getGenericSuperclass();
        if (superclass instanceof ParameterizedType) {
            return (Class<?>) ((ParameterizedType) superclass).getActualTypeArguments()[0];
        }
        return getEntityInterface((Class<?>) superclass);
    }

    public Session getCurrentSession() {
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }

    /**
     * Return the persistent instance of the given entity class
     * with the given identifier, or {@code null} if not found.
     *
     * @param id the identifier of the persistent instance
     * @return the persistent instance, or {@code null} if not found
     */
    @Override
    @SuppressWarnings("unchecked")
    public T get(I id) {
        return (T)  hibernateTemplate.get(getEntityInterface(getClass()), id);
    }

    /**
     * Return the list from all persistent instances of the given entity
     * or an empty {@link List}
     *
     * @return {@link List} from all persistent entities or an empty {@link List}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> list() {
        return getCurrentSession().createCriteria(getEntityInterface(getClass())).list();
    }

    /**
     * Persist the given transient instance.
     *
     * @param entity the transient instance to persist
     * @return the generated identifier
     * @throws DatabaseException for some db errors
     */
    @Override
    @SuppressWarnings("unchecked")
    public I save(T entity) {
        return (I) hibernateTemplate.save(entity);
    }

    /**
     * Update the given persistent instance,
     * associating it with the current Hibernate {@link org.hibernate.Session}.
     *
     * @param entity the persistent instance to update
     */
    @Override
    public void update(T entity) {
        hibernateTemplate.update(entity);
    }

    /**
     * Save or update the given persistent instance,
     * according to its id (matching the configured "unsaved-value"?).
     * Associates the instance with the current Hibernate {@link org.hibernate.Session}.
     *
     * @param entity the persistent instance to save or update
     */
    @Override
    public void saveOrUpdate(T entity) throws DatabaseException {
        hibernateTemplate.saveOrUpdate(entity);
    }

    /**
     * Delete the given persistent instance.
     *
     * @param entity the persistent instance to delete
     */
    @Override
    public void delete(T entity) throws DatabaseException {
        hibernateTemplate.delete(entity);
    }

    /**
     * Flush data
     */
    @Override
    public void flush() {
        hibernateTemplate.flush();
    }

    /**
     * Truncate all data in current table
     */
    @Override
    public void truncate() {
        String tableName = getEntityInterface(getClass()).getName();
        Query query = getCurrentSession().createQuery("delete from " + tableName);
        query.executeUpdate();
    }

    /**
     * Re-read the state of the given persistent instance.
     * @param entity
     */
    public void refresh(T entity) {
        hibernateTemplate.refresh(entity);
    }

    /**
     * Concatenate the {@link java.lang.String}s with delimiter "."
     * @param properties
     * @return
     */
    public String chainRelationship(String... properties) {
        StringBuilder chainRelationshipStringBuilder = new StringBuilder();

        if (ArrayUtils.isNotEmpty(properties)) {
            if (UNIQUE_RESULT == properties.length) {
                chainRelationshipStringBuilder.append(properties[0]);
            } else {
                for (int i = 0; i < properties.length; i++) {
                    chainRelationshipStringBuilder.append(properties[i]).append(DELIMITER);
                }
                chainRelationshipStringBuilder =  new StringBuilder(chainRelationshipStringBuilder
                        .substring(0, chainRelationshipStringBuilder.lastIndexOf(DELIMITER)));
            }
        }

        return chainRelationshipStringBuilder.toString();
    }
}
