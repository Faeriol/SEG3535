package lab.seg3525.tip_calculator.tipcalculator;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.EditText;


public class SettingsActivity extends PreferenceActivity{

    private static final String TIP = "pref_default_tip";
    private static final String CURRENCY = "pref_currency";

    private SharedPreferences sp;
    private EditText percentTip;
    private String currency;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        percentTip = (EditText)findViewById(R.id.tipPercentText);
        addPreferencesFromResource(R.xml.preferences);
    }

}