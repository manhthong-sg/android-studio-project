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

import java.net.URISyntaxException;

public class SignUpActivity extends AppCompatActivity {
    TextView tvSignIn;
    EditText edt_displayName, edt_phoneNumber, edt_password, edt_email;
    Button btn_signUp;
    boolean displayName_flag=false, phoneNumber_flag=false, password_flag=false, email_flag=false;
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://192.168.13.111:3000");
        } catch (URISyntaxException e) {}
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);
        mSocket.connect();
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

//        if (!edt_displayName.getText().toString().isEmpty() || edt_displayName.getText().toString() != null) {
//            displayName_flag = true;
//        }
//        if (!edt_phoneNumber.getText().toString().isEmpty() || edt_phoneNumber.getText().toString() != null) {
//            phoneNumber_flag = true;
//        }
//        if (!edt_password.getText().toString().isEmpty() || edt_password.getText().toString() != null) {
//            password_flag = true;
//        }
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
                if (displayName_flag==true && phoneNumber_flag==true && password_flag==true && email_flag==true ) {
                    Toast.makeText(SignUpActivity.this, "Sign Up Successfuly!", Toast.LENGTH_LONG).show();
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
                }
            }
        });
    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}