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
    private String bill;
    private String totalAmout;
    private String totalTip;
    private String tipPerPerson;
    private String billPerPerson;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Summary")
                .setMessage("The total bill is : " + bill + "\n" +
                        "The total tip is : " + totalTip + "\n" +
                        "The total amount is : " + totalAmout + "\n" +
                        "\n" +
                        "Tip per person : " + tipPerPerson + "\n" +
                        "Bill per person : " + billPerPerson)
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


    public void setBill(String bill) {
        this.bill = bill;
    }

    public void setTotalAmout(String totalAmout) {
        this.totalAmout = totalAmout;
    }

    public void setTotalTip(String totalTip) {
        this.totalTip = totalTip;
    }

    public void setTipPerPerson(String tipPerPerson) {
        this.tipPerPerson = tipPerPerson;
    }

    public void setBillPerPerson(String billPerPerson) {
        this.billPerPerson = billPerPerson;
    }

}
