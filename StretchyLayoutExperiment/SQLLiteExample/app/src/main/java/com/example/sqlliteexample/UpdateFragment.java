package com.example.sqlliteexample;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateFragment extends Fragment {

    private EditText Update_id, Update_name, Update_email;
    private Button Update_bn;

    public UpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_update, container, false);

        Update_id=view.findViewById(R.id.txt_update_person_id);
        Update_name=view.findViewById(R.id.txt_update_person_name);
        Update_email=view.findViewById(R.id.txt_update_person_email);
        Update_bn=view.findViewById(R.id.bn_updateSave);

        Update_bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateContact();
            }
        });

        return view;
    }

    private void updateContact()
    {
        int id=Integer.parseInt((Update_id.getText().toString()));
        String name = Update_name.getText().toString();
        String email = Update_email.getText().toString();

        ContactDBHelper contactDBHelper=new ContactDBHelper(getActivity());
        SQLiteDatabase database=contactDBHelper.getWritableDatabase();

        contactDBHelper.updateContact(id,name,email,database);
        contactDBHelper.close();

        Toast.makeText(getActivity(), "Contact Updated", Toast.LENGTH_SHORT).show();

        Update_id.setText("");
        Update_name.setText("");
        Update_email.setText("");
    }

}
