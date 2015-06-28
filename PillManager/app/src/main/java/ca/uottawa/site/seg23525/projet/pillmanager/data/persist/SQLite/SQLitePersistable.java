package ca.uottawa.site.seg23525.projet.pillmanager.data.persist.SQLite;

import ca.uottawa.site.seg23525.projet.pillmanager.data.persist.Persistable;

/**
 * A object that can be persisted through SQLite
 */
public interface SQLitePersistable<T> extends Persistable<T> {

    /**
     * Gets the ID in the Database
     * @return the id
     */
    int getID();

}
