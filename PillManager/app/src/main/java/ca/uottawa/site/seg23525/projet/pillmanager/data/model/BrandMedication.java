package ca.uottawa.site.seg23525.projet.pillmanager.data.model;

/**
 * A specific Brand of a Medication
 */
public class BrandMedication extends Medication {
    private Brand brand;

    protected BrandMedication(Medication medication, Brand brand){
        super(medication);
        this.brand = brand;
    }

    public Brand getBrand(){
        return brand;
    }


}
