package com.example.sqlliteexample;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadContactsFragment extends Fragment {

    private TextView Txt_Display;

    public ReadContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_read_contacts, container, false);

        Txt_Display=view.findViewById(R.id.txt_display);
        readContacts();
        return view;
    }

    private void readContacts()
    {
        ContactDBHelper contactDBHelper=new ContactDBHelper(getActivity());
        SQLiteDatabase database = contactDBHelper.getReadableDatabase();

        Cursor cursor= contactDBHelper.readContacts(database);
        String info="";
        while( cursor.moveToNext()){
            String id= Integer.toString(cursor.getInt(cursor.getColumnIndex(ContactContract.ContractEntry.CONTACT_ID)));
            Log.d("Debug","id"+ id);
            String name= cursor.getString(cursor.getColumnIndex(ContactContract.ContractEntry.NAME));
            String email= cursor.getString(cursor.getColumnIndex(ContactContract.ContractEntry.EMAIL));

            info=info+"\n\n "
                    +"id "+id
                    +"\nName "+name
                    +"\nEmail "+email;

         }

        Txt_Display.setText(info);
        cursor.close();
        database.close();
    }

}
