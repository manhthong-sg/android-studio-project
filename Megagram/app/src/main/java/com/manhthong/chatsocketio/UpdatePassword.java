package com.manhthong.chatsocketio;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.manhthong.chatsocketio.Model.User;
import com.manhthong.chatsocketio.api.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePassword extends AppCompatActivity {
    ImageButton btnBack;
    Button btn_updatePassword;
    EditText edt_currentPass, edt_newPass, edt_confirmPass;
    ImageView imgV_eye_newPass,imgV_eye_currentPass,imgV_eye_confirmPass;
    boolean currentpass_flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatepassword);
        //tham chieu
        btn_updatePassword=findViewById(R.id.btn_updatePassword);
        edt_currentPass = findViewById(R.id.current_password);
        edt_newPass = findViewById(R.id.new_password);
        edt_confirmPass = findViewById(R.id.confirm_password);
        imgV_eye_newPass = findViewById(R.id.imgV_eye_newPass);
        imgV_eye_currentPass = findViewById(R.id.imgV_eye_currentPass);
        imgV_eye_confirmPass = findViewById(R.id.imgV_eye_confirmPass);
        btnBack=findViewById(R.id.imgbtnBack);

        edt_confirmPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //edt_displayName.setError("* required");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(edt_confirmPass.getText().toString().isEmpty() || edt_confirmPass.getText().toString()==null) {
                    edt_confirmPass.setError("Display name is required");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(edt_currentPass.getText().toString().equals(edt_confirmPass.getText().toString())){
                    currentpass_flag=true;
                }
            }
        });

        //set su kien cho btn_update password
        btn_updatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_currentPass.getText().toString().equals(MainActivity.password)){
                    if(currentpass_flag==true){
                        updateMyPassword();
                    }
                    else {
                        edt_confirmPass.setError("Must similar with new password");
                    }
                }else {
                    edt_currentPass.setError("Current password is incorrect");
                }
            }
        });
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
    private void updateMyPassword() {
        User user = new User(MainActivity.phoneNumber, edt_confirmPass.getText().toString() );
        ApiService.apiService.updateMyPassword(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                Toast.makeText(UpdatePassword.this, "Update successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(UpdatePassword.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}