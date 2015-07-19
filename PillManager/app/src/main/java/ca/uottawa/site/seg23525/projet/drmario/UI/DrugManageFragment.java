package ca.uottawa.site.seg23525.projet.drmario.UI;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.uottawa.site.seg23525.projet.drmario.R;


public class DrugManageFragment extends Fragment {

    public DrugManageFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_drug_manage, container, false);

        return rootView;
    }
}
