package com.example.tarea4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ImageActivity extends AppCompatActivity {

    private TextView tvNameImage, tvGenderImage;
    private String getName, getGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        tvNameImage = (TextView)findViewById(R.id.tvNameImage);
        getName = getIntent().getStringExtra("Name");
        tvNameImage.setText("Name: "+ getName);

        tvGenderImage = (TextView)findViewById(R.id.tvGenderImage);
        getGender = getIntent().getStringExtra("Gender");
        tvGenderImage.setText("Gender: "+ getGender);

    }

    //this is to go to text reflection
    public void textReflections(View view){
        Intent i = new Intent(this, activity_textReflection.class);
        i.putExtra("Name", getName);
        i.putExtra("Gender", getGender);
        startActivity(i);
    }

    //this is to go to image reflection
    public void imageReflections(View view){
        Intent i = new Intent(this, activity_imageReflection.class);
        i.putExtra("Name", getName);
        i.putExtra("Gender", getGender);
        startActivity(i);
    }

    //this is used to go to home
    public void back(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    //this is used to exit the app
    public void exit(View view){
        AlertDialog.Builder builderExit = new AlertDialog.Builder(ImageActivity.this);
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