package ca.uottawa.site.seg23525.projet.drmario.data.model.Medical;

import java.util.Set;

/**
 * Represents a Medication
 * @author faeriol
 */
public abstract class Medication {

    private Set<Medication> interactions;
    private Set<Ailment> threats;
    private String name;
    private String commonName;

    public Medication(){};
    public Medication(Medication medication){
        this.interactions = medication.interactions;
        this.threats = medication.threats;
        this.name = medication.name;
        this.commonName = medication.commonName;
    }

    public String getName(){
        return name;
    }

    public void setInteractions(Set<Medication> interactions){
        this.interactions = interactions;
    }

    public void setThreats(Set<Ailment> threats){
        this.threats = threats;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCommonName(String name){
        this.commonName = name;
    }
}
