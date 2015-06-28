package ca.uottawa.site.seg23525.projet.pillmanager.data.persist;

import java.util.Set;

/**
 * The base interface to persist objects
 * @author faeriol
 */
public interface Persistable<T> {


    /**
     * Persists the current object
     * @return true if the operation was a success
     */
    boolean persist();

    /**
     * Will retrieve the current incomplete object from the database
     * For when you want to retrieve base on ID or UNIQUE property
     * @return The object was retrieved
     */
    boolean retrieveOne();

    /**
     * Will retrieve all corresponding objects from the @link{PersistLayer}
     * @return A set containing the retrieved objects
     */
    Set<T> retrieveMany();
}
