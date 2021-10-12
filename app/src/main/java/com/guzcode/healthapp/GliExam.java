package com.guzcode.healthapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class GliExam extends AppCompatActivity {
    private String nameGli, lastNameGli, ccGli, bornGli, epsGli;
    private EditText gliLevelet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gli_exam);
    }
    public void creatExamBtn(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "admin", null, 1);
        nameGli = getIntent().getStringExtra("p_name");
        lastNameGli = getIntent().getStringExtra("p_lastname");
        bornGli = getIntent().getStringExtra("borndate");
        ccGli = getIntent().getStringExtra("cc");
        epsGli = getIntent().getStringExtra("eps");
        epsGli = getIntent().getStringExtra("eps");
        gliLevelet = (EditText) findViewById(R.id.etExam);
        int gliLevel = Integer.parseInt(gliLevelet.getText().toString());
        int hasSymptom= 1;
        String state = gliPatient.gliCalc(gliLevel);
        try (SQLiteDatabase db = admin.getWritableDatabase()) {


            if (!gliLevelet.getText().toString().isEmpty() ) {
                ContentValues register = new ContentValues();

                register.put("p_name", nameGli);
                register.put("p_lastname", lastNameGli);
                register.put("borndate", bornGli);
                register.put("cc", ccGli);
                register.put("eps", epsGli);
                register.put("hasSymptom", 1);
                register.put("state", state);
                db.insert("gli_patients", null, register);
                db.close();
                Toast.makeText(this, "Patient Created", Toast.LENGTH_SHORT).show();

                alertCreate("Hey you have a " + state);

            } else {
                Toast.makeText(this, "You must to fill the inputs correctly", Toast.LENGTH_SHORT).show();
            }
        }
    }
        private void alertCreate(String text) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            dialogBuilder.setMessage(text);
            dialogBuilder.setCancelable(true).setTitle("Caution!");
            dialogBuilder.create().show();
        }
}