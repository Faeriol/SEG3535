package ca.uottawa.site.seg23525.projet.drmario.UI.views;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

    //Components
    private ImageView imageView;
    private TextView medName, medCommonName;
    private Button cancelButton, postponeButton;

    public PrescribedMedCard(Context context, PrescribedMedication m) {
        super(context);
        this.m = m;
        setCardElevation(elevation);
        this.id = baseId;
        baseId += 100;
        this.setId(id);

        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 400);
        params.setMargins(bm, bm, bm, bm);
        setLayoutParams(new LinearLayout.LayoutParams(params));

        rlayout = new RelativeLayout(context);
        rlayout.setLayoutParams(new RelativeLayout.LayoutParams(params));

        addView(rlayout);



        this.createTexts();
        this.createButtons();

    }

    private void createTexts() {
        medName  = new TextView(this.getContext());
        medName.setId(id++);
        medName.setText(m.getMedication().getName());
        RelativeLayout.LayoutParams lay = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lay.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        lay.addRule(RelativeLayout.ALIGN_PARENT_START);
        lay.setMargins(bm, bm, bm, bm);
        medName.setLayoutParams(lay);

        rlayout.addView(medName);

        medCommonName  = new TextView(this.getContext());
        medCommonName.setId(id++);
        medCommonName.setText("\"" + m.getMedication().getCommonName() + "\"");
        lay = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lay.addRule(RelativeLayout.BELOW, medName.getId());
        lay.addRule(RelativeLayout.ALIGN_PARENT_START);
        lay.setMargins(bm, bm, bm, bm);
        medCommonName.setLayoutParams(lay);

        rlayout.addView(medCommonName);
    }

    private void createButtons(){
        cancelButton = new Button(this.getContext());
        cancelButton.setText("Cancel");
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
}
