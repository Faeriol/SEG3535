package ca.uottawa.site.seg23525.projet.drmario.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import ca.uottawa.site.seg23525.projet.drmario.R;
import ca.uottawa.site.seg23525.projet.drmario.UI.helper.DAOListFragment;


public class PrescriptionHistoryFragment extends DAOListFragment {

    public PrescriptionHistoryFragment(){}

    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_prescription_history, container, false);

        listView = (ListView) rootView.findViewById(android.R.id.list);

        String[] values = new String[] { "July 1st: Reactine Added. Dosage: 20mg",
                                "July 2nd: Some other drug Added. Dosage: 30mg",
                                "July 4th: Dosage of Some other drug change to: 25mg"};
        // Defined Array values to show in ListView
        /*String[] values = new String[] { "Please populate me",
                "With stupid data that makes sense",
                "because i am not sure what to put"
        };*/

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        return rootView;
    }
}
