package ca.uottawa.site.seg23525.projet.drmario.data.model.Medical;

/**
 * A specific Brand of a Medication
 */
public abstract class BrandMedication extends Medication {
    private Brand brand;
    private byte[] picture;

    public BrandMedication(){}

    public BrandMedication(Medication medication, Brand brand){
        super(medication);
        this.brand = brand;
    }

    public Brand getBrand(){
        return brand;
    }
    public void setBrand(Brand brand){this.brand = brand;}

    public byte[] getPicture(){
        return picture;
    }

    public void setPicture(byte[] picture){
        this.picture = picture;
    }

}
