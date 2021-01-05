package com.manhthong.chatsocketio.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.manhthong.chatsocketio.Model.User;
import com.manhthong.chatsocketio.R;

import java.util.ArrayList;
import java.util.List;

public class SearchUserAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<User> lst__online_user;

    public SearchUserAdapter(Context context, int layout, ArrayList<User> lst_online_user) {
        this.context = context;
        this.layout = layout;
        this.lst__online_user = lst_online_user;
    }

    @Override
    public int getCount() {
        return lst__online_user.size();
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
        ImageView imgUser=convertView.findViewById(R.id.imgUser);
        //Gan gia tri
        User online_user= lst__online_user.get(position);
        tv_userName.setText(online_user.getDisplayName());
//        imgUser.setImageResource(online_user.get());
        return convertView;
    }

    public void clear() {
        lst__online_user.clear();
        notifyDataSetChanged();
    }

    public void addAll(ArrayList<User> tmp) {
            lst__online_user.addAll(tmp);
            notifyDataSetChanged();
    }

//    public void clear() {
//        i
//    }
}
