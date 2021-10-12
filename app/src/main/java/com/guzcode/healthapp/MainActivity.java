package com.guzcode.healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void hemBtn (View view){
        Intent nextHem = new Intent(this , GliActivity.class);
        startActivity(nextHem);
    }

    public void anmBtn (View view){
        Intent nextAnm = new Intent(this , AnmActivity.class);
        startActivity(nextAnm);
    }
}