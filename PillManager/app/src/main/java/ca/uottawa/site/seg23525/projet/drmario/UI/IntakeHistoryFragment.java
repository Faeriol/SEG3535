package ca.uottawa.site.seg23525.projet.drmario.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.uottawa.site.seg23525.projet.drmario.R;


public class IntakeHistoryFragment extends DAOFragment {

    public IntakeHistoryFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_intake_history, container, false);

        return rootView;
    }
}
