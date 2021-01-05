package com.manhthong.chatsocketio.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.manhthong.chatsocketio.Fragment.SearchFragment;
import com.manhthong.chatsocketio.Model.User;
import com.manhthong.chatsocketio.R;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

public class UserSearchAdapter extends RecyclerView.Adapter<UserSearchAdapter.UserViewHolder>{
    ArrayList<User> usersList;
    ArrayList<User> usersListAll;
    //Context context;
    //CustomFilter customFilter;
    UserSearchInterface userSearchInterface;
    SearchFragment context;
    String user1;

    public UserSearchAdapter(ArrayList<User> usersList,String user1, SearchFragment context) {
        this.usersList = usersList;
        this.user1=user1;
        this.context = context;
    }

    public UserSearchAdapter(ArrayList<User> usersList, SearchFragment context) {
        this.usersList = usersList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(context).inflate(R.layout.list_search_user, parent, false);
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_search_user, parent, false);
        UserViewHolder viewHolder = new UserViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = usersList.get(position);
        holder.tv_userName.setText(user.getDisplayName());
        holder.tv_PhoneNumber.setText(String.valueOf(user.getPhoneNumber()));
        holder.tv_Address.setText(String.valueOf(user.getAddress()));
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.createRoom(user1,String.valueOf(user.getPhoneNumber()),user);
            }
        });
    }



    @Override
    public int getItemCount() {
        return usersList.size();
    }
    public void filterList(ArrayList<User> tmp) {
        usersList=tmp;
        notifyDataSetChanged();
    }



    public interface UserSearchInterface {
        void showDetail(User user);
    }


    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_userName;
        TextView tv_PhoneNumber;
        TextView tv_Address;
        LinearLayout layout;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            //ivProduct = itemView.findViewById(R.id.ivProduct);
            layout=itemView.findViewById(R.id.layout);
            tv_userName = itemView.findViewById(R.id.tv_userName);
            tv_PhoneNumber = itemView.findViewById(R.id.tv_PhoneNumber);
            tv_Address = itemView.findViewById(R.id.tv_Address);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            userSearchInterface.showDetail(usersList.get(getAdapterPosition()));
        }

    }
}
