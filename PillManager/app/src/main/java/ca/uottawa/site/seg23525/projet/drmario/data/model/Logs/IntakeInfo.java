package ca.uottawa.site.seg23525.projet.drmario.data.model.Logs;

import ca.uottawa.site.seg23525.projet.drmario.data.model.Medical.PrescribedMedication;

/**
 * Tracking intake info
 * @author faeriol
 */
public abstract class IntakeInfo {
    private PrescribedMedication medication;
    private boolean taken; // Whether this scheduled medication was taken
    private int delay; // The delay (in seconds) after which the medication was taken
}
