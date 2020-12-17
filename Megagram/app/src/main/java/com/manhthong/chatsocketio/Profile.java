package com.manhthong.chatsocketio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class Profile extends AppCompatActivity {
    TextView edt_date;
    ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnBack=findViewById(R.id.btnBack);
        //chonngay
        edt_date= findViewById(R.id.edt_date);
        //set onClick cho btn back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}