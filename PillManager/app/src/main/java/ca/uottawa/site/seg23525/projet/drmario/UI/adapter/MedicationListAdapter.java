package ca.uottawa.site.seg23525.projet.drmario.UI.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;


import ca.uottawa.site.seg23525.projet.drmario.UI.model.MedicationItem;

/**
 * Created by amirgaouaou on 15-07-23.
 */
public class MedicationListAdapter extends ArrayAdapter<MedicationItem> {


    public MedicationListAdapter(Context context, int resource) {
        super(context, resource);
    }
}
