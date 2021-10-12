package com.guzcode.healthapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class AnmEditActivity extends AppCompatActivity {

    anmPatient patient;
    int id;
    TextView idTv;
    String sexAnm;

    private EditText nameAnm, lastNameAnm, ccAnm, emailAnm, bornAnm, hemLevelAnm;
    private RadioButton male, female;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anm_edit);
        id = getIntent().getIntExtra("id", 0);
        idTv = (TextView) findViewById(R.id.idEditAnm);
        idTv.setText("id: " + id);

        nameAnm = (EditText) findViewById(R.id.editNameAnm);
        lastNameAnm = (EditText) findViewById(R.id.editLNameAnm);
        ccAnm = (EditText) findViewById(R.id.editCCAnm);
        emailAnm = (EditText) findViewById(R.id.editEmailAnm);
        bornAnm = (EditText) findViewById(R.id.editAnmBornDate);
        hemLevelAnm = (EditText) findViewById(R.id.editAnmHemLevel);

        male = (RadioButton) findViewById(R.id.editMale);
        female = (RadioButton) findViewById(R.id.editFemale);
        anmSearch();
    }

    public void anmSearch() {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "admin", null, 1);
        try (SQLiteDatabase db = admin.getWritableDatabase()) {
            Cursor cursor = db.rawQuery("SELECT * FROM anm_patients WHERE id=" + id, null);
            while (cursor.moveToNext()) {
                nameAnm.setText(cursor.getString(1));
                lastNameAnm.setText(cursor.getString(2));
                bornAnm.setText(cursor.getString(3));
                ccAnm.setText(cursor.getString(4));
                String sex = cursor.getString(5);
                switch (sex) {
                    case ("male"):
                        male.setChecked(true);
                        break;
                    case ("female"):
                        female.setChecked(true);
                        break;

                }

                emailAnm.setText(cursor.getString(6));
                hemLevelAnm.setText(String.valueOf(cursor.getInt(7)));
            }
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void anmEditBtn(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "admin", null, 1);


        String name = nameAnm.getText().toString();
        String lName = lastNameAnm.getText().toString();
        String cc = ccAnm.getText().toString();
        String email = emailAnm.getText().toString();
        String born = bornAnm.getText().toString();
        try (SQLiteDatabase db = admin.getWritableDatabase()) {
            int hemLevel = Integer.parseInt(hemLevelAnm.getText().toString());

            if (male.isChecked() == true) {
                sexAnm = "male";
            }
            if (female.isChecked() == true) {
                sexAnm = "female";
            }
            String state = anmPatient.anmCalc(hemLevel, sexAnm, born);

            if (!(!name.isEmpty() && !lName.isEmpty() && !cc.isEmpty() && email.isEmpty() && !born.isEmpty() && !hemLevelAnm.getText().toString().isEmpty()) && (!male.isChecked() || !female.isChecked())) {
                ContentValues register = new ContentValues();

                register.put("p_name", name);
                register.put("p_lastname", lName);
                register.put("borndate", born);
                register.put("cc", cc);
                register.put("email", email);
                register.put("sex", sexAnm);
                register.put("hem_level", hemLevel);
                register.put("state", state);
                db.update("anm_patients",  register, "id="+id,null);
                db.close();
                Toast.makeText(this, "Patient Edited", Toast.LENGTH_SHORT).show();
                Intent backInt = new Intent(this, AnmActivity.class);
                startActivity(backInt);
            } else {
                Toast.makeText(this, "You must to fill the inputs correctly", Toast.LENGTH_SHORT).show();
            }
        }

    }


    public void anmDeleteBtn(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "admin", null, 1);
        try (SQLiteDatabase db = admin.getWritableDatabase()) {
            db.delete("anm_patients", "id=?", new String[]{String.valueOf(id)});
            db.close();
            Toast.makeText(this, "Patient deleted", Toast.LENGTH_SHORT).show();

            Intent backInt = new Intent(this, AnmActivity.class);
            startActivity(backInt);
        }
    }
}