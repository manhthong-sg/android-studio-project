package com.manhthong.chatsocketio;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import android.widget.TextView;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.manhthong.chatsocketio.Adapter.MessageAdapter;
import com.manhthong.chatsocketio.Model.MessageFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Message_Activity extends AppCompatActivity {
    ImageButton btnBack, btnInfo;
    private EditText edt_send;
    TextView tv_header_username;
    private ImageButton btn_send;
    List<MessageFormat> messageFormatList;
    public static final String TAG  = "Message_Activity";
    ArrayList<MessageFormat>arrayList=new ArrayList<>();
    //public static String uniqueId;
    public String Time;
    private String Username;

    private Boolean hasConnection = false;

    private ListView messageListView;
    private MessageAdapter messageAdapter;

    private Thread thread2;
    private boolean startTyping = false;
    private int time;
    String room="1";
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://172.168.10.233:3000");
        } catch (URISyntaxException e) {
            Log.d("SocketIO", "connection error");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);


//        Log.d("test", uniqueId);
        //tham chieu
        btnBack=findViewById(R.id.btnBack);
        btnInfo=findViewById(R.id.btnInfo);
        tv_header_username=findViewById(R.id.tv_header_username);
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

        messageFormatList = new ArrayList<>();
        messageFormatList.add(new MessageFormat("00001", "Hữu Lộc", "Chào cậu", "00:00"));
        messageFormatList.add(new MessageFormat(MainActivity.uniqueId, "Thông", "Chào cậu :))", "00:01"));
        messageFormatList.add(new MessageFormat("00001", "Hữu Lộc", "Cậu ăn cơm chưa????", "00:02"));
        messageFormatList.add(new MessageFormat("00001", "Hữu Lộc", "Có ăn cơm với canh không????", "00:03"));
        messageFormatList.add(new MessageFormat(MainActivity.uniqueId, "Thông", "Cậu vui tánh quá :))", "00:04"));
        messageFormatList.add(new MessageFormat("00001", "Hữu Lộc", "Cậu quá khen hihi :))", "00:06"));
        messageFormatList.add(new MessageFormat(MainActivity.uniqueId, "Thông", ":) ", "00:09"));
        messageFormatList.add(new MessageFormat("00001", "Hữu Lộc", "Cậu ăn cơm chưa????", "00:12"));
        messageFormatList.add(new MessageFormat("00001", "Hữu Lộc", "Có ăn cơm với canh không????", "00:18"));
        messageFormatList.add(new MessageFormat(MainActivity.uniqueId, "Thông", "Cậu vui tánh quá :))", "00:24"));

//        MessageFormat messageFormatLast=messageFormatList.get(messageFormatList.size()-1);
//        TextView message_time = findViewById(R.id.message_time);
//        message_time.setVisibility(View.VISIBLE);
//        messageFormatLast.getTime();
        messageAdapter = new MessageAdapter(this, R.layout.item_message, messageFormatList);
        messageListView.setAdapter(messageAdapter);

        //set onclick to messageListView

        messageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Integer count=0;
            int pos;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if(pos!=position)
                    {
                        TextView message_time = view.findViewById(R.id.message_time);
                        message_time.setVisibility(View.VISIBLE);
                        //messageAdapter.notifyDataSetChanged();
                        pos=position;
                    }
                    else {
                        TextView message_time = view.findViewById(R.id.message_time);
                        message_time.setVisibility(View.GONE);
                        pos=-1;
                    }
                //messageAdapter.notifyDataSetChanged();
            }
        });
//        messageListView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(hasFocus) {
//                    TextView message_time = v.findViewById(R.id.message_time);
//                    message_time.setVisibility(View.VISIBLE);
//                }
//                else {
//                    TextView message_time = v.findViewById(R.id.message_time);
//                    message_time.setVisibility(View.GONE);
//                }
//            }
//        });
        //connect SocketIO and its request
        mSocket.connect();

        mSocket.on("onMessage",onNewMessage);

        //set su kien onClick cho btnSend
        btn_send.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                JSONObject message_container = new JSONObject();
                try {
                    message_container.put("senderId", MainActivity.uniqueId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    message_container.put("nd", edt_send.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E HH:mm a");
                LocalDateTime now = LocalDateTime.now();
                Time=dtf.format(now);
                try {
                    message_container.put("time", Time );
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //emit tin nhan di toi server
                mSocket.emit("client-gui-tn", message_container);

                edt_send.setText("");
            }

        });

        //onTypeButtonEnable();
    }
    public void onTypeButtonEnable(){
        edt_send.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                JSONObject onTyping = new JSONObject();
                try {
                    onTyping.put("typing", true);
                    onTyping.put("username", Username);
                    //onTyping.put("uniqueId", uniqueId);
                    mSocket.emit("on typing", onTyping);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (charSequence.toString().trim().length() > 0) {
                    btn_send.setEnabled(true);
                } else {
                    btn_send.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
    Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //messageAdapter.clear();
                    JSONObject data = (JSONObject) args[0];
                    String message;
                    String id;
                    String time;
                    try {
                        id=data.getString("id");
                        message=data.getString("noidung");
                        time=data.getString("time");
                        if(MainActivity.uniqueId==id) {
                            messageAdapter.add(new MessageFormat(MainActivity.uniqueId, "Thông", message, time));
                            messageAdapter.notifyDataSetChanged();
                        }
                        else {
                            messageAdapter.add(new MessageFormat(id, "Thông", message, time));
                            messageAdapter.notifyDataSetChanged();
                        }
                    } catch (Exception e) {
                        return;
                    }
                }
            });
        }
    };

    Emitter.Listener userID = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    //String data =  args[0].toString();
                    //String id;
                    String message;
                    try {
                        message = data.getString("noidung");
                        //id = data1.getString("socketID");
                        messageAdapter.add(new MessageFormat("00002","Thông",message, "00:00"));
                        //tv_header_username.setText(id);
                        messageAdapter.notifyDataSetChanged();
                        //messageAdapter.clear();
                    } catch (Exception e) {
                        return;
                    }
                }
            });
        }
    };
}