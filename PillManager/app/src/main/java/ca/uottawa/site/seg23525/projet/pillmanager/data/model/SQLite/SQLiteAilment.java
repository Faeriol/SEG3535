package ca.uottawa.site.seg23525.projet.pillmanager.data.model.SQLite;

import java.util.Set;

import ca.uottawa.site.seg23525.projet.pillmanager.data.model.Ailment;
import ca.uottawa.site.seg23525.projet.pillmanager.data.persist.SQLite.SQLitePersistable;

/**
 * A persistable Version of Ailment
 * @author faeriol
 */
public class SQLiteAilment extends Ailment implements SQLitePersistable {

    private int id;

    @Override
    public int getID() {
        return id;
    }

    @Override
    public boolean persist() {
        return false;
    }

    @Override
    public boolean retrieveOne() {
        return false;
    }

    @Override
    public Set retrieveMany() {
        return null;
    }
}
