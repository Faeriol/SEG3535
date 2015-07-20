package ca.uottawa.site.seg23525.projet.drmario.data.model.Medical.SQLite;

import ca.uottawa.site.seg23525.projet.drmario.data.model.Medical.BrandMedication;
import ca.uottawa.site.seg23525.projet.drmario.data.model.Medical.PrescribedMedication;
import ca.uottawa.site.seg23525.projet.drmario.data.persist.SQLite.SQLitePersistable;

/**
 * @author faeriol on 28/06/15.
 */
public class SQLitePrescribedMedication extends PrescribedMedication implements SQLitePersistable{

    private int id;

    public SQLitePrescribedMedication(int id, BrandMedication medication, float dosage){
        super(medication, dosage);
        this.id = id;
    }

    @Override
    public int getID() {
        return id;
    }

}
