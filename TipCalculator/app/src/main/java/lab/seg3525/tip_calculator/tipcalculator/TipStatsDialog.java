package lab.seg3525.tip_calculator.tipcalculator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by faeriol on 03/06/15.
 */
public class TipStatsDialog extends DialogFragment {
    private float share;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Your share of the bill is: " +share +"$")
                .setPositiveButton("Thanks", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // What do we do now?
                    }
                });
        // Do we even want cancel?
        /*builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
                });*/
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public void setShare(float share){
        this.share = share;
    }

}
