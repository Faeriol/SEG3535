package ca.uottawa.site.seg23525.projet.drmario.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.uottawa.site.seg23525.projet.drmario.R;
import ca.uottawa.site.seg23525.projet.drmario.UI.helper.DAOFragment;


public class InteractionsFragment extends DAOFragment {

    public InteractionsFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_interactions, container, false);

        return rootView;
    }
}
