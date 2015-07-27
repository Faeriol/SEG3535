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


public class IntakeHistoryFragment extends DAOFragment {

    public IntakeHistoryFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_intake_history, container, false);

        ListView listview = (ListView) rootView.findViewById(R.id.intake_list_view);
        String[] values = new String[] { "July 25th: Reactine forgotten", "July 26th: Reactine delayed for 1h"};

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
