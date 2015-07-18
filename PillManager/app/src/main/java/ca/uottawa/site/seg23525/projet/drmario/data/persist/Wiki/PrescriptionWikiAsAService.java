package ca.uottawa.site.seg23525.projet.drmario.data.persist.Wiki;

import java.net.*;
import java.io.*;

/**
 * @author faeriol on 01/07/15.
 * A MedWiki that relies on the MedWiki webserver which is part of this project
 * MedWiki uses http://medlibrary.org/ as a Datasource
 */
public class PrescriptionWikiAsAService implements PrescriptionDrugWiki {

    public static final String DEFAULT_BASE_URL = "http://lanayru.faeriol.me";
    public static final String DEFAULT_SERVICE = "/wiki/";

    private String baseURL;
    private String service;

    public PrescriptionWikiAsAService() {
        this.baseURL=DEFAULT_BASE_URL;
        this.service = DEFAULT_SERVICE;
    }
    public PrescriptionWikiAsAService(String baseURL, String service){
            this.baseURL = baseURL;
            this.service = service;
    }
    @Override
    public String getDrugByName(String name) {
        String result = null;
        try {
            URL url = new URL(getDrugUrlByName(name));
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

    @Override
    public String getDrugUrlByName(String name){
        return baseURL+service+name;
    }

}
