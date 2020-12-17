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

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.manhthong.chatsocketio.Model.MessageFormat;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class LoginActivity extends AppCompatActivity {
    TextView tvSignUp;
    Button btnSignIn;
    Boolean Login_Status=false;
    EditText edt_username, edt_password;
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://192.168.13.108:3000");
        } catch (URISyntaxException e) {
            Log.d("SocketIO", "connection error");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

//        mSocket.connect();
//        mSocket.on("server-gui-tn", new Emitter.Listener() {
//            @Override
//            public void call(final Object... args) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        JSONObject data = (JSONObject) args[0];
//                        String message;
//                        try {
//                            message = data.getString("noidung");
//                            btnSignIn.setText(message);
//                        } catch (JSONException e) {
//                            return;
//                        }
//                    }
//                });
//            }
//        });
        //connect socket va xu ly
        mSocket.connect();
        mSocket.on("login_status", login_status);

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
                JSONObject user_login = new JSONObject();
                try {
                    user_login.put("username", edt_username.getText());
                    user_login.put("password", edt_password.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mSocket.emit("client-login", user_login);
//                if(Login_Status==true){
//                    Intent intentSignUp= new Intent(LoginActivity.this, MainActivity.class);
//                    //finish();
//                    startActivity(intentSignUp);
//                }


            }
        });
    }
    Emitter.Listener login_status = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String nd;
                    try {
                        nd=data.getString("nd");
                        if(nd!="true") {
                            edt_username.setText("Wrong info, try again!");
                        }
                        else{
                            Intent intentSignUp= new Intent(LoginActivity.this, MainActivity.class);
                            finish();
                            startActivity(intentSignUp);
                        }
                    } catch (Exception e) {
                        return;
                    }
                }
            });
        }
    };
}