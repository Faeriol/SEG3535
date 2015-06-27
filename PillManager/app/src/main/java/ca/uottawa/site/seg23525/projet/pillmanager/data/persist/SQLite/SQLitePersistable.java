package ca.uottawa.site.seg23525.projet.pillmanager.data.persist.SQLite;

import java.util.Set;

/**
 * A object that can be persisted through SQLite
 */
public interface SQLitePersistable<T> {
    int getID();

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
