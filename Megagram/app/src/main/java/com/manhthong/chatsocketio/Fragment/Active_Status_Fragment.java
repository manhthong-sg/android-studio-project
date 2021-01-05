package com.manhthong.chatsocketio.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.manhthong.chatsocketio.MainActivity;
import com.manhthong.chatsocketio.Message_Activity;
import com.manhthong.chatsocketio.Adapter.OnlineUserAdapter;
import com.manhthong.chatsocketio.Model.Online_User;
import com.manhthong.chatsocketio.Model.Room;
import com.manhthong.chatsocketio.Model.User;
import com.manhthong.chatsocketio.R;
import com.manhthong.chatsocketio.api.ApiService;
import com.manhthong.chatsocketio.editproflie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Active_Status_Fragment extends Fragment {
    ListView lv_online_user;
//    List<Online_User> lst_online_user;
    OnlineUserAdapter onlineUserAdapter;
    TextView tv_onlineUser;
    ArrayList<User> usersList=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_active_status,container,false);

        //tham chieu

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        lv_online_user=view.findViewById(R.id.lv_online_user);
//        tv_onlineUser=view.findViewById(R.id.tv_onlineUser);
//
//        tv_onlineUser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loadUser();
//                onlineUserAdapter.notifyDataSetChanged();
//            }
//        });
        loadUser();
//        //add du lieu vao list view online user
//        lst_online_user=new ArrayList<>();
//        lst_online_user.add(new Online_User("Minh Hạnh", R.drawable.avatar_minh_hanh));
//        lst_online_user.add(new Online_User("Như Ý", R.drawable.avatar_nhu_y));
//        lst_online_user.add(new Online_User("Thanh Trường", R.drawable.avatar_thanhtruong));
//        lst_online_user.add(new Online_User("Hữu Lộc", R.drawable.avatar_huuloc));
//        lst_online_user.add(new Online_User("Mạnh Thông", R.drawable.avatar_thong));
//        lst_online_user.add(new Online_User("Nhật Vy", R.drawable.avatar_nhat_vy));
//        lst_online_user.add(new Online_User("Vợ yêu 002", R.drawable.avatar_hanh_le));
        //loadUser();
        //do data vao listview


    }
    private void loadUser() {
        //ArrayList<User> lstUser;
        ApiService.apiService.getAllUser().enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                ArrayList<User> list_user_result=response.body();
                for(User user : list_user_result){
                    usersList.add(user);
                    //userSearchAdapter.notifyDataSetChanged();
                }
                setAdapter(usersList);
//                Log.d("1",usersList.get(1).toString());
//                usersList.clear();
//                usersList.addAll(usersList);
//                userSearchAdapter.notifyDataSetChanged();
                Log.d("1",usersList.get(1).getDisplayName());
//                Toast.makeText(getContext(), usersList.get(1).getDisplayName().toString(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Toast.makeText(getActivity(), "Fail", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void setAdapter(ArrayList<User>usersList){
        onlineUserAdapter= new OnlineUserAdapter(getActivity(), R.layout.list_active_user, usersList);
        lv_online_user.setAdapter(onlineUserAdapter);

        //set su kien onClick cho tung item online user
        lv_online_user.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                createRoom(MainActivity.phoneNumber, usersList.get(position).getPhoneNumber(),usersList.get(position));
            }
        });
    }
    private void createRoom(String user1, String user2, User user) {
        Room room=new Room(user1, user2);
        ApiService.apiService.createRoom(room).enqueue(new Callback<Room>() {
            @Override
            public void onResponse(Call<Room> call, Response<Room> response) {
//                User user = response.body();
                Intent intent=new Intent(getContext(),Message_Activity.class);
                intent.putExtra("user",(Serializable) user);
                intent.putExtra("room",response.body().get_id());
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Room> call, Throwable t) {
               // Toast.makeText(getContext(), "Fail to update information", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
