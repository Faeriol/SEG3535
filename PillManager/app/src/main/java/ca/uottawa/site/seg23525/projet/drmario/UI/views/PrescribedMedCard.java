package ca.uottawa.site.seg23525.projet.drmario.UI.views;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Formatter;

import ca.uottawa.site.seg23525.projet.drmario.R;
import ca.uottawa.site.seg23525.projet.drmario.data.model.Medical.PrescribedMedication;

/**
 * Created by faeriol on 15-07-23.
 */
public class PrescribedMedCard extends CardView{

    private static int baseId = 100100;
    private PrescribedMedication m;
    private int id; // tracking
    private RelativeLayout rlayout;
    private int elevation = 5;
    private int bm = 16; // Base Margin
    private Calendar c;

    //Components
    private ImageView imageView;
    private TextView medName, medCommonName, dosage, dueTime;
    private Button cancelButton, postponeButton;

    public PrescribedMedCard(Context context, PrescribedMedication m) {
        super(context);
        c = Calendar.getInstance();
        this.m = m;
        setCardElevation(elevation);
        this.id = baseId;
        baseId += 100;
        this.setId(id);

        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
        params.setMargins(bm, bm, bm, bm);
        setLayoutParams(new LinearLayout.LayoutParams(params));

        rlayout = new RelativeLayout(context);
        rlayout.setLayoutParams(new RelativeLayout.LayoutParams(params));

        addView(rlayout);


        this.createImage();
        this.createTexts();
        this.createButtons();

    }

    private void createImage() {
        imageView = new ImageView(this.getContext());
        imageView.setId(id++);
        imageView.setImageResource(R.mipmap.ic_launcher);
        RelativeLayout.LayoutParams lay = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lay.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        lay.addRule(RelativeLayout.ALIGN_PARENT_START);
        lay.setMargins(bm, bm, bm, bm);
        imageView.setLayoutParams(lay);
        rlayout.addView(imageView);
    }

    private void createTexts() {
        medName  = new TextView(this.getContext());
        medName.setId(id++);
        medName.setText(m.getMedication().getName());
        medName.setTypeface(null, Typeface.BOLD);
        RelativeLayout.LayoutParams lay = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lay.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        lay.addRule(RelativeLayout.RIGHT_OF, imageView.getId());
        lay.setMargins(bm, bm, bm, bm);
        medName.setLayoutParams(lay);

        rlayout.addView(medName);

        dosage = new TextView(this.getContext());
        dosage.setId(id++);
        dosage.setText(m.getDosage() + "mg");
        lay = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lay.addRule(RelativeLayout.RIGHT_OF, medName.getId());
        lay.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        lay.setMargins(bm, bm, bm, bm);
        dosage.setLayoutParams(lay);

        rlayout.addView(dosage);

        medCommonName  = new TextView(this.getContext());
        medCommonName.setId(id++);
        medCommonName.setText("\"" + m.getMedication().getCommonName() + "\"");
        lay = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lay.addRule(RelativeLayout.BELOW, medName.getId());
        lay.addRule(RelativeLayout.RIGHT_OF, imageView.getId());
        lay.setMargins(bm, bm, bm, bm);
        medCommonName.setLayoutParams(lay);

        rlayout.addView(medCommonName);

        dueTime  = new TextView(this.getContext());
        dueTime.setId(id++);
        c.add(Calendar.HOUR_OF_DAY, 2);
        Formatter f = new Formatter();
        f.format("%tl:%tM", c, c);
        dueTime.setText("Take at: " +f.toString());
        lay = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lay.addRule(RelativeLayout.BELOW, medCommonName.getId());
        lay.addRule(RelativeLayout.RIGHT_OF, imageView.getId());
        lay.setMargins(bm, bm, bm, bm);
        dueTime.setLayoutParams(lay);

        rlayout.addView(dueTime);
    }

    private void createButtons(){
        cancelButton = new Button(this.getContext());
        cancelButton.setText("Dismiss");
        cancelButton.setId(id++);
        RelativeLayout.LayoutParams cancelLay = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        cancelLay.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        cancelLay.addRule(RelativeLayout.ALIGN_PARENT_END);
        cancelLay.setMargins(bm, bm, bm, bm);
        cancelLay.setMarginEnd(24);
        cancelButton.setLayoutParams(cancelLay);
        rlayout.addView(cancelButton);

        postponeButton = new Button(this.getContext());
        postponeButton.setId(id++);
        postponeButton.setText("Postpone");
        RelativeLayout.LayoutParams postponeLay = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        postponeLay.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        postponeLay.addRule(RelativeLayout.START_OF, cancelButton.getId());
        postponeLay.setMargins(bm, bm, bm, bm);
        postponeButton.setLayoutParams(postponeLay);

        rlayout.addView(postponeButton);
    }

    public Button getCancelButton(){
        return cancelButton;
    }

    public Button getPostponeButton(){
        return postponeButton;
    }

    public PrescribedMedication getMedication() {
        return m;
    }
}
