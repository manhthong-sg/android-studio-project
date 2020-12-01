package com.manhthong.chatsocketio.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.manhthong.chatsocketio.Message_Activity;
import com.manhthong.chatsocketio.Adapter.OnlineUserAdapter;
import com.manhthong.chatsocketio.Model.Online_User;
import com.manhthong.chatsocketio.R;

import java.util.ArrayList;
import java.util.List;

public class Active_Status_Fragment extends Fragment {
    ListView lv_online_user;
    List<Online_User> lst_online_user;
    OnlineUserAdapter onlineUserAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_active_status,container,false);

        //tham chieu
        lv_online_user=view.findViewById(R.id.lv_online_user);
        //add du lieu vao list view online user
        lst_online_user=new ArrayList<>();
        lst_online_user.add(new Online_User("Minh Hạnh", R.drawable.avatar_minh_hanh));
        lst_online_user.add(new Online_User("Như Ý", R.drawable.avatar_nhu_y));
        lst_online_user.add(new Online_User("Thanh Trường", R.drawable.avatar_thanhtruong));
        lst_online_user.add(new Online_User("Hữu Lộc", R.drawable.avatar_huuloc));
        lst_online_user.add(new Online_User("Mạnh Thông", R.drawable.avatar_thong));
        lst_online_user.add(new Online_User("Nhật Vy", R.drawable.avatar_nhat_vy));
        lst_online_user.add(new Online_User("Vợ yêu 002", R.drawable.avatar_hanh_le));

        //do data vao listview
        onlineUserAdapter= new OnlineUserAdapter(getActivity(), R.layout.list_active_user, lst_online_user);
        lv_online_user.setAdapter(onlineUserAdapter);

        //set su kien onClick cho tung item online user
        lv_online_user.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= new Intent(getActivity(), Message_Activity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
