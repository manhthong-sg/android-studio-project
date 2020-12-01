package com.manhthong.chatsocketio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

public class SignUpActivity extends AppCompatActivity {
    TextView tvSignIn;
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
        tvSignIn=findViewById(R.id.tvSignIn);
        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignIn= new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intentSignIn);
            }
        });

    }
}