package ca.uottawa.site.seg23525.projet.drmario.UI;

    import android.os.Bundle;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;

    import java.util.List;

    import ca.uottawa.site.seg23525.projet.drmario.R;
    import ca.uottawa.site.seg23525.projet.drmario.UI.helper.DAOFragment;
    import ca.uottawa.site.seg23525.projet.drmario.data.model.Medical.PrescribedMedication;


public class HomeFragment extends DAOFragment {


    public HomeFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        List<PrescribedMedication> pMed = this.dao.getAllPrescribedMedication();
        for(PrescribedMedication m : pMed){
            System.out.println(pMed);
        }

        return rootView;
    }

}

