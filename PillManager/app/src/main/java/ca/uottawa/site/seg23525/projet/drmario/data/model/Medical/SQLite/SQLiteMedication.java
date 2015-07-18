package ca.uottawa.site.seg23525.projet.drmario.data.model.Medical.SQLite;

import ca.uottawa.site.seg23525.projet.drmario.data.model.Medical.Medication;
import ca.uottawa.site.seg23525.projet.drmario.data.persist.SQLite.SQLitePersistable;

/**
 * @author faeriol on 15-06-27.
 */
public class SQLiteMedication extends Medication implements SQLitePersistable {
    private int id;

    public SQLiteMedication(Medication medication) {
        super(medication);
    }

    @Override
    public int getID() {
        return id;
    }
}
