package com.example.tarea4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class genderActivity extends AppCompatActivity {

    private EditText etName;
    private RadioGroup radioGroup;
    private RadioButton rbMale, rbFeminine;
    private String text;
    private Button btnEnterNG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);

        etName = (EditText)findViewById(R.id.etName);

        //radioGroup
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        //radioButtons
        rbMale = (RadioButton) findViewById(R.id.rbMale);
        rbFeminine = (RadioButton) findViewById(R.id.rbFeminine);

        //btnEnterNG
        btnEnterNG = (Button) findViewById(R.id.btbEnterNG);
        btnEnterNG.setEnabled(false);

        //this is for when the name is written the button is enabled
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.equals("")){
                    btnEnterNG.setEnabled(false);
                }
                else{
                    btnEnterNG.setEnabled(true);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().equals("")){
                    btnEnterNG.setEnabled(false);
                }
                else{
                    btnEnterNG.setEnabled(true);
                }
            }
        });

    }

    //method for select a radio button of radioGroup
    private void selectRadioButton(){
        for(int i = 0 ;i < radioGroup.getChildCount();i++) {
            RadioButton rb = (RadioButton) radioGroup.getChildAt(i);
            if (rb.isChecked()) {
                text = rb.getText().toString();
                break;
            }
        }
    }

    //method to go to next activity
    public void imageReflection(View view) {
        Intent i = new Intent(this, ImageActivity.class);
        i.putExtra("Name", etName.getText().toString());

        //call to method selectRadioButton()
        selectRadioButton();

        i.putExtra("Gender", text);

        startActivity(i);
    }

    //method to go to main activity
    public void back(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    //method for exit of the app
    public void exit(View view){
        AlertDialog.Builder builderExit = new AlertDialog.Builder(genderActivity.this);
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