package com.manhthong.chatsocketio;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
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
import com.manhthong.chatsocketio.Adapter.OnlineUserAdapter;
import com.manhthong.chatsocketio.Model.MessageFormat;
import com.manhthong.chatsocketio.Model.User;
import com.manhthong.chatsocketio.api.ApiService;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Message_Activity extends AppCompatActivity {
    private static final int REQUEST_CALL = 1;
    ImageButton btnBack, btnInfo,btnCall;
    private EditText edt_send;
    TextView tv_header_username;
    private ImageButton btn_send;
    ArrayList<MessageFormat> messageFormatList=new ArrayList<>();
    public static final String TAG  = "Message_Activity";
    ArrayList<MessageFormat>arrayList=new ArrayList<>();
    public String numberTemp;

    public String Time;
//    private String Username;

    private Boolean hasConnection = false;

    private ListView messageListView;
    private MessageAdapter messageAdapter;

    private Thread thread2;
    private boolean startTyping = false;
    private int time;
    String room;
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://192.168.43.37:3000");
        } catch (URISyntaxException e) {
            Log.d("SocketIO", "connection error");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Intent intent=getIntent();
        User user= (User) intent.getSerializableExtra("user");
        numberTemp=user.getPhoneNumber().toString();
//        Toast.makeText(this, user.getDisplayName(), Toast.LENGTH_SHORT).show();


//        Log.d("test", uniqueId);
        //tham chieu
        btnBack=findViewById(R.id.btnBack);
        btnInfo=findViewById(R.id.btnInfo);
        tv_header_username=findViewById(R.id.tv_header_username);
        btnCall=findViewById(R.id.btnCall);
        tv_header_username.setText(user.getDisplayName());
        //set sk cho btnCall
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callButton();
            }
        });
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
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        edt_send = findViewById(R.id.edt_send);
        btn_send = findViewById(R.id.btn_send);
        messageListView = findViewById(R.id.messageListView);



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

        room=intent.getStringExtra("room");
        getRoomMessage(room);
        mSocket.connect();
//        mSocket.emit("room",room);
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

                try {
                    message_container.put("roomnhan", room );
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //emit tin nhan di toi server
                mSocket.emit("client-gui-tn", message_container);

                edt_send.setText("");
            }

        });
    }
    public void setAdapter(ArrayList<MessageFormat> messageList){
        messageAdapter = new MessageAdapter(this, R.layout.item_message, messageList);
        messageListView.setAdapter(messageAdapter);
    }
    private  void getRoomMessage(String roomId){

        ApiService.apiService.getRoomMessage(roomId).enqueue(new Callback<ArrayList<MessageFormat>>() {
            @Override
            public void onResponse(Call<ArrayList<MessageFormat>> call, Response<ArrayList<MessageFormat>> response) {
                ArrayList<MessageFormat> list_message=response.body();
                for(MessageFormat messageFormat : list_message){
                    messageFormatList.add(messageFormat);
                    //userSearchAdapter.notifyDataSetChanged();
                }
                setAdapter(messageFormatList);
            }

            @Override
            public void onFailure(Call<ArrayList<MessageFormat>> call, Throwable t) {
                //Toast.makeText(SignUpActivity.this, "Call API Error!", Toast.LENGTH_LONG).show();
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
                    String roomNhan;
                    try {

                        id=data.getString("id");
                        message=data.getString("noidung");
                        time=data.getString("time");
                        roomNhan=data.getString("roomnhan");
                        if(roomNhan.equals(room)){
                            if(MainActivity.uniqueId==id) {
                                messageAdapter.add(new MessageFormat(MainActivity.uniqueId, message, time));
                                messageAdapter.notifyDataSetChanged();
                            }
                            else {

                                messageAdapter.add(new MessageFormat(id, message, time));
                                messageAdapter.notifyDataSetChanged();

                            }
                        }

                    } catch (Exception e) {
                        return;
                    }
                }
            });
        }
    };



    private void callButton() {
        String number = numberTemp;
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(Message_Activity.this, Manifest.permission.CALL_PHONE) !=
                    PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Message_Activity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:"+number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }
    }


//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callButton();
            } else {
                Toast.makeText(this, "permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}