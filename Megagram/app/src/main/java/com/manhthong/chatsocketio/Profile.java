package com.manhthong.chatsocketio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.manhthong.chatsocketio.Model.User;


public class Profile extends AppCompatActivity {
    TextView edt_date, edt_name, edt_gender, edt_email, edt_phoneNumber,edt_address;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent=getIntent();
        User user= (User) intent.getSerializableExtra("user");

        btnBack=findViewById(R.id.btnBack);
        edt_name=findViewById(R.id.tv_name);
        edt_gender=findViewById(R.id.tv_Gender);
        edt_email=findViewById(R.id.tv_Mail);
        edt_phoneNumber=findViewById(R.id.tv_PhoneNumber);
        edt_address=findViewById(R.id.tv_Address);
        edt_date= findViewById(R.id.tv_Birthday);



        edt_name.setText(user.getDisplayName());
        edt_gender.setText(user.getGender());
        edt_email.setText(user.getEmail());
        edt_phoneNumber.setText(user.getPhoneNumber());
        edt_address.setText(user.getAddress());
        edt_date.setText(user.getBirthday());
        //set onClick cho btn back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}