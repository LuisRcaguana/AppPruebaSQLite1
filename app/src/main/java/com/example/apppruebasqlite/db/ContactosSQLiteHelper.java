package com.example.apppruebasqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.apppruebasqlite.model.Contacto;

public class ContactosSQLiteHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "CONTACTOS_DB";
    static final int VERSION_DB = 2;

    static final String CREATE_TABLE_CONTACTOS =
            "CREATE TABLE " + ContactoContract.ContactoEntry.TABLE_NAME
                    + "( " +
                    ContactoContract.ContactoEntry.COLUMN_ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    ContactoContract.ContactoEntry.COLUMN_NAME +
                    " TEXT NOT NULL," +
                    ContactoContract.ContactoEntry.COLUMN_MAIL +
                    " TEXT NOT NULL);";


    public ContactosSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACTOS);
        cargaInicial(db);
    }

    private void cargaInicial(SQLiteDatabase db) {
        Contacto c1 = new Contacto("contactoIni1", "contIni1@mail.com");

        db.beginTransaction();

        ContentValues contactoValues = new ContentValues();
        contactoValues.put(ContactoContract.ContactoEntry.COLUMN_NAME,
                c1.getNombre());
        contactoValues.put(ContactoContract.ContactoEntry.COLUMN_MAIL,
                c1.getEmail());

        db.insert(ContactoContract.ContactoEntry.TABLE_NAME,
                null, contactoValues);

        Contacto c2 = new Contacto("contactoIni2", "contIni2@mail.com");

        contactoValues = new ContentValues();
        contactoValues.put(ContactoContract.ContactoEntry.COLUMN_NAME,
                c2.getNombre());
        contactoValues.put(ContactoContract.ContactoEntry.COLUMN_MAIL,
                c2.getEmail());

        db.insert(ContactoContract.ContactoEntry.TABLE_NAME,
                null, contactoValues);

        db.setTransactionSuccessful();
        db.endTransaction();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +
                ContactoContract.ContactoEntry.TABLE_NAME);
        onCreate(db);

    }
}
