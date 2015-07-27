package ca.uottawa.site.seg23525.projet.drmario.UI;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

import ca.uottawa.site.seg23525.projet.drmario.R;
import ca.uottawa.site.seg23525.projet.drmario.data.model.Medical.Brand;
import ca.uottawa.site.seg23525.projet.drmario.data.model.Medical.BrandMedication;
import ca.uottawa.site.seg23525.projet.drmario.data.model.Medical.Medication;
import ca.uottawa.site.seg23525.projet.drmario.data.model.Medical.PrescribedMedication;
import ca.uottawa.site.seg23525.projet.drmario.data.persist.SQLite.DAO;

public class AddMedicamentActivity extends Activity  implements TimePickerDialog.OnTimeSetListener {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private EditText medName;
    private EditText dosage;
    private EditText common_name;
    private EditText brand;
    private ImageView medImage;
    private DAO dao;

    private RelativeLayout rlayout;

    private ArrayList<TextView> times;
    private ImageView addTime;

    private String time;
    private int timeId;
    private int editId;

    private boolean[] dateChosen;
    private Button[] dates;
    private final int WEEK =7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicament);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        dao = new DAO(this.getApplicationContext());
        dao.open();

        times = new ArrayList<TextView>();
        times.add((TextView) findViewById(R.id.time_text));
        timeId = 0;
        addTime = (ImageView) findViewById(R.id.add_time);
        rlayout = (RelativeLayout) findViewById(R.id.addmed_layout);

        dates = new Button[WEEK];
        dateChosen = new boolean[WEEK];

        dates[0]= (Button) findViewById(R.id.date1);
        dates[1]= (Button) findViewById(R.id.date2);
        dates[2]= (Button) findViewById(R.id.date3);
        dates[3]= (Button) findViewById(R.id.date4);
        dates[4]= (Button) findViewById(R.id.date5);
        dates[5]= (Button) findViewById(R.id.date6);
        dates[6]= (Button) findViewById(R.id.date7);

        for(int i=0; i<WEEK; i++){
            dates[i].setOnClickListener(new DateClickListener(i));
        }

        medName = (EditText) findViewById(R.id.med_name_edit);
        dosage  = (EditText) findViewById(R.id.dosage_edit);
        common_name = (EditText) findViewById(R.id.common_name_edit);
        brand = (EditText) findViewById(R.id.brand_edit);
        medImage = (ImageView) findViewById(R.id.image_med);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_medicament, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void getPhoto(View view){
        dispatchTakePictureIntent();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            medImage.setImageBitmap(imageBitmap);
        }
    }


    public void addTime(View view){
        showTimePicker();
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        if(editId !=0){
            if(editId>1)
                times.get(editId).setText("| " + hourOfDay + ":" + minute);
            else
                times.get(editId).setText(hourOfDay + ":" + minute);
            editId=0;
        }else {
            TextView text = new TextView(AddMedicamentActivity.this);
            text.setId(++timeId);
            if (timeId > 1)
                text.setText("| " + hourOfDay + ":" + minute);
            else
                text.setText(hourOfDay + ":" + minute);
            text.setTextAppearance(this, android.R.style.TextAppearance_Large);
            text.setTextColor(Color.BLACK);
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showTimePicker();
                    editId = v.getId();
                }
            });
            RelativeLayout.LayoutParams lay = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lay.addRule(RelativeLayout.ALIGN_BOTTOM, times.get(times.size() - 1).getId());
            lay.addRule(RelativeLayout.END_OF, times.get(times.size() - 1).getId());
            lay.setMargins(4, 0, 4, 0);
            times.add(text);
            rlayout.addView(text, lay);

            RelativeLayout.LayoutParams lay2 = new RelativeLayout.LayoutParams(dpToPx(30), dpToPx(30));
            lay2.addRule(RelativeLayout.END_OF, timeId);
            lay2.addRule(RelativeLayout.ALIGN_TOP, times.get(0).getId());
            addTime.setLayoutParams(lay2);
        }
    }

    public static int dpToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public void showTimePicker(){
        Calendar now = Calendar.getInstance();
        TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                true);
        timePickerDialog.show(getFragmentManager(), "TimePickerDialog");
    }

    public class DateClickListener implements View.OnClickListener
    {

        int dateId;
        public DateClickListener(int dateId) {
            this.dateId = dateId;
        }

        @Override
        public void onClick(View v)
        {
            dateChosen[dateId]= !dateChosen[dateId];
            if(dateChosen[dateId]){
                dates[dateId].setTextColor(getResources().getColor(R.color.primary));
            }else{
                dates[dateId].setTextColor(Color.BLACK);
            }
        }

    };

    public void onSave(View view){

        String errorMessage ="";
        boolean flag=false;
        boolean error=false;

        for(int i=0;i<WEEK;i++){
            if(dateChosen[i]){
                flag=true;
            }
        }

        if(medName.getText().toString().matches("")){
            error=true;
            errorMessage = "Please specify a Medicament Name";
        }else if(dosage.getText().toString().matches("")){
            errorMessage = "Please specify a value for the dosage";
            error=true;
        }else if(times.size()<2){
            error=true;
            errorMessage = "Please specify a time value for the reminder";
        }else if(!flag){
            errorMessage = "Please chose a date";
            error=true;
        }

        if(error){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this,R.style.Base_Theme_AppCompat_Light_Dialog_Alert);

            // set title
            alertDialogBuilder.setTitle("Incomplete Form");

            // set dialog message
            alertDialogBuilder
                    .setMessage(errorMessage)
                    .setCancelable(true)
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        }else{

            Medication me = new Medication();
            me.setName(medName.getText().toString());
            me.setCommonName(common_name.getText().toString());
            BrandMedication br = new BrandMedication(me, new Brand(brand.getText().toString()));

            PrescribedMedication med = new PrescribedMedication(br, Integer.parseInt(dosage.getText().toString()));
            dao.insertPrescribedMedicament(med);

            Toast.makeText(this, "Medicament added", Toast.LENGTH_SHORT);
            finish();
        }

    }

}
