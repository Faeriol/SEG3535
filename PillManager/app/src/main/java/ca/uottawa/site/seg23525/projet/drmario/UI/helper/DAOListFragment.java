package ca.uottawa.site.seg23525.projet.drmario.UI.helper;

import android.app.ListFragment;

import ca.uottawa.site.seg23525.projet.drmario.data.persist.SQLite.DAO;

/**
 * Created by faeriol on 22/07/15.
 */
public abstract class DAOListFragment extends ListFragment {
    protected DAO dao;
    public void setDAO(DAO dao){
        this.dao = dao;
    }

    public void checkDAO(){
        if (dao == null) {
            dao = new DAO(getActivity().getApplicationContext());
            dao.open();
        }
    }
}
