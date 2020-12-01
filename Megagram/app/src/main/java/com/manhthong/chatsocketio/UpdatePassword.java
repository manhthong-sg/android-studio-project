package com.manhthong.chatsocketio;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class UpdatePassword extends AppCompatActivity {
    ImageButton btnBack;
    EditText edt_currentPass, edt_newPass, edt_confirmPass;
    ImageView imgV_eye_newPass,imgV_eye_currentPass,imgV_eye_confirmPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatepassword);
        //tham chieu
        edt_currentPass = findViewById(R.id.current_password);
        edt_newPass = findViewById(R.id.new_password);
        edt_confirmPass = findViewById(R.id.confirm_password);
        imgV_eye_newPass = findViewById(R.id.imgV_eye_newPass);
        imgV_eye_currentPass = findViewById(R.id.imgV_eye_currentPass);
        imgV_eye_confirmPass = findViewById(R.id.imgV_eye_confirmPass);
        btnBack=findViewById(R.id.imgbtnBack);
        //set su kien click
        imgV_eye_newPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_newPass.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    edt_newPass.setInputType(InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    edt_newPass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
            }
        });

        imgV_eye_currentPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_currentPass.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    edt_currentPass.setInputType(InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    edt_currentPass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
            }
        });

        imgV_eye_confirmPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_confirmPass.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    edt_confirmPass.setInputType(InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    edt_confirmPass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
            }
        });
//        set OnCick cho btnBack
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}