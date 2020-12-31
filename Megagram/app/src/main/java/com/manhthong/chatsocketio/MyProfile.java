package com.manhthong.chatsocketio;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyProfile extends AppCompatActivity {
    ImageButton btnBack, btnEditProfile;
    TextView tv_name, tv_gender, tv_birthday, tv_phoneNumber, tv_mail, tv_address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        //tham chieu
        btnBack=findViewById(R.id.btnBack);
        btnEditProfile=findViewById(R.id.btnEditProfile);
        tv_name=findViewById(R.id.tv_name);
        tv_gender=findViewById(R.id.tv_Gender);
        tv_birthday=findViewById(R.id.tv_Birthday);
        tv_phoneNumber=findViewById(R.id.tv_PhoneNumber);
        tv_mail=findViewById(R.id.tv_Mail);
        tv_address=findViewById(R.id.tv_Address);

        //do dữ liệu người dùng vào setting fragment
        tv_name.setText(MainActivity.displayName);
        tv_phoneNumber.setText(MainActivity.phoneNumber);
        tv_mail.setText(MainActivity.mail);
        tv_address.setText(MainActivity.address);
        tv_gender.setText(MainActivity.gender);
        tv_birthday.setText(MainActivity.birthday);

        //set su kien onClick cho btnBack
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyProfile.this, editproflie.class);
                startActivity(intent);
            }
        });
    }

}