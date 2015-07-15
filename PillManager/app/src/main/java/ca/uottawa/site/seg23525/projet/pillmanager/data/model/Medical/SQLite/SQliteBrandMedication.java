package ca.uottawa.site.seg23525.projet.pillmanager.data.model.Medical.SQLite;

import ca.uottawa.site.seg23525.projet.pillmanager.data.model.Medical.Brand;
import ca.uottawa.site.seg23525.projet.pillmanager.data.model.Medical.BrandMedication;
import ca.uottawa.site.seg23525.projet.pillmanager.data.model.Medical.Medication;
import ca.uottawa.site.seg23525.projet.pillmanager.data.persist.SQLite.SQLitePersistable;

/**
 * Created by faeriol on 28/06/15.
 */
public class SQliteBrandMedication extends BrandMedication implements SQLitePersistable{

    private int id;

    public SQliteBrandMedication(Medication medication, Brand brand) {
        super(medication, brand);
    }

    @Override
    public int getID() {
        return id;
    }
}
