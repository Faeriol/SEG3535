package ca.uottawa.site.seg23525.projet.drmario.data.persist.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ca.uottawa.site.seg23525.projet.drmario.data.model.Medical.Brand;
import ca.uottawa.site.seg23525.projet.drmario.data.model.Medical.BrandMedication;
import ca.uottawa.site.seg23525.projet.drmario.data.model.Medical.Medication;
import ca.uottawa.site.seg23525.projet.drmario.data.model.Medical.PrescribedMedication;
import ca.uottawa.site.seg23525.projet.drmario.data.model.Medical.SQLite.SQLiteBrand;
import ca.uottawa.site.seg23525.projet.drmario.data.model.Medical.SQLite.SQLitePrescribedMedication;
import ca.uottawa.site.seg23525.projet.drmario.data.model.Medical.SQLite.SQliteBrandMedication;

/**
 * A DAO to push data up
 * Created by faeriol on 15-07-19.
 */
public class DAO {
    private SQLiteDatabase db;
    private SQLiteHelper helper;
    private String[] medicationColumns = {"_id", "name", "common_name"};
    private String[] brandMedicationColumns = {"_id", "med_id", "brand_id"};
    private String[] brandColumns = {"_id", "name"};
    private String[] prescribedMedicationColumns = {"_id", "brand_med_id", "dosage"};

    public DAO(Context context) {
        helper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        db = helper.getWritableDatabase();
    }

    public void close() {
        helper.close();
    }

    public List<Medication> getAllMedication() {
        List<Medication> comments = new ArrayList<Medication>();

        Cursor cursor = db.query("Medication",
                medicationColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Medication medication = cursorToMedication(cursor);
            comments.add(medication);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    public Medication getMedicationWithID(int id){
        Cursor cursor = db.query("medication", medicationColumns, "_id=?", new String[]{Integer.toString(id)}, null, null, null);
        cursor.moveToFirst();
        return cursorToMedication(cursor);
    }

    private Medication cursorToMedication(Cursor cursor) {
        SQliteBrandMedication med = new SQliteBrandMedication();
        med.setID(cursor.getInt(0));
        med.setName(cursor.getString(1));
        med.setCommonName(cursor.getString(2));
        // Do stuffs about le Medicament! SHIT
        //brandMed.omment(cursor.getString(1));
        return med;
    }

    public List<BrandMedication> getAllBrandMedication() {
        List<BrandMedication> comments = new ArrayList<BrandMedication>();

        Cursor cursor = db.query("Brand_Medication",
                brandMedicationColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            BrandMedication brandMedication = cursorToBrandMedication(cursor);
            comments.add(brandMedication);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    public BrandMedication getBrandMedicationWithID(int id){
        Cursor cursor = db.query("brand_medication", brandMedicationColumns, "_id=?", new String[]{Integer.toString(id)}, null, null, null);
        cursor.moveToFirst();
        return cursorToBrandMedication(cursor);
    }

    private BrandMedication cursorToBrandMedication(Cursor cursor) {
        SQliteBrandMedication brandMed = new SQliteBrandMedication(cursor.getInt(0), getMedicationWithID(cursor.getInt(1)), getBrandWithID(cursor.getInt(2)));
        return brandMed;
    }

    public List<Brand> getAllBrand() {
        List<Brand> comments = new ArrayList<Brand>();

        Cursor cursor = db.query("Brand",
                brandColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Brand brand = cursorToBrand(cursor);
            comments.add(brand);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    public Brand getBrandWithID(int id){
        Cursor cursor = db.query("brand", brandColumns, "_id=?", new String[]{Integer.toString(id)}, null, null, null);
        cursor.moveToFirst();
        return cursorToBrand(cursor);
    }

    private Brand cursorToBrand(Cursor cursor) {
        SQLiteBrand brand = new SQLiteBrand(cursor.getInt(0), cursor.getString(1));
        return brand;
    }


    public List<PrescribedMedication> getAllPrescribedMedication() {
        List<PrescribedMedication> comments = new ArrayList<PrescribedMedication>();

        Cursor cursor = db.query("Prescribed_Medication",
                prescribedMedicationColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            PrescribedMedication prescribedMedication = cursorToPrescribedMedication(cursor);
            comments.add(prescribedMedication);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    private PrescribedMedication cursorToPrescribedMedication(Cursor cursor) {
        SQLitePrescribedMedication prescMed = new SQLitePrescribedMedication(cursor.getInt(0), getBrandMedicationWithID(cursor.getInt(1)), cursor.getFloat(2));
        return prescMed;
    }
}
