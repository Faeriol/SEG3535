package ca.uottawa.site.seg23525.projet.drmario.data.persist.Help;

/**
 * An interface to define the Help behavior
 * @author faeriol
 */
public interface HelpProvider {

    /**
     * A function that fetches HTML representing a help entry on a certain subject
     * @param name
     * @return The JSON representing the page
     */
    String getSubjectByName(String name);

    /**
     * A function that returns the URL corresponding to a help entry for a certain subject
     * @param name
     * @return
     */
    String getSubjectUrlByName(String name);

}
