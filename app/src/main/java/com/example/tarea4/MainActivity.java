package com.example.tarea4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //this is used to activity gender
    public void gender(View view){
        Intent i = new Intent(this, genderActivity.class);
        startActivity(i);
    }

    //this is used to exit the app
    public void exit(View view){
        AlertDialog.Builder builderExit = new AlertDialog.Builder(MainActivity.this);
        builderExit.setTitle("ALERT");
        builderExit.setMessage("You sure want to go out?");

        builderExit.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builderExit.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
            }
        });

        builderExit.create();
        builderExit.show();
    }

}