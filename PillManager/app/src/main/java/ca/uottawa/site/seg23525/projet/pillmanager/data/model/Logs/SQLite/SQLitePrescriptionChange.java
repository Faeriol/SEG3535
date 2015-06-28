package ca.uottawa.site.seg23525.projet.pillmanager.data.model.Logs.SQLite;

import java.util.Set;

import ca.uottawa.site.seg23525.projet.pillmanager.data.model.Logs.PrescriptionChange;
import ca.uottawa.site.seg23525.projet.pillmanager.data.persist.SQLite.SQLitePersistable;

/**
 * Created by faeriol on 28/06/15.
 */
public class SQLitePrescriptionChange extends PrescriptionChange implements SQLitePersistable<PrescriptionChange> {

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
    public Set<PrescriptionChange> retrieveMany() {
        return null;
    }
}
