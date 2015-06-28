package ca.uottawa.site.seg23525.projet.pillmanager.data.model.Medical;

/**
 * Represents an Ailment
 */
public abstract class Ailment {

    private String description;

    public Ailment(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

}
