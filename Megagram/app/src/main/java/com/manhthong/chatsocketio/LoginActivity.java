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

import java.net.URISyntaxException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextView tvSignUp;
    Button btnSignIn;
    //Boolean Login_Status=false;
    EditText edt_username, edt_password;
//    private Socket mSocket;
//    {
//        try {
//            mSocket = IO.socket("http://172.168.10.75:3000");
//        } catch (URISyntaxException e) {
//            Log.d("SocketIO", "connection error");
//        }
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);


        //connect socket va xu ly
//        mSocket.connect();
//        mSocket.on("login_status", login_status);

        //tham chieu
        tvSignUp=findViewById(R.id.tvSignUp);
        btnSignIn=findViewById(R.id.btnSignIn);
        edt_username=findViewById(R.id.edt_username);
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
//                JSONObject user_login = new JSONObject();
//                try {
//                    user_login.put("username", edt_username.getText());
//                    user_login.put("password", edt_password.getText());
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                mSocket.emit("client-login", user_login);
                callAPI();

            }
        });
    }

    private void callAPI() {
        ApiService.apiService.showUser().enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(LoginActivity.this, "Login Success!!", Toast.LENGTH_SHORT).show();
                User user=response.body();
                if (user != null)
                {
                    JSONObject login_container = new JSONObject();
                    try {
                        login_container.put("senderId", MainActivity.uniqueId);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Login Fail!", Toast.LENGTH_SHORT).show();
            }
        });
    }
//    Emitter.Listener login_status = new Emitter.Listener() {
//        @Override
//        public void call(final Object... args) {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    JSONObject data = (JSONObject) args[0];
//                    String nd;
//                    String id;
//                    try {
//                        nd=data.getString("nd");
//                        id=edt_username.getText().toString();
//                        //Log.d("test", id);
//                        if(nd =="false") {
//                            edt_username.setError("Phone number or password are wrong");
//                            edt_password.setError("Phone number or password are wrong");
//                        }
//                        else{
//                            Intent intentSignIn= new Intent(LoginActivity.this, MainActivity.class);
//                            intentSignIn.putExtra("id", id);
//                            finish();
//                            startActivity(intentSignIn);
//                        }
//                    } catch (Exception e) {
//                        return;
//                    }
//                }
//            });
//        }
//    };
}