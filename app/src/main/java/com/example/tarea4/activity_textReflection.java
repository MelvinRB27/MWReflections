package com.example.tarea4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.Random;

public class activity_textReflection extends AppCompatActivity {
    private TextView tvNameTR, tvGenderTR, tvReflection;
    private Integer q;
    private String getGender, getName;

    private int actualPosition = -1;
    private static final Random textRandom = new Random();

    //array women text reflections
    private static final Integer[] imageIDFeme ={
            R.string.feme1, R.string.feme2, R.string.feme3,
            R.string.feme4, R.string.feme5,
    };

    //array men text reflections
    private static final Integer[] imageIDMale ={
            R.string.male1, R.string.male2, R.string.male3,
            R.string.male4, R.string.male5,
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_reflection);

        tvNameTR = (TextView) findViewById(R.id.tvNameTR);
        getName = getIntent().getStringExtra("Name");
        tvNameTR.setText("Name: "+getName);

        tvGenderTR = (TextView) findViewById(R.id.tvGenderTR);
        getGender = getIntent().getStringExtra("Gender");
        tvGenderTR.setText("Gender: "+getGender);

        //validate gender
        validateGender();

    }

    //this is to show different reflections according gender
    private void validateGender(){
        switch (getGender){
            case "Feminine":
                //this is so it doesn't show the actual text
               do{
                   q = imageIDFeme[textRandom.nextInt(imageIDFeme.length)];
               } while (actualPosition == q);

               actualPosition = q;

                //ivReflections
                tvReflection = (TextView) findViewById(R.id.tvReflection);
                tvReflection.setText(q);
                break;
            case "Male":
                do{
                    q = imageIDMale[textRandom.nextInt(imageIDFeme.length)];
                }while (actualPosition == q);

                actualPosition = q;

                //tvReflections
                tvReflection = (TextView) findViewById(R.id.tvReflection);
                tvReflection.setText(q);
            default:
                break;
        }
    }

    //this is used for change text
    public void txRandom(View view){
        validateGender();
    }
    //this is used to go to home
    public void back(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
    //this is used for exit of the app
    public void exit(View view){
        AlertDialog.Builder builderExit = new AlertDialog.Builder(activity_textReflection.this);
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

    //this is to share text
    public void shareText(View view){
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/html");
        share.putExtra(Intent.EXTRA_TEXT, tvReflection.getText());
        startActivity(Intent.createChooser(share, "Share"));

    }
}