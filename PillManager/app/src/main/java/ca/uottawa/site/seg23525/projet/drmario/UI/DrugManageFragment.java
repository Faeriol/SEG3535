package ca.uottawa.site.seg23525.projet.drmario.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.hudomju.swipe.SwipeToDismissTouchListener;
import com.hudomju.swipe.adapter.ListViewAdapter;

import java.util.List;

import ca.uottawa.site.seg23525.projet.drmario.R;
import ca.uottawa.site.seg23525.projet.drmario.UI.adapter.MedicationListAdapter;
import ca.uottawa.site.seg23525.projet.drmario.UI.helper.DAOListFragment;
import ca.uottawa.site.seg23525.projet.drmario.data.model.Medical.PrescribedMedication;


public class DrugManageFragment extends DAOListFragment {


    public DrugManageFragment(){

    }

    private ListView medList;
    private List<PrescribedMedication> meds;
    private MedicationListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_drug_manage, container, false);

        checkDAO();


        medList = (ListView) rootView.findViewById(android.R.id.list);
        System.out.println(medList.toString());
        meds = dao.getAllPrescribedMedication();

        adapter = new MedicationListAdapter(getActivity(), R.layout.fragment_drug_manage, meds);

        medList.setAdapter(adapter);

        final SwipeToDismissTouchListener<ListViewAdapter> touchListener =
                new SwipeToDismissTouchListener<>(
                        new ListViewAdapter(medList),
                        new SwipeToDismissTouchListener.DismissCallbacks<ListViewAdapter>() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListViewAdapter view, int position) {
                                adapter.remove(position);
                            }
                        });
        medList.setOnTouchListener(touchListener);
        medList.setOnScrollListener((AbsListView.OnScrollListener) touchListener.makeScrollListener());
        medList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (touchListener.existPendingDismisses()) {
                    touchListener.undoPendingDismiss();
                } else {
                    Toast.makeText(getActivity(), "Position " + position, Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }
}
