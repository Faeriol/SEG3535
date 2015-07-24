package ca.uottawa.site.seg23525.projet.drmario.UI;

    import android.content.Context;
    import android.graphics.Color;
    import android.os.Bundle;
    import android.support.v7.widget.CardView;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Button;
    import android.widget.LinearLayout;
    import android.widget.RelativeLayout;
    import android.widget.TextView;

    import java.util.List;

    import ca.uottawa.site.seg23525.projet.drmario.R;
    import ca.uottawa.site.seg23525.projet.drmario.UI.helper.DAOFragment;
    import ca.uottawa.site.seg23525.projet.drmario.UI.views.PrescribedMedCard;
    import ca.uottawa.site.seg23525.projet.drmario.data.model.Medical.PrescribedMedication;


public class HomeFragment extends DAOFragment {

    private LinearLayout layout;
    private PrescribedMedCard []medCards;

    public HomeFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        checkDAO();

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        layout = (LinearLayout) rootView;

        Context context = this.layout.getContext();

        List<PrescribedMedication> pMed = this.dao.getAllPrescribedMedication();
        medCards = new PrescribedMedCard[pMed.size()];
        for(int i = 0; i<pMed.size(); i++){
            medCards[i] = new PrescribedMedCard(context, pMed.get(i));
            layout.addView(medCards[i]);
            medCards[i].getCancelButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layout.removeView((View) v.getParent().getParent()); // Such troll
                }
            });
            medCards[i].getPostponeButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layout.removeView((View)v.getParent().getParent()); // Such troll
                }
            });
        }

        return rootView;
    }
}

