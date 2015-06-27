package ca.uottawa.site.seg23525.projet.pillmanager.data.model;

import java.util.Set;

/**
 * Represents a Medication
 * @author faeriol
 */
public class Medication {

    private Set<Medication> interactions;
    private Set<Ailment> threats;
    private String name;
    private String commonName;

    protected Medication(Medication medication){
        this.interactions = medication.interactions;
        this.threats = medication.threats;
        this.name = medication.name;
        this.commonName = medication.commonName;
    }

    public String getName(){
        return name;
    }
}
