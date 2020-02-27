package com.example.apppruebasqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.apppruebasqlite.model.Contacto;

import java.util.ArrayList;

public class ContactosPersistencia {

    private Context contexto;
    private ContactosSQLiteHelper csh;

    public ContactosPersistencia(Context contexto) {
        this.contexto = contexto;
        csh = new ContactosSQLiteHelper(contexto);
    }

    public SQLiteDatabase openReadable() {
        return csh.getReadableDatabase();
    }
    public SQLiteDatabase openWriteable() {
        return csh.getWritableDatabase();
    }
    public void close(SQLiteDatabase database) {
        database.close();
    }

    public long insertarContacto(Contacto contacto) {
        SQLiteDatabase database = openWriteable();
        database.beginTransaction();

        // gestionar el insert
        ContentValues contactoValues = new ContentValues();
        contactoValues.put(ContactoContract.ContactoEntry.COLUMN_NAME,
                contacto.getNombre());
        contactoValues.put(ContactoContract.ContactoEntry.COLUMN_MAIL,
                contacto.getEmail());

        long id = database.insert(ContactoContract.ContactoEntry.TABLE_NAME,
                null, contactoValues);

        if (id != -1) {
            database.setTransactionSuccessful();
        }

        database.endTransaction();
        close(database);

        return id;
    }

    public void actualizarContacto(Contacto contacto) {
        SQLiteDatabase database = openWriteable();
        database.beginTransaction();

        // gestionar el update
        ContentValues contactoValues = new ContentValues();
        contactoValues.put(ContactoContract.ContactoEntry.COLUMN_NAME,
                contacto.getNombre());
        contactoValues.put(ContactoContract.ContactoEntry.COLUMN_MAIL,
                contacto.getEmail());

        /*database.update(ContactoContract.ContactoEntry.TABLE_NAME,
                contactoValues,
                String.format("%s=%d",
                        ContactoContract.ContactoEntry.COLUMN_ID,
                        contacto.getId()),
                null);*/

        String [] whereArgs = { String.valueOf(contacto.getId()) };
        database.update(ContactoContract.ContactoEntry.TABLE_NAME,
                contactoValues,
                ContactoContract.ContactoEntry.COLUMN_ID + " = ?",
                whereArgs);


        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);
    }

    public void borrarContacto(long idContacto) {
        SQLiteDatabase database = openWriteable();
        database.beginTransaction();

        // ELIMINAR CONTACTO
        String [] whereArgs = {String.valueOf(idContacto)};
        database.delete(ContactoContract.ContactoEntry.TABLE_NAME,
                ContactoContract.ContactoEntry.COLUMN_ID + " = ?",
                whereArgs);

        /*database.delete(ContactoContract.ContactoEntry.TABLE_NAME,
                ContactoContract.ContactoEntry.COLUMN_ID
                        + " = " + idContacto,
                null);*/

        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);
    }

    public Contacto leerContacto(long idContacto) {
        Contacto contacto = null;
        SQLiteDatabase database = openReadable();

        String query = "SELECT "
                + ContactoContract.ContactoEntry.COLUMN_ID
                + ", " + ContactoContract.ContactoEntry.COLUMN_NAME
                + ", " + ContactoContract.ContactoEntry.COLUMN_MAIL
                + " FROM " + ContactoContract.ContactoEntry.TABLE_NAME
                + " WHERE "
                + ContactoContract.ContactoEntry.COLUMN_ID + " = ?";

        String [] whereArgs = {String.valueOf(idContacto)};

        Cursor cursor = database.rawQuery(query, whereArgs);

        long id;
        String nombre;
        String email;
        if (cursor.moveToFirst()) {
            id = cursor.getLong(cursor.getColumnIndex(
                    ContactoContract.ContactoEntry.COLUMN_ID));
            nombre = cursor.getString(cursor.getColumnIndex(
                    ContactoContract.ContactoEntry.COLUMN_NAME));
            email = cursor.getString(cursor.getColumnIndex(
                    ContactoContract.ContactoEntry.COLUMN_MAIL));

            contacto = new Contacto(nombre, email);
            contacto.setId(id);

        }

        cursor.close();
        close(database);

        return contacto;
    }

    public ArrayList<Contacto> leerContactos() {
        ArrayList<Contacto> listaContactos = new ArrayList<Contacto>();
        SQLiteDatabase database = openReadable();

        String query = "SELECT "
                + ContactoContract.ContactoEntry.COLUMN_ID
                + ", " + ContactoContract.ContactoEntry.COLUMN_NAME
                + ", " + ContactoContract.ContactoEntry.COLUMN_MAIL
                + " FROM " + ContactoContract.ContactoEntry.TABLE_NAME;

        Cursor cursor = database.rawQuery(query, null);

        Contacto contacto = null;
        Long id;
        String nombre;
        String email;
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getLong(cursor.getColumnIndex(
                        ContactoContract.ContactoEntry.COLUMN_ID));
                nombre = cursor.getString(cursor.getColumnIndex(
                        ContactoContract.ContactoEntry.COLUMN_NAME));
                email = cursor.getString(cursor.getColumnIndex(
                        ContactoContract.ContactoEntry.COLUMN_MAIL));

                contacto = new Contacto(nombre, email);
                contacto.setId(id);

                listaContactos.add(contacto);

            } while (cursor.moveToNext());
        }

        close(database);
        return listaContactos;
    }

    public ArrayList<Contacto> leerContactosEmail(String servidor) {
        ArrayList<Contacto> listaContactos = new ArrayList<Contacto>();
        SQLiteDatabase database = openReadable();

        String query = "SELECT "
                + ContactoContract.ContactoEntry.COLUMN_ID
                + ", " + ContactoContract.ContactoEntry.COLUMN_NAME
                + ", " + ContactoContract.ContactoEntry.COLUMN_MAIL
                + " FROM " + ContactoContract.ContactoEntry.TABLE_NAME
                +  " WHERE " + ContactoContract.ContactoEntry.COLUMN_MAIL
                + " LIKE '%"+ servidor + "%'";

        Cursor cursor = database.rawQuery(query, null);

        Contacto contacto = null;
        Long id;
        String nombre;
        String email;
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getLong(cursor.getColumnIndex(
                        ContactoContract.ContactoEntry.COLUMN_ID));
                nombre = cursor.getString(cursor.getColumnIndex(
                        ContactoContract.ContactoEntry.COLUMN_NAME));
                email = cursor.getString(cursor.getColumnIndex(
                        ContactoContract.ContactoEntry.COLUMN_MAIL));

                contacto = new Contacto(nombre, email);
                contacto.setId(id);

                listaContactos.add(contacto);

            } while (cursor.moveToNext());
        }

        close(database);
        return listaContactos;
    }
}
