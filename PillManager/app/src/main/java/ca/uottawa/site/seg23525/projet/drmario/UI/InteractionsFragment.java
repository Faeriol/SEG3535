package ca.uottawa.site.seg23525.projet.drmario.UI;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import ca.uottawa.site.seg23525.projet.drmario.R;
import ca.uottawa.site.seg23525.projet.drmario.UI.helper.DAOFragment;


public class InteractionsFragment extends Fragment{

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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Context context = this.getActivity().getBaseContext();
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        Toast toast = Toast.makeText(context, "Not yet available. Please request help from your pharmacist", Toast.LENGTH_LONG);
        toast.show();

        return super.onOptionsItemSelected(item);
    }

}
