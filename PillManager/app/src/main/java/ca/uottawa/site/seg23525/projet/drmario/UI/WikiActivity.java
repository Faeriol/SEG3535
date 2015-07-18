package ca.uottawa.site.seg23525.projet.drmario.UI;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.SearchView;

import ca.uottawa.site.seg23525.projet.drmario.R;
import ca.uottawa.site.seg23525.projet.drmario.data.persist.Wiki.PrescriptionDrugWiki;
import ca.uottawa.site.seg23525.projet.drmario.data.persist.Wiki.PrescriptionWikiAsAService;


/**
 * An activity to Handle wiki information and searches
 * @author faeriol
 */
public class WikiActivity extends Activity implements SearchView.OnQueryTextListener {

    private PrescriptionDrugWiki wiki;
    private SearchView searchView;
    private WebView wikiView;

    public WikiActivity(){
        super();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        wiki = new PrescriptionWikiAsAService();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(null==wiki){
            wiki = new PrescriptionWikiAsAService();
        }
        setContentView(R.layout.activity_wiki);
        wikiView = (WebView)findViewById(R.id.wikiView);
        /*wikiView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url != null && url.startsWith("http://")) {
                    view.getContext().startActivity(
                            new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    return true;
                } else {
                    return false;
                }
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wiki, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
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
        wikiView.loadUrl(wiki.getDrugUrlByName(query));
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
