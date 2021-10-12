package com.guzcode.healthapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AnmActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TextView consult, create;
    private PagerViewAdapterAnm pagerViewAdapterAnm;

    private EditText nameAnm, lastNameAnm, ccAnm, emailAnm, bornAnm, hemLevelAnm;
    private RadioButton male, female;

    private String sexAnm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anm);
//        list


        consult = findViewById(R.id.tabConsultAnm);
        create = findViewById(R.id.tabCreateAnm);
        viewPager = findViewById(R.id.anm_pager);

//        Put Data


//      Form Data


        consult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
            }

        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
            }

        });
        pagerViewAdapterAnm = new PagerViewAdapterAnm(getSupportFragmentManager());
        viewPager.setAdapter(pagerViewAdapterAnm);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                onChangeTab(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void onChangeTab(int position) {

        if (position == 0) {

            consult.setBackgroundColor(getResources().getColor(R.color.purple_700));
            create.setBackgroundColor(getResources().getColor(R.color.purple_200));
            consult.setTextColor(getResources().getColor(R.color.white));
            create.setTextColor(getResources().getColor(R.color.black));
        }
        if (position == 1) {
            consult.setBackgroundColor(getResources().getColor(R.color.purple_200));
            create.setBackgroundColor(getResources().getColor(R.color.purple_700));
            create.setTextColor(getResources().getColor(R.color.white));
            consult.setTextColor(getResources().getColor(R.color.black));
        }


    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void anmSaveBtn(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "admin", null, 1);
        nameAnm = (EditText) findViewById(R.id.etNameAnm);
        lastNameAnm = (EditText) findViewById(R.id.etLNameAnm);
        ccAnm = (EditText) findViewById(R.id.etCCAnm);
        emailAnm = (EditText) findViewById(R.id.etEmailAnm);
        bornAnm = (EditText) findViewById(R.id.eTAnmBornDate);
        hemLevelAnm = (EditText) findViewById(R.id.eTAnmHemLevel);

        male = (RadioButton) findViewById(R.id.radioButton2);
        female = (RadioButton) findViewById(R.id.radioButton3);
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
                db.insert("anm_patients", null, register);
                db.close();
                Toast.makeText(this, "Patient Created", Toast.LENGTH_SHORT).show();
                if (state=="Anemia"){

                alertCreate("You probably have anemia");
                }else{
                alertCreate("Everything OK!");

                }
            } else {
                Toast.makeText(this, "You must to fill the inputs correctly", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void alertCreate(String text) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage(text);
        dialogBuilder.setCancelable(true).setTitle("Caution");
        dialogBuilder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                recreate();

            }
        });
        dialogBuilder.create().show();
    }

}