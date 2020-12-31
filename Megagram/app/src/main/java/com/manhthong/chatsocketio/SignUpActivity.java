package com.manhthong.chatsocketio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.manhthong.chatsocketio.Model.User;
import com.manhthong.chatsocketio.api.ApiService;

import java.net.URISyntaxException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    TextView tvSignIn;
    EditText edt_displayName, edt_phoneNumber, edt_password, edt_email;
    Button btn_signUp;
    boolean displayName_flag=false, phoneNumber_flag=false, password_flag=false, email_flag=false, phoneNumberCheckDB=false;
//    private Socket mSocket;
//    {
//        try {
//            mSocket = IO.socket("http://192.168.13.111:3000");
//        } catch (URISyntaxException e) {}
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);
        //mSocket.connect();
        tvSignIn = findViewById(R.id.tvSignIn);
        btn_signUp = findViewById(R.id.btn_signUp);
        edt_displayName = findViewById(R.id.edt_displayName);
        edt_phoneNumber = findViewById(R.id.edt_phoneNumber);
        edt_password = findViewById(R.id.edt_password);
        edt_email = findViewById(R.id.edt_email);
        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignIn = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intentSignIn);
            }
        });
        //set dk cho edittext
        edt_displayName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //edt_displayName.setError("* required");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(edt_displayName.getText().toString().isEmpty() || edt_displayName.getText().toString()==null) {
                    edt_displayName.setError("Display name is required");
                }
                displayName_flag=true;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edt_phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(edt_phoneNumber.getText().toString().isEmpty() || edt_phoneNumber.getText().toString()==null) {
                    edt_phoneNumber.setError("Phone number is required");
                }
                phoneNumber_flag=true;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edt_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(edt_password.getText().toString().isEmpty() || edt_password.getText().toString()==null) {
                    edt_password.setError("Password is required");
                }
                password_flag=true;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        edt_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isValidEmail(edt_email.getText().toString()) == false) {
                    edt_email.setError("required @xxx.com");
                }
                else{
                    email_flag=true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkValuedation()==true){
                    sendUser();
                }
                else{
                    Toast.makeText(SignUpActivity.this, "Please fill up all fields!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private  void sendUser(){
        User user = new User(edt_displayName.getText().toString(), edt_email.getText().toString(), edt_password.getText().toString(), edt_phoneNumber.getText().toString());
        ApiService.apiService.insertUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User userResult=response.body();
                if(userResult.getPhoneNumber()!= null && userResult.getPhoneNumber()!=""){
                    finish();
                    Toast.makeText(SignUpActivity.this, "You can login by your account now.", Toast.LENGTH_SHORT).show();
                    }
                else {
                    edt_phoneNumber.setError("Your phone number has been used in other account.");
                    Toast.makeText(SignUpActivity.this, "Số điện thoại này đã có dùng dùng rồi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "Call API Error!", Toast.LENGTH_LONG).show();
            }
        });
    }
    private boolean checkValuedation(){
        if (displayName_flag==true && phoneNumber_flag==true && password_flag==true && email_flag==true ) {
            return true;
        }
        else {
            if (!displayName_flag){
                edt_displayName.setError("Display name is required.");
            }
            if (!phoneNumber_flag){
                edt_phoneNumber.setError("Phone number is required.");
            }
            if (!password_flag){
                edt_password.setError("Password is required.");
            }
            if (!email_flag){
                edt_email.setError("Email is required.");
            }
            return false;
        }
    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}