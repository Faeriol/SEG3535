package ca.uottawa.site.seg23525.projet.drmario.UI;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import ca.uottawa.site.seg23525.projet.drmario.R;
import ca.uottawa.site.seg23525.projet.drmario.UI.helper.DAOFragment;


public class InteractionsFragment extends Fragment implements SearchView.OnQueryTextListener {

    private SearchView searchView;

    public InteractionsFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_interactions, container, false);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_interactions, menu);
        MenuItem searchItem = menu.findItem(R.id.action_interaction_search);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
