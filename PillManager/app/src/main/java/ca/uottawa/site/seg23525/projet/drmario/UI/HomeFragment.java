package ca.uottawa.site.seg23525.projet.drmario.UI;

    import android.content.Context;
    import android.os.Bundle;
    import android.support.v7.widget.CardView;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Button;
    import android.widget.LinearLayout;

    import java.util.List;

    import ca.uottawa.site.seg23525.projet.drmario.R;
    import ca.uottawa.site.seg23525.projet.drmario.UI.helper.DAOFragment;
    import ca.uottawa.site.seg23525.projet.drmario.data.model.Medical.PrescribedMedication;


public class HomeFragment extends DAOFragment {

    private LinearLayout layout;

    public HomeFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        checkDAO();

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        layout = (LinearLayout) rootView;

        List<PrescribedMedication> pMed = this.dao.getAllPrescribedMedication();
        for(PrescribedMedication m : pMed){
            createCard(m);
        }

        return rootView;
    }


    private void createCard(PrescribedMedication m){
        Context context = getActivity().getApplicationContext();
        CardView cardView = new CardView(context);
        cardView.setCardElevation(5);

        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(16, 16, 16, 16);

        cardView.setLayoutParams(new LinearLayout.LayoutParams(params));
        layout.addView(cardView);

        Button cancelButton = new Button(context);
        cancelButton.setText("Cancel");
        cardView.addView(cancelButton);
    }
}

