package lab.seg3525.tip_calculator.tipcalculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class MainActivity extends ActionBarActivity{

    private final static String SHARE_DIALOG_TAG = "YOURSHAREDIALOG";
    private EditText price;
    private EditText percentTip;
    private EditText nbPersonnes;
    private TextView currencyText;
    private Button calculate;
    private String currency;
    private String defaultTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currencyText = (TextView) findViewById(R.id.currency);
        currencyText.setText(currency);
        price = (EditText)findViewById(R.id.priceText);
        percentTip = (EditText)findViewById(R.id.tipPercentText);
        nbPersonnes = (EditText)findViewById(R.id.nbPersonnesText);
        calculate = (Button)findViewById(R.id.calculateButton);

    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        defaultTip = prefs.getString("defaultTip", "");
        currency = prefs.getString("currency","$");
        currencyText.setText(currency);
        percentTip.setText(defaultTip);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            //TODO: Go to Settings activity
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void recommendTip(View view){

        LayoutInflater inflater = LayoutInflater.from(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View customDialogView = inflater.inflate(R.layout.rating_dialog, null, false);
        final RatingBar ratingBar = (RatingBar) customDialogView.findViewById(R.id.ratingBar);
        builder.setView(customDialogView);

        int tipPercentage;
        
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                int tipPercentage = (int) (10 + (ratingBar.getRating() * 2));
                Toast.makeText(getApplicationContext(), "Tip is now : " + tipPercentage + " %",
                        Toast.LENGTH_SHORT).show();

                percentTip.setText("" + tipPercentage);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
            }
        });

        final AlertDialog mAlertDialog = builder.create();
        mAlertDialog.show();
    }

    public void calculate(View view) {
        // Alternatively, follow heuristics better and disallow click if fields are empty
        if(price.getText().toString().isEmpty() || nbPersonnes.getText().toString().isEmpty() || percentTip.getText().toString().isEmpty()){
            ProblemDialog problem = new ProblemDialog();
            problem.show(getFragmentManager(), SHARE_DIALOG_TAG);
            return;
        }
        float bill = Float.valueOf(price.getText().toString());
        int pers = Integer.valueOf( nbPersonnes.getText().toString());
        float tip = (Float.valueOf(percentTip.getText().toString())/100) + 1;

        DecimalFormat df = new DecimalFormat("0.00");
        String billString = df.format(bill);
        String totalAmout = df.format(bill*tip);
        String totalTip = df.format(bill*(tip-1));
        String tipPerPerson = df.format(bill*(tip-1)/pers);
        String billPerPerson = df.format(bill*tip/pers);

        TipStatsDialog shareDialog = new TipStatsDialog();

        shareDialog.setBill(billString);
        shareDialog.setTotalAmout(totalAmout);
        shareDialog.setTotalTip(totalTip);
        shareDialog.setTipPerPerson(tipPerPerson);
        shareDialog.setBillPerPerson(billPerPerson);
        shareDialog.setCurrency(currency);

        shareDialog.show(getFragmentManager(), SHARE_DIALOG_TAG);

    }

}
