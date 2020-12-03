package com.manhthong.chatsocketio;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class LoginActivity extends AppCompatActivity {
    TextView tvSignUp;
    Button btnSignIn;
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://192.168.13.112:3000");
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
        tvSignUp=findViewById(R.id.tvSignUp);
        btnSignIn=findViewById(R.id.btnSignIn);
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
                Intent intentSignUp= new Intent(LoginActivity.this, MainActivity.class);
                finish();
                startActivity(intentSignUp);
            }
        });


    }
    public Socket  get_mSocket(){
        return mSocket;
    }
}