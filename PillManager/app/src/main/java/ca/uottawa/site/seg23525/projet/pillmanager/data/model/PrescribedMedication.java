package ca.uottawa.site.seg23525.projet.pillmanager.data.model;

/**
 * Represents some prescribed medication
 * @author faeriol
 */
public class PrescribedMedication {
    private BrandMedication medication;
    private float dosage;

    protected PrescribedMedication(BrandMedication medication, float dosage){
        this.medication = medication;
        this.dosage = dosage;
    }

    public BrandMedication getMedication(){
        return medication;
    }

    /**
     * Sets a new @link{BrandMedication} in this PrescribedMedication
     * @param medication the new branded medication
     * @return true if we could change the brand
     */
    public boolean setMedication(BrandMedication medication){
        if (this.medication.getName().equals(medication.getName())){
            this.medication = medication;
            return true;
        }
        return false;
    }

}
