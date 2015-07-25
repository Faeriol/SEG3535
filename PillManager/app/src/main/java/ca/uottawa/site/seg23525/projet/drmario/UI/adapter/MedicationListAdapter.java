package ca.uottawa.site.seg23525.projet.drmario.UI.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ca.uottawa.site.seg23525.projet.drmario.R;
import ca.uottawa.site.seg23525.projet.drmario.data.model.Medical.PrescribedMedication;

/**
 * Created by amirgaouaou on 15-07-23.
 */
public class MedicationListAdapter extends BaseAdapter {

     Context mContext;
     int resourceId;
     List<PrescribedMedication> meds;

    public MedicationListAdapter(Context mContext, int resourceId, List<PrescribedMedication> meds) {
        this.mContext = mContext;
        this.resourceId = resourceId;
        this.meds = meds;
    }

    @Override
    public int getCount() {
        return meds.size();
    }

    @Override
    public Object getItem(int position) {
        return meds.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void remove(int position) {
        meds.remove(position);
        notifyDataSetChanged();
    }

    static class ViewHolder {
        ImageView medImg;
        TextView medName;
        TextView commonName;
        TextView dosage;
        TextView takeAt;

        ViewHolder(View view) {
            medImg = (ImageView) view.findViewById(R.id.med_image);
            medName = (TextView) view.findViewById(R.id.med_name);
            commonName = (TextView) view.findViewById(R.id.common_name);
            dosage = (TextView) view.findViewById(R.id.dosage);
            takeAt = (TextView) view.findViewById(R.id.take_at);
            view.setTag(this);
        }
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = convertView == null
                ? new ViewHolder(convertView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.medication_item, parent, false))
                : (ViewHolder) convertView.getTag();

        viewHolder.medImg.setImageResource(R.mipmap.ic_launcher);
        viewHolder.medName.setText(meds.get(position).getMedication().getName());
        viewHolder.commonName.setText("\"" + meds.get(position).getMedication().getCommonName()+"\"");
        viewHolder.dosage.setText(meds.get(position).getDosage() + " mg");
        viewHolder.takeAt.setText("Taken at : 8:10 AM");


        return convertView;
    }
}
