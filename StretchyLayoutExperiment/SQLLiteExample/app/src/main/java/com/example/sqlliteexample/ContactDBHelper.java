package com.example.sqlliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ContactDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="contact_db";
    public static final int DATABASE_VERSION =1;
    public static final String CREATE_TABLE="create table "
            + ContactContract.ContractEntry.TABLE_NAME + "("+ ContactContract.ContractEntry.CONTACT_ID+ " number,"
            + ContactContract.ContractEntry.NAME+" text,"
            + ContactContract.ContractEntry.EMAIL+ " text);";
    public static final String DROP_TABLE =" drop table if exists "+ ContactContract.ContractEntry.TABLE_NAME;



    public ContactDBHelper(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
        Log.d("database Operations", "Database created ");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.d("Database Operations", "Table Created ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void AddContact(int id, String name, String email, SQLiteDatabase db)
    {
        Log.d("debug", "AddContact Id"+id);
        ContentValues contentValues=new ContentValues();
        contentValues.put(ContactContract.ContractEntry.CONTACT_ID,id);
        contentValues.put(ContactContract.ContractEntry.NAME,name);
        contentValues.put(ContactContract.ContractEntry.EMAIL,email);

        db.insert(ContactContract.ContractEntry.TABLE_NAME,null,contentValues);
        Log.d("Database Operations", "add Operation");

    }

    public Cursor readContacts(SQLiteDatabase database)
    {
        String[] projections={ContactContract.ContractEntry.CONTACT_ID, ContactContract.ContractEntry.NAME, ContactContract.ContractEntry.EMAIL};

        Cursor cursor = database.query(ContactContract.ContractEntry.TABLE_NAME, projections, null, null, null,null,null);

        return cursor;
    }

    public void updateContact(int id, String name, String email, SQLiteDatabase database){

        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactContract.ContractEntry.NAME,name);
        contentValues.put(ContactContract.ContractEntry.EMAIL,email);

        String selection=ContactContract.ContractEntry.CONTACT_ID+ "="+ id;
        database.update(ContactContract.ContractEntry.TABLE_NAME,contentValues,selection,null);
    }

}