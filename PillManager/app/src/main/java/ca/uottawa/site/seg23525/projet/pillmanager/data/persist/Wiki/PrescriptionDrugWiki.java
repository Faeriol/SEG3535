package ca.uottawa.site.seg23525.projet.pillmanager.data.persist.Wiki;

/**
 * An interface to define the Wiki behavior
 * @author faeriol
 */
public interface PrescriptionDrugWiki {

    /**
     * A function that fetches HTML representing a wiki entry on a certain prescription drug
     * @param name
     * @return The JSON representing the page
     */
    String getDrugByName(String name);

    /**
     * A function that returns the URL corresponding to a wiki entry for a certain prescription drug
     * @param name
     * @return
     */
    String getDrugUrlByName(String name);

    }
