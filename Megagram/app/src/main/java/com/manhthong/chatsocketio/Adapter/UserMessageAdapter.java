package com.manhthong.chatsocketio.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.manhthong.chatsocketio.R;
import com.manhthong.chatsocketio.Model.User_Message;

import java.util.List;

public class UserMessageAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<User_Message> lst__user_message;

    public UserMessageAdapter(Context context, int layout, List<User_Message> lst__user_message) {
        this.context = context;
        this.layout = layout;
        this.lst__user_message = lst__user_message;
    }

    @Override
    public int getCount() {
        return lst__user_message.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=layoutInflater.inflate(layout, null);
        //Map field
        TextView tv_userName=convertView.findViewById(R.id.tv_userName);
        TextView tv_messDesc= convertView.findViewById(R.id.tv_messageDesc);
        TextView tv_messTime=convertView.findViewById(R.id.tv_messageTime);
        ImageView imgUser=convertView.findViewById(R.id.imgUser);
        //Gan gia tri
        User_Message user_message= lst__user_message.get(position);
        tv_userName.setText(user_message.getUserName());
        tv_messDesc.setText(user_message.getMessage_desc());
        tv_messTime.setText(user_message.getMessage_time());
        imgUser.setImageResource(user_message.getImgUser());
        return convertView;
    }
}
