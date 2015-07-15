package ca.uottawa.site.seg23525.projet.pillmanager.data.model.Medical.SQLite;

import java.util.Set;

import ca.uottawa.site.seg23525.projet.pillmanager.data.model.Medical.Brand;
import ca.uottawa.site.seg23525.projet.pillmanager.data.persist.SQLite.SQLitePersistable;

/**
 * Created by faeriol on 28/06/15.
 */
public class SQLiteBrand extends Brand implements SQLitePersistable{

    private int id;

    public SQLiteBrand(String name){
        super(name);
    }

    @Override
    public int getID() {
        return id;
    }

}
