package ca.uottawa.site.seg23525.projet.pillmanager.data.model.Medical;

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

    public Medication(Medication medication){
        this.interactions = medication.interactions;
        this.threats = medication.threats;
        this.name = medication.name;
        this.commonName = medication.commonName;
    }

    public String getName(){
        return name;
    }
}
