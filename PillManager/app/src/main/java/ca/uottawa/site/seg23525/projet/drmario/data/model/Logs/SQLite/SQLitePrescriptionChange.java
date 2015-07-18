package ca.uottawa.site.seg23525.projet.drmario.data.model.Logs.SQLite;

import ca.uottawa.site.seg23525.projet.drmario.data.model.Logs.PrescriptionChange;
import ca.uottawa.site.seg23525.projet.drmario.data.persist.SQLite.SQLitePersistable;

/**
 * Created by faeriol on 28/06/15.
 */
public class SQLitePrescriptionChange extends PrescriptionChange implements SQLitePersistable {

    private int id;

    @Override
    public int getID() {
        return id;
    }

}
