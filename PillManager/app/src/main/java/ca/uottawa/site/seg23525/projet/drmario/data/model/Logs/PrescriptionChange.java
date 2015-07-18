package ca.uottawa.site.seg23525.projet.drmario.data.model.Logs;

import android.provider.CalendarContract;

import java.util.Date;

import ca.uottawa.site.seg23525.projet.drmario.data.model.Medical.Brand;
import ca.uottawa.site.seg23525.projet.drmario.data.model.Medical.PrescribedMedication;

/**
 * Created by faeriol on 28/06/15.
 */
public abstract class PrescriptionChange {
    private PrescribedMedication medication;
    private Date date;
    private Brand from;
    private Brand to;
    private float dosageDelta;
    private CalendarContract.Events eventChange;
}
