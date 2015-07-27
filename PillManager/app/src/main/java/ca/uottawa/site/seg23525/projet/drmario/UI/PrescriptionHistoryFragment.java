package ca.uottawa.site.seg23525.projet.drmario.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import ca.uottawa.site.seg23525.projet.drmario.R;
import ca.uottawa.site.seg23525.projet.drmario.UI.helper.DAOFragment;


public class PrescriptionHistoryFragment extends DAOFragment {

    public PrescriptionHistoryFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_prescription_history, container, false);

        ListView listview = (ListView) rootView.findViewById(R.id.prescription_list_view);
        String[] values = new String[] { "July 1st: Reactine Added. Dosage: 20mg",
                                "July 2nd: Some other drug Added. Dosage: 30mg",
                                "July 4th: Dosage of Some other drug change to: 25mg"};

        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this.getActivity().getBaseContext(),
                android.R.layout.simple_list_item_1, list);
        if (listview==null){
            listview = new ListView(this.getActivity().getBaseContext());
            System.out.println("List view was null again!");
        }
        listview.setAdapter(adapter);


        return rootView;
    }
}
