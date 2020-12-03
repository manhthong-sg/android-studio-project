package com.manhthong.chatsocketio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import java.util.ArrayList;
import java.util.List;

public class Message_Activity extends AppCompatActivity {
    ImageButton btnBack, btnInfo;
    private EditText edt_send;
    TextView tv_header_username;
    private ImageButton btn_send;
    List<MessageFormat> messageFormatList;
    public static final String TAG  = "Message_Activity";
    ArrayList<MessageFormat>arrayList=new ArrayList<>();
    public static String uniqueId;

    private String Username;

    private Boolean hasConnection = false;

    private ListView messageListView;
    private MessageAdapter messageAdapter;

    private Thread thread2;
    private boolean startTyping = false;
    private int time = 2;
    String room="1";
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://192.168.11.138:3000");
        } catch (URISyntaxException e) {
            Log.d("SocketIO", "connection error");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

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
//        messageFormatList.add(new MessageFormat("00001", "Hữu Lộc", "Chào cậu"));
//        messageFormatList.add(new MessageFormat("00002", "Thông", "Chào cậu :))"));
//        messageFormatList.add(new MessageFormat("00001", "Hữu Lộc", "Cậu ăn cơm chưa????"));
//        messageFormatList.add(new MessageFormat("00001", "Hữu Lộc", "Có ăn cơm với canh không????"));
//        messageFormatList.add(new MessageFormat("00002", "Thông", "Cậu vui tánh quá :))"));
//        messageFormatList.add(new MessageFormat("00001", "Hữu Lộc", "Cậu quá khen hihi :))"));
//        messageFormatList.add(new MessageFormat("00002", "Thông", ":) "));
//        messageFormatList.add(new MessageFormat("00001", "Hữu Lộc", "Cậu ăn cơm chưa????"));
//        messageFormatList.add(new MessageFormat("00001", "Hữu Lộc", "Có ăn cơm với canh không????"));
//        messageFormatList.add(new MessageFormat("00002", "Thông", "Cậu vui tánh quá :))"));
        messageAdapter = new MessageAdapter(this, R.layout.item_message, arrayList);
        messageListView.setAdapter(messageAdapter);
        mSocket.connect();

        mSocket.on("onMessage",onNewMessage);
      //  mSocket.on("user-connect", userID);
       // mSocket.emit("create",room);

        //set su kien onClick cho btnSend
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageAdapter.clear();
                mSocket.emit("client-gui-tn", edt_send.getText());
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
                    onTyping.put("uniqueId", uniqueId);
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
                    messageAdapter.clear();
                    JSONObject data = (JSONObject) args[0];
                    //String data =  args[0].toString();
                    try {
                        JSONArray array=data.getJSONArray("noidung");
                        //Toast.makeText(Message_Activity.this, data.getJSONArray("noidung").toString(), Toast.LENGTH_SHORT).show();
                        for (int i=0;i<array.length();i++){
                            messageAdapter.add(new MessageFormat("00002","thong",array.getString(i)));
                        }

                        //messageAdapter.add(new MessageFormat("00002","Thông",message));
                      //  tv_header_username.setText(id);
                        messageAdapter.notifyDataSetChanged();
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
                    JSONObject data1 = (JSONObject) args[0];
                    //String data =  args[0].toString();
                    String id;
                    //String message;
                    try {
                        //message = data.getString("noidung");
                        id = data1.getString("socketID");
                        //messageAdapter.add(new MessageFormat("00002","Thông",message));
                        tv_header_username.setText(id);
                        //messageAdapter.notifyDataSetChanged();
                        //messageAdapter.clear();
                    } catch (Exception e) {
                        return;
                    }
                }
            });
        }
    };
}