package ca.uottawa.site.seg23525.projet.drmario.data.model.Medical.SQLite;

import ca.uottawa.site.seg23525.projet.drmario.data.model.Medical.Brand;
import ca.uottawa.site.seg23525.projet.drmario.data.persist.SQLite.SQLitePersistable;

/**
 * Created by faeriol on 28/06/15.
 */
public class SQLiteBrand extends Brand implements SQLitePersistable{

    private int id;

    public SQLiteBrand(int id, String name){
        super(name);
        this.id = id;
    }

    @Override
    public int getID() {
        return id;
    }

}
