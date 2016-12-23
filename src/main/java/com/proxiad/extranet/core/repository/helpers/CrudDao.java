package com.proxiad.extranet.core.repository.helpers;

import java.io.Serializable;
import java.util.List;

/**
 * Interface for all CRUD Operations
 *
 * @param <T> the entity for which is this DAO implementations
 * @param <I> the identifier of the persistent instance
 */
public interface CrudDao<T, I extends Serializable> {
    /**
     * Return the persistent instance of the given entity class
     * with the given identifier, or {@code null} if not found.
     *
     * @param id the identifier of the persistent instance
     * @return the persistent instance, or {@code null} if not found
     */
    T get(I id);

    /**
     * Return the list from all persistent instances of the given entity
     * or an empty {@link List}
     *
     * @return {@link List} from all persistent entities or an empty {@link List}
     */
    List<T> list();

    /**
     * Persist the given transient instance.
     *
     * @param entity the transient instance to persist
     * @return the generated identifier
     */
    I save(T entity);

    /**
     * Update the given persistent instance
     *
     * @param entity the persistent instance to update
     */
    void update(T entity);

    /**
     * Save or update the given persistent instance,
     * according to its id (matching the configured "unsaved-value"?).
     *
     * @param entity the persistent instance to save or update
     */
    void saveOrUpdate(T entity);

    /**
     * Delete the given persistent instance.
     *
     * @param entity the persistent instance to delete
     */
    void delete(T entity);

    /**
     * Flush data
     */
    void flush();

    /**
     * Truncate all data in current table
     */
    void truncate();

    /**
    * Re-read the state of the given persistent instance.
    */
    public void refresh(T entity);
}
