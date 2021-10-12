package com.guzcode.healthapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class GliActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TextView consult, create;
    private PagerViewAdapterGli pagerViewAdapterGli;
    private EditText nameGli, lastNameGli, ccGli, bornGli, epsGli, gliLevel;
    private CheckBox sym1, sym2, sym3, sym4, sym5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gli);

        consult = findViewById(R.id.tabConsultGli);
        create = findViewById(R.id.tabCreateGli);
        viewPager = findViewById(R.id.hem_pager);


        viewPager.setCurrentItem(0);
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
        pagerViewAdapterGli = new PagerViewAdapterGli(getSupportFragmentManager());
        viewPager.setAdapter(pagerViewAdapterGli);

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

    public void createHemBtn(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "admin", null, 1);
        nameGli = (EditText) findViewById(R.id.etNameGli);
        lastNameGli = (EditText) findViewById(R.id.etLNameGli);
        ccGli = (EditText) findViewById(R.id.etCCGli);
        bornGli = (EditText) findViewById(R.id.eTGliBornDate);
        epsGli = (EditText) findViewById(R.id.etEpsGli);
        String name = nameGli.getText().toString();
        String lName = lastNameGli.getText().toString();
        String cc = ccGli.getText().toString();
        String born = bornGli.getText().toString();
        String eps = epsGli.getText().toString();
        sym1 = (CheckBox) findViewById(R.id.cBGliCreate1);
        sym2 = (CheckBox) findViewById(R.id.cBGliCreate2);
        sym3 = (CheckBox) findViewById(R.id.cBGliCreate3);
        sym4 = (CheckBox) findViewById(R.id.cBGliCreate4);
        sym5 = (CheckBox) findViewById(R.id.cBGliCreate5);
        boolean sym1Data = sym1.isChecked();
        boolean sym2Data = sym2.isChecked();
        boolean sym3Data = sym3.isChecked();
        boolean sym4Data = sym4.isChecked();
        boolean sym5Data = sym5.isChecked();

        if (sym1Data || sym2Data || sym3Data || sym4Data || sym5Data) {
            if (!name.isEmpty() && !lName.isEmpty() && !cc.isEmpty() && !born.isEmpty() && !eps.isEmpty()) {
                Intent intent = new Intent(this,
                        GliExam.class);
                intent.putExtra("p_name", name);
                intent.putExtra("p_lastname", lName);
                intent.putExtra("borndate", born);
                intent.putExtra("cc", cc);
                intent.putExtra("eps", eps);
                intent.putExtra("hasSymptom", 1);
                startActivity(intent);
            } else {

                Toast.makeText(this, "You must to fill the inputs correctly", Toast.LENGTH_SHORT).show();

            }
        } else {

            try (SQLiteDatabase db = admin.getWritableDatabase()) {


                if (!name.isEmpty() && !lName.isEmpty() && !cc.isEmpty() && !born.isEmpty() && !eps.isEmpty()) {
                    ContentValues register = new ContentValues();

                    register.put("p_name", name);
                    register.put("p_lastname", lName);
                    register.put("borndate", born);
                    register.put("cc", cc);
                    register.put("eps", eps);
                    register.put("hasSymptom", 0);
                    register.put("state", "Healthy");
                    db.insert("gli_patients", null, register);
                    db.close();
                    Toast.makeText(this, "Patient Created", Toast.LENGTH_SHORT).show();

                    alertCreate("Everything OK!");

                } else {
                    Toast.makeText(this, "You must to fill the inputs correctly", Toast.LENGTH_SHORT).show();
                }


            }
        }
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

    private void alertCreate(String text) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage(text);
        dialogBuilder.setCancelable(true).setTitle("Oh yea!");
        dialogBuilder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                recreate();

            }
        });
        dialogBuilder.create().show();
    }

}