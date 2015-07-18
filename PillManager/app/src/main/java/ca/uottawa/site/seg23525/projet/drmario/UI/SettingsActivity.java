package ca.uottawa.site.seg23525.projet.drmario.UI;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import ca.uottawa.site.seg23525.projet.drmario.R;


public class SettingsActivity extends PreferenceActivity{;

    private SharedPreferences sp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        addPreferencesFromResource(R.xml.preferences);
    }

}