package ca.uottawa.site.seg23525.projet.pillmanager.data.model.Medical.SQLite;

import java.util.Set;

import ca.uottawa.site.seg23525.projet.pillmanager.data.model.Medical.BrandMedication;
import ca.uottawa.site.seg23525.projet.pillmanager.data.model.Medical.PrescribedMedication;
import ca.uottawa.site.seg23525.projet.pillmanager.data.persist.SQLite.SQLitePersistable;

/**
 * @author faeriol on 28/06/15.
 */
public class SQLitePrescribedMedication extends PrescribedMedication implements SQLitePersistable{

    private int id;

    public SQLitePrescribedMedication(BrandMedication medication, float dosage){
        super(medication, dosage);
    }

    @Override
    public int getID() {
        return id;
    }

}
