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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Random;

public class activity_imageReflection extends AppCompatActivity {
    private TextView tvNameIR, tvGenderIR;
    private ImageView ivReflections;
    private String getName, getGender;
    private Integer q;

    private int actualPosition = -1;
    private static final Random imageRandom = new Random();

    //array women text reflections
    private static final Integer[] imageIDFeme ={
            R.drawable.feme1,  R.drawable.feme2,  R.drawable.feme3,
            R.drawable.feme4,  R.drawable.feme5,
    };

    //array men text reflections
    private static final Integer[] imageIDMale ={
            R.drawable.male1,  R.drawable.male2,  R.drawable.male3,
            R.drawable.male4,  R.drawable.male5,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_reflection);

        tvNameIR = (TextView) findViewById(R.id.tvNameIR);
        getName = getIntent().getStringExtra("Name");
        tvNameIR.setText("Name: "+ getName);

        tvGenderIR = (TextView) findViewById(R.id.tvGenderIR);
        getGender = getIntent().getStringExtra("Gender");
        tvGenderIR.setText("Gender: "+ getGender);

        //validate gender
        validateGender();
    }

    //this is to show different reflections according gender
    private void validateGender(){
        switch (getGender){
            case "Feminine":
                //this is so it doesn't show the actual image
                do {
                    q = imageIDFeme[imageRandom.nextInt(imageIDFeme.length)];
                } while (actualPosition == q);

                actualPosition = q;

                //ivReflections
                ivReflections = (ImageView) findViewById(R.id.ivReflections);
                ivReflections.setImageResource(q);
                break;
            case "Male":
                do {
                    q = imageIDMale[imageRandom.nextInt(imageIDMale.length)];
                } while (actualPosition == q);

                actualPosition = q;

                //ivReflections
                ivReflections = (ImageView) findViewById(R.id.ivReflections);
                ivReflections.setImageResource(q);
                break;
            default:
                break;
        }
    }

    //this is used for change image
    public void imRandom(View view){
        validateGender();
    }
    //this is used for go to home
    public void back(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
    //this is used fot exit of the app
    public void exit(View view){
        AlertDialog.Builder builderExit = new AlertDialog.Builder(activity_imageReflection.this);
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

    //this is to share image
    public void shareImage(View view){
       Bitmap b = BitmapFactory.decodeResource(getResources(), q);
       Intent share = new Intent(Intent.ACTION_SEND);
       share.setType("image/jpeg");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), b, "Title",null );
        Uri imageUri = Uri.parse(path);

        share.putExtra(Intent.EXTRA_STREAM, imageUri);
        startActivity(Intent.createChooser(share, "Select"));
    }
}