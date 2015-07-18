package ca.uottawa.site.seg23525.projet.drmario.data.model.Medical.SQLite;

import ca.uottawa.site.seg23525.projet.drmario.data.model.Medical.Ailment;
import ca.uottawa.site.seg23525.projet.drmario.data.persist.SQLite.SQLitePersistable;

/**
 * A persistable Version of Ailment
 * @author faeriol
 */
public class SQLiteAilment extends Ailment implements SQLitePersistable {

    private int id;

    public SQLiteAilment(String description) {
        super(description);
    }

    @Override
    public int getID() {
        return id;
    }

}
