package com.manhthong.chatsocketio;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.manhthong.chatsocketio.Model.User;
import com.manhthong.chatsocketio.api.ApiService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class editproflie extends AppCompatActivity {
    RadioGroup edt_gender;
    RadioButton radioButton;
    //TextView textView;
    EditText edt_birthday, edt_name, edt_phoneNumber, edt_mail, edt_address;
    ImageButton btnBack;
    Button btn_Save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editproflie);

        // tham chieu
//        radioGroup = findViewById(R.id.radioGroup);
//        textView = findViewById(R.id.text_view_selected);
        btnBack=findViewById(R.id.btnBack);
        btn_Save=findViewById(R.id.btn_save);
        edt_name=findViewById(R.id.edt_Name);
        edt_gender=findViewById(R.id.groupGender);
        edt_phoneNumber=findViewById(R.id.edt_phoneNumber);
        edt_mail=findViewById(R.id.edt_mail);
        edt_address=findViewById(R.id.edt_address);
        edt_birthday=findViewById(R.id.edt_birthday);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //do du lieu ben my profile sang edit profile
        edt_name.setText(MainActivity.displayName);
        edt_birthday.setHint(MainActivity.birthday);
        //Toast.makeText(this, edt_birthday.getText(), Toast.LENGTH_SHORT).show();
        edt_phoneNumber.setText(MainActivity.phoneNumber);
        edt_mail.setText(MainActivity.mail);
        edt_address.setText(MainActivity.address);
//
//        //chonngay
        edt_birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonNgay();
            }
        });
        //chon gioi tinh
        //Button buttonApply = findViewById(R.id.button_apply);
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMyProfile();
//                Intent intentBack = new Intent(editproflie.this, MainActivity.class);
//                startActivity(intentBack);
            }
        });
    }

    private void updateMyProfile() {
        User user = new User(edt_name.getText().toString(), edt_mail.getText().toString(), edt_phoneNumber.getText().toString(), radioButton.getText().toString(), edt_birthday.getText().toString(), edt_address.getText().toString());
        ApiService.apiService.updateMyProfile(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                Toast.makeText(editproflie.this, "Update successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(editproflie.this, "Fail to update information", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void checkButton(View v) {
        int radioId = edt_gender.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this, "Selected: " + radioButton.getText(),
                Toast.LENGTH_SHORT).show();
    }
    private void ChonNgay(){
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edt_birthday.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }
}
