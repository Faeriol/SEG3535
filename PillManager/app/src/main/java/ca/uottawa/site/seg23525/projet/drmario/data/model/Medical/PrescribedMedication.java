package ca.uottawa.site.seg23525.projet.drmario.data.model.Medical;

import android.provider.CalendarContract;

/**
 * Represents some prescribed medication
 * @author faeriol
 */
public class PrescribedMedication {
    private BrandMedication medication;
    private float dosage; // Always in milligrams... DEMO
    private CalendarContract.Events schedule;

    public PrescribedMedication(BrandMedication medication, float dosage){
        this.medication = medication;
        this.dosage = dosage;
    }

    public BrandMedication getMedication(){
        return medication;
    }

    /**
     * Returns the dosage, in milligrams
     * @return
     */
    public float getDosage() {return dosage;}

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

    public void setSchedule(CalendarContract.Events schedule){ this.schedule=schedule; }

    public CalendarContract.Events getSchedule(){ return schedule;}

}
