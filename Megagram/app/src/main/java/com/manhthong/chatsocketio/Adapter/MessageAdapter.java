package com.manhthong.chatsocketio.Adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.manhthong.chatsocketio.Message_Activity;
import com.manhthong.chatsocketio.Model.MessageFormat;
import com.manhthong.chatsocketio.R;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<MessageFormat> {
    public MessageAdapter(Context context, int resource, List<MessageFormat> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(Message_Activity.TAG, "getView:");

        MessageFormat message = getItem(position);
//
//        if(TextUtils.isEmpty(message.getMessage())){
//
//
//            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.user_connected, parent, false);
//
//            TextView messageText = convertView.findViewById(R.id.message_body);
//
//            Log.i(Message_Activity.TAG, "getView: is empty ");
//            String userConnected = message.getUsername();
//            messageText.setText(userConnected);
//
//        }else
            if(message.getUniqueId().equals("00002")){//equals(Message_Activity.uniqueId)
            Log.i(Message_Activity.TAG, "getView: " + message.getUniqueId() + " " + Message_Activity.uniqueId);


            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.my_message, parent, false);
            TextView messageText = convertView.findViewById(R.id.message_body);
            messageText.setText(message.getMessage());

        }else {
            Log.i(Message_Activity.TAG, "getView: is not empty");

            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.their_message, parent, false);

            TextView messageText = convertView.findViewById(R.id.message_body);
//            TextView usernameText = (TextView) convertView.findViewById(R.id.name);

            messageText.setVisibility(View.VISIBLE);
//            usernameText.setVisibility(View.VISIBLE);

            messageText.setText(message.getMessage());
//            usernameText.setText(message.getUsername());
        }

        return convertView;
    }
}
