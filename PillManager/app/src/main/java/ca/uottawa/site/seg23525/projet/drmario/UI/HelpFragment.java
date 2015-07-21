package ca.uottawa.site.seg23525.projet.drmario.UI;

import android.app.Fragment;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.SearchView;

import ca.uottawa.site.seg23525.projet.drmario.R;
import ca.uottawa.site.seg23525.projet.drmario.data.persist.Help.HelpAsAService;
import ca.uottawa.site.seg23525.projet.drmario.data.persist.Help.HelpProvider;


public class HelpFragment extends Fragment implements SearchView.OnQueryTextListener {

    private HelpProvider help;
    private SearchView searchView;
    private WebView helpView;

    public HelpFragment(){
        super();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        help = new HelpAsAService();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        if(null==help){
            help = new HelpAsAService();
        }
        View rootView = inflater.inflate(R.layout.fragment_help, container, false);
        //wikiView = (WebView)container.findViewById(R.id.wikiView);
        helpView = (WebView) rootView.findViewById(R.id.helpView);
        System.out.println(helpView);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_wiki, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        System.out.println(query);
        //System.out.println(wiki.getDrugByName(query));
        helpView.loadUrl(help.getSubjectUrlByName(query.replaceAll("\\s", "")));
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
