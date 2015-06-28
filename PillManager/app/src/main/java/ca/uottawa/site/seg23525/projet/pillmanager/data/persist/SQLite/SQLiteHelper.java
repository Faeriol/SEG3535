package ca.uottawa.site.seg23525.projet.pillmanager.data.persist.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by faeriol on 28/06/15.
 */
public class SQLiteHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "pillMan.db";
    public static final int DB_VERSION = 0;

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create Brand
        db.execSQL("CREATE TABLE brand(" +
                "_id INTEGER AUTOINCREMENT" +
                "name VARCHAR(255) NOT NULL);");
        //Create Ailment
        db.execSQL("CREATE TABLE ailment(" +
                "_id INTEGER AUTOINCREMENT" +
                "name VARCHAR(255) NOT NULL," +
                "description TEXT);");
        //Create Medication
        db.execSQL("CREATE TABLE medication(" +
                "_id INTEGER AUTOINCREMENT," +
                "name VARCHAR(255) NOT NULL," +
                "common_name VARCHAR(255));");
        //Create Interactions
        db.execSQL("CREATE TABLE interactions(" +
                "_id INTEGER AUTOINCREMENT," +
                "med1 INTEGER NOT NULL," +
                "med2 INTEGER NOT NULL);" );
        //Create MedThreatsAilment
        db.execSQL("CREATE TABLE threats(" +
                "_id INTEGER AUTOINCREMENT," +
                "med_id INTEGER NOT NULL REFERENCES medication," +
                "ailment_id INTEGER NOT NULL REFERENCES ailment);" );
        //Create BrandMedication
        db.execSQL("CREATE TABLE brand_medication(" +
                "_id INTEGER AUTOINCREMENT," +
                "med_id INTEGER NOT NULL REFERENCES medication," +
                "brand_id INTEGER NOT NULL REFERENCES brand," +
                "picture BLOB);");
        //Create PrescribedMedication
        db.execSQL("CREATE TABLE prescribed_medication(" +
                "_id INTEGER AUTOINCREMENT," +
                "brand_med_id INTEGER NOT NULL REFERENCES brand_medication," +
                "dosage REAL NOT NULL);");

        /// Logging
        // Prescription change
        db.execSQL("CREATE TABLE prescription_change(" +
                "_id INTEGER AUTOINCREMENT," +
                "presc INTEGER NOTE NULL REFERENCES prescribed_medication," +
                "old_brand INTEGER REFERENCES brand," +
                "new_brand INTEGER REFERENCES brand," +
                "dosage_delta REAL);");
        // IntakeInfo
        db.execSQL("CREATE TABLE intake_info(" +
                "_id INTEGER AUTOINCREMENT," +
                "presc INTEGER NOT NULL REFERENCES prescribed_medication," +
                "taken BOOLEAN NOT NULL DEFAULT false," +
                "delay INTEGER NOT NULL DEFAULT 0);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
