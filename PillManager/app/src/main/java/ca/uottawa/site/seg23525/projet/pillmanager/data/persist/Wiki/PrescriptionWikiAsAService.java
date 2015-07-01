package ca.uottawa.site.seg23525.projet.pillmanager.data.persist.Wiki;

import java.net.*;
import java.io.*;

/**
 * @author faeriol on 01/07/15.
 */
public class PrescriptionWikiAsAService implements PrescriptionDrugWiki {

    private String baseURL;

    public PrescriptionWikiAsAService(String baseURL){
            this.baseURL = baseURL;
    }
    @Override
    public String getDrugByName(String name) {
        String result = null;
        try {
            URL url = new URL(baseURL+name);
            URLConnection conn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inline;
            while((inline = in.readLine())!=null){
                result += inline;
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}
