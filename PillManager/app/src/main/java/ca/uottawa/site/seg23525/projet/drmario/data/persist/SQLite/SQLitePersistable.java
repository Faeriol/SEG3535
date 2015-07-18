package ca.uottawa.site.seg23525.projet.drmario.data.persist.SQLite;

/**
 * A object that can be persisted through SQLite
 */
public interface SQLitePersistable {

    /**
     * Gets the ID in the Database
     * @return the id
     */
    int getID();

}
