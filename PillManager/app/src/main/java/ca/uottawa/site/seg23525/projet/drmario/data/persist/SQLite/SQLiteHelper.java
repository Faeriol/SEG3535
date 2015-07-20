package ca.uottawa.site.seg23525.projet.drmario.data.persist.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author faeriol
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

        // Inject some data
        db.execSQL("INSERT INTO Brand (name) VALUES ('Company1');");
        db.execSQL("INSERT INTO Brand (name) VALUES ('Company2');");
        db.execSQL("INSERT INTO Brand (name) VALUES ('Company3');");
        db.execSQL("INSERT INTO Brand (name) VALUES ('Company4');");
        db.execSQL("INSERT INTO Brand (name) VALUES ('Company5');");
        db.execSQL("INSERT INTO Medication (name, common_name) VALUES ('Cetirizine', 'Reactine');");
        db.execSQL("INSERT INTO Medication (name, common_name) VALUES ('Acetaminophen', 'Tylenol');");
        db.execSQL("INSERT INTO Medication (name, common_name) VALUES ('Ibuprofen', 'Advil');");
        db.execSQL("INSERT INTO Medication (name, common_name) VALUES ('Some other drug', 'Some common name');");
        db.execSQL("INSERT INTO Medication (name, common_name) VALUES ('Scientific looking name... Not', 'B');");
        db.execSQL("INSERT INTO Brand_Medication (med_id, brand_id) VALUES (1, 1);");
        db.execSQL("INSERT INTO Brand_Medication (med_id, brand_id) VALUES (1, 2);");
        db.execSQL("INSERT INTO Brand_Medication (med_id, brand_id) VALUES (1, 3);");
        db.execSQL("INSERT INTO Brand_Medication (med_id, brand_id) VALUES (1, 4);");
        db.execSQL("INSERT INTO Brand_Medication (med_id, brand_id) VALUES (2, 2);");
        db.execSQL("INSERT INTO Brand_Medication (med_id, brand_id) VALUES (2, 3);");
        db.execSQL("INSERT INTO Brand_Medication (med_id, brand_id) VALUES (4, 5);");
        db.execSQL("INSERT INTO Prescribed_Medication (brand_med_id, dosage) VALUES (1, 20);");
        db.execSQL("INSERT INTO Prescribed_Medication (brand_med_id, dosage) VALUES (2, 1000);");
        db.execSQL("INSERT INTO Prescribed_Medication (brand_med_id, dosage) VALUES (4, 25);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
