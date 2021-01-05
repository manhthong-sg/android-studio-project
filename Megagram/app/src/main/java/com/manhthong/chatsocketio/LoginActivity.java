package com.manhthong.chatsocketio;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.manhthong.chatsocketio.Model.MessageFormat;
import com.manhthong.chatsocketio.Model.User;
import com.manhthong.chatsocketio.api.ApiService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.ref.Reference;
import java.net.URISyntaxException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextView tvSignUp;
    Button btnSignIn;
    //Boolean Login_Status=false;
    EditText edt_phoneNumber, edt_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);


        //tham chieu
        tvSignUp=findViewById(R.id.tvSignUp);
        btnSignIn=findViewById(R.id.btnSignIn);
        edt_phoneNumber=findViewById(R.id.edt_phoneNumber);
        edt_password=findViewById(R.id.edt_password);

        //xu ly du lieu
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignUp= new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intentSignUp);

            }
        });

        //set su kien cho btnSignIn

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    callLoginAPI();
                }catch (Exception exception){
                    Toast.makeText(LoginActivity.this, "This account is not exist", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void callLoginAPI() {
        String ps_user_type=edt_password.getText().toString();
            Call<List<User>> call= ApiService.apiService.getApiLogin(edt_phoneNumber.getText().toString());
                call.enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        List<User> lst_userResult = response.body();
                        if(!lst_userResult.get(0).getPassword().equals("0_found")) {
                            String ps_db = lst_userResult.get(0).getPassword();
                            if (ps_user_type.equals(ps_db)) {
                                Intent intent2Main= new Intent(LoginActivity.this, MainActivity.class);
                                User user = lst_userResult.get(0);
                                intent2Main.putExtra("user_data", user);
                                startActivity(intent2Main);
                            } else {
                                Toast.makeText(LoginActivity.this, "Your password is incorrect", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Check your network please", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}