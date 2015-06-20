package lab.seg3525.tip_calculator.tipcalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private final static String SHARE_DIALOG_TAG = "YOURSHAREDIALOG";
    private SharedPreferences sp;
    private EditText price;
    private EditText percentTip;
    private EditText nbPersonnes;
    private Button calculate;
    private String currency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        setContentView(R.layout.activity_main);
        price = (EditText)findViewById(R.id.priceText);
        percentTip = (EditText)findViewById(R.id.tipPercentText);
        nbPersonnes = (EditText)findViewById(R.id.nbPersonnesText);
        calculate = (Button)findViewById(R.id.calculateButton);
        percentTip.setText(sp.getString("pref_default_tip", "15"));
        currency = sp.getString("pref_currency", "$");
        ((TextView)findViewById(R.id.priceView)).setText("Prix (" + currency + ")");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onResume(){ // We want to intercept the call and check the preferences
        super.onResume();
        percentTip.setText(sp.getString("pref_default_tip", "15"));
        currency = sp.getString("pref_currency", "$");
        ((TextView)findViewById(R.id.priceView)).setText("Prix (" + currency + ")");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void calculate(View view) {
        // Alternatively, follow heuristics better and disallow click if fields are empty
        if(price.getText().toString().isEmpty() || nbPersonnes.getText().toString().isEmpty() || percentTip.getText().toString().isEmpty()){
            ProblemDialog problem = new ProblemDialog();
            problem.show(getFragmentManager(), SHARE_DIALOG_TAG);
            return;
        }
        float bill = Float.valueOf(price.getText().toString());
        int pers = Integer.valueOf(nbPersonnes.getText().toString());
        float tip = (Float.valueOf(percentTip.getText().toString())/100) + 1;
        float yourShare = (bill*tip/pers);
        //System.out.println("Your share is: " + yourShare + currency); // Debug
        TipStatsDialog shareDialog = new TipStatsDialog();
        shareDialog.setShare(yourShare);
        shareDialog.show(getFragmentManager(), SHARE_DIALOG_TAG);

    }
}
