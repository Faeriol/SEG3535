package ca.uottawa.site.seg23525.projet.drmario.UI;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

import ca.uottawa.site.seg23525.projet.drmario.R;

public class AddMedicamentActivity extends Activity  implements TimePickerDialog.OnTimeSetListener {

    private EditText medName;
    private EditText dosage;
    private EditText common_name;
    private EditText brand;

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

    public void onSave(View view){
        //
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

}
