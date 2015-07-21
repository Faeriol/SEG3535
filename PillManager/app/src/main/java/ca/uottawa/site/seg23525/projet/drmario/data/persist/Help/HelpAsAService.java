package ca.uottawa.site.seg23525.projet.drmario.data.persist.Help;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author faeriol on 01/07/15.
 * A MedWiki that relies on the MedWiki webserver which is part of this project
 * MedWiki uses http://medlibrary.org/ as a Datasource
 */
public class HelpAsAService implements HelpProvider {

    public static final String DEFAULT_BASE_URL = "http://lanayru.faeriol.me";
    public static final String DEFAULT_SERVICE = "/help/";

    private String baseURL;
    private String service;

    public HelpAsAService() {
        this.baseURL=DEFAULT_BASE_URL;
        this.service = DEFAULT_SERVICE;
    }
    public HelpAsAService(String baseURL, String service){
            this.baseURL = baseURL;
            this.service = service;
    }
    @Override
    public String getSubjectByName(String name) {
        String result = null;
        try {
            URL url = new URL(getSubjectUrlByName(name));
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
    public String getSubjectUrlByName(String name){
        return baseURL+service+name;
    }

}
