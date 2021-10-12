package com.guzcode.healthapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AnmConsult#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnmConsult extends Fragment {


        ArrayList<anmPatient> listPatients;
    ListView listViewPatients;


    public AnmConsult() {
        // Required empty public constructor
    }


    public static AnmConsult newInstance(String param1, String param2) {
        AnmConsult fragment = new AnmConsult();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (container == null) {
            return null;
        }

        LinearLayout cl = (LinearLayout) inflater.inflate(R.layout.fragment_anm_consult, container, false);
        consultAnmPatients ();

        listViewPatients =( ListView) cl.findViewById(R.id.listAnm);
        listViewPatients.setAdapter(new anmListAdapter(this.getContext(), listPatients));


        return cl;
    }
    public void consultAnmPatients (){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.getContext(),
                "admin", null, 1);
        anmPatient patient =null;
        listPatients = new ArrayList<anmPatient>();
        try (SQLiteDatabase db = admin.getWritableDatabase()) {

            Cursor cursor = db.rawQuery("SELECT * FROM anm_patients",null);
            while(cursor.moveToNext()){
                patient = new anmPatient();
                patient.id=cursor.getInt(0);
                patient.name=cursor.getString(1);
                patient.lName=cursor.getString(2);
                patient.BornDate=cursor.getString(3);
                patient.cc=cursor.getString(4);
                patient.sex=cursor.getString(5);
                patient.email=cursor.getString(6);
                patient.hemLevel=cursor.getInt(7);
                patient.state=cursor.getString(8);

                listPatients.add(patient);
            }

        }
    }
}