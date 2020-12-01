package com.manhthong.chatsocketio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.manhthong.chatsocketio.Adapter.MessageAdapter;
import com.manhthong.chatsocketio.Model.MessageFormat;

import java.util.ArrayList;
import java.util.List;

public class Message_Activity extends AppCompatActivity {
    ImageButton btnBack, btnInfo;
    private EditText edt_send;
    private ImageButton btn_send;

    public static final String TAG  = "Message_Activity";
    public static String uniqueId;

    private String Username;

    private Boolean hasConnection = false;

    private ListView messageListView;
    private MessageAdapter messageAdapter;

    private Thread thread2;
    private boolean startTyping = false;
    private int time = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        //tham chieu
        btnBack=findViewById(R.id.btnBack);
        btnInfo=findViewById(R.id.btnInfo);
        //set sk btnBack
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //set sk btnInfo
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Message_Activity.this, Profile.class);
                startActivity(intent);
            }
        });
        edt_send = findViewById(R.id.edt_send);
        btn_send = findViewById(R.id.btn_send);
        messageListView = findViewById(R.id.messageListView);

        List<MessageFormat> messageFormatList = new ArrayList<>();
        messageFormatList.add(new MessageFormat("00001", "Hữu Lộc", "Chào cậu"));
        messageFormatList.add(new MessageFormat("00002", "Thông", "Chào cậu :))"));
        messageFormatList.add(new MessageFormat("00001", "Hữu Lộc", "Cậu ăn cơm chưa????"));
        messageFormatList.add(new MessageFormat("00001", "Hữu Lộc", "Có ăn cơm với canh không????"));
        messageFormatList.add(new MessageFormat("00002", "Thông", "Cậu vui tánh quá :))"));
        messageFormatList.add(new MessageFormat("00001", "Hữu Lộc", "Cậu quá khen hihi :))"));
        messageFormatList.add(new MessageFormat("00002", "Thông", ":) "));
        messageFormatList.add(new MessageFormat("00001", "Hữu Lộc", "Cậu ăn cơm chưa????"));
        messageFormatList.add(new MessageFormat("00001", "Hữu Lộc", "Có ăn cơm với canh không????"));
        messageFormatList.add(new MessageFormat("00002", "Thông", "Cậu vui tánh quá :))"));
        messageAdapter = new MessageAdapter(this, R.layout.item_message, messageFormatList);
        messageListView.setAdapter(messageAdapter);
        //set su kien onClick cho btnSend
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageFormatList.add(new MessageFormat("00002","Thông", edt_send.getText().toString()));
                messageAdapter.notifyDataSetChanged();
                edt_send.setText("");

            }
        });
    }
}