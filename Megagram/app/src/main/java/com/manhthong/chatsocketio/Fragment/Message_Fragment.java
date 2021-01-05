package com.manhthong.chatsocketio.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.manhthong.chatsocketio.Adapter.OnlineUserAdapter;
import com.manhthong.chatsocketio.Adapter.RecyclerViewAdapter;
import com.manhthong.chatsocketio.Adapter.UserMessageAdapter;
import com.manhthong.chatsocketio.MainActivity;
import com.manhthong.chatsocketio.Message_Activity;
import com.manhthong.chatsocketio.Model.Image_Message;
import com.manhthong.chatsocketio.Model.MessageFormat;
import com.manhthong.chatsocketio.Model.Room;
import com.manhthong.chatsocketio.Model.User;
import com.manhthong.chatsocketio.R;
import com.manhthong.chatsocketio.Model.User_Message;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.manhthong.chatsocketio.api.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Message_Fragment extends Fragment  {
    //khai bao danh sach nguoi dung nhan tin
    ListView lv_user_massage;
    ArrayList<User> lst_user_massage;
    UserMessageAdapter adapter;
    EditText edt_search;
    TextView tv_hello;
    String id;
    ArrayList<String> phoneConnected=new ArrayList<>();
    //request socketIO
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://172.168.10.233:3000");
        } catch (URISyntaxException e) {}
    }
    //test recyclerview
    private static final String TAG = "Message_Fragment";

    //vars

    private ArrayList<Image_Message> mImageUrls = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_message, container, false);
        getInfoMainActivity();
        //id=get;
        //mSocket.connect();
        //anh xa
        lv_user_massage=view.findViewById(R.id.lv_userMessage);
        edt_search=view.findViewById(R.id.edt_search);
        tv_hello=view.findViewById(R.id.tvMessage);

        //Show ten nguoi dang nhap
        tv_hello.setText("Hello "+ MainActivity.displayName);

        //them du lieu vao list nguoi nhan tin

        lst_user_massage= new ArrayList<>();

        loadUser();

//        lst_user_massage.add(new User_Message("Minh Hạnh", "nhớ a quá à >< . .", "16:04", R.drawable.avatar_minh_hanh));
//        lst_user_massage.add(new User_Message("Như Ý", "em yêu anh. .", "15:44", R.drawable.avatar_nhu_y));
//        lst_user_massage.add(new User_Message("Hữu Lộc", "thằng nhóc này. .", "15:44", R.drawable.avatar_huuloc));
//        lst_user_massage.add(new User_Message("Dự phòng 2", "cuối tuần này a rảnh k :3 . .", "14:50", R.drawable.avatar_nhat_vy));
//        lst_user_massage.add(new User_Message("Dự phòng 1", "hôm nay a bận r . .", "00:00", R.drawable.avatar_uyen));
//        lst_user_massage.add(new User_Message("Thanh Trường", "ê tao bảo . .", "16:04", R.drawable.avatar_thanhtruong));
//        lst_user_massage.add(new User_Message("Hạnh Lê", "Làm ny tớ nhé . .", "12:35", R.drawable.avatar_hanh_le));
//        lst_user_massage.add(new User_Message("Mạnh Thông", "ccc . .", "12:35", R.drawable.avatar_thong));

//        //them du lieu nao recyclerview nguoi online
        mImageUrls.add(new Image_Message( R.drawable.avatar_huuloc, "Hữu Lộc"));
        mImageUrls.add(new Image_Message(R.drawable.avatar_hanh_le, "Hạnh Lê"));
        mImageUrls.add(new Image_Message(R.drawable.avatar_thanhtruong, "Thanh Trường"));
        mImageUrls.add(new Image_Message(R.drawable.avatar_minh_hanh, "Minh Hạnh"));
        mImageUrls.add(new Image_Message(R.drawable.avatar_thong, "Mạnh Thông"));
        mImageUrls.add(new Image_Message( R.drawable.avatar_huuloc, "Hữu Lộc"));
        mImageUrls.add(new Image_Message(R.drawable.avatar_uyen, "Tuyết Uyển"));
        mImageUrls.add(new Image_Message(R.drawable.avatar_hanh_le, "Hạnh Lê"));
        mImageUrls.add(new Image_Message(R.drawable.avatar_nhu_y, "Như Ý"));
        mImageUrls.add(new Image_Message(R.drawable.avatar_minh_hanh, "Minh Hạnh"));
        mImageUrls.add(new Image_Message(R.drawable.avatar_thong, "Mạnh Thông"));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerViewAdapter recyclerViewAdapteradapter = new RecyclerViewAdapter(getActivity(), mImageUrls);
        recyclerView.setAdapter(recyclerViewAdapteradapter);


        //set onCLick de mo tin nhan
        lv_user_massage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentMessage= new Intent(getActivity(), Message_Activity.class);
                startActivity(intentMessage);
            }
        });
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //getInfoMainActivity();
        edt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).switchFragment(new SearchFragment());
                //edt_search.setFocusable(true);
            }
        });
    }
    private void getUserRoomConnected(String phone) {
        //phone=MainActivity.phoneNumber;
        ApiService.apiService.getUserRoomConnected(phone).enqueue(new Callback<ArrayList<Room>>() {
            @Override
            public void onResponse(Call<ArrayList<Room>> call, Response<ArrayList<Room>> response) {
                ArrayList<Room> list_room_result=response.body();
                //phoneConnected= new ArrayList(){};
                for(Room room : list_room_result){
                    if(MainActivity.phoneNumber.equals(room.getUser1())){
                        phoneConnected.add(room.getUser2());

                    }
                    else if(MainActivity.phoneNumber.equals(room.getUser2())){
                        phoneConnected.add(room.getUser1());
                    }
                    //userSearchAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Room>> call, Throwable t) {
                // Toast.makeText(getContext(), "Fail to update information", Toast.LENGTH_SHORT).show();
            }
        });
    }
private void loadUser() {
    getUserRoomConnected(MainActivity.phoneNumber);
    ApiService.apiService.getAllUser().enqueue(new Callback<ArrayList<User>>() {
        @Override
        public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {

            ArrayList<User> list_user_result=response.body();
            for(User user : list_user_result){
                //getUserRoomConnected(MainActivity.phoneNumber);
                for(String phone : phoneConnected){
                    if(user.getPhoneNumber().equals(phone)){
                        lst_user_massage.add(user);
                    }
                }
                setAdapter(lst_user_massage);

//                if(user.getPhoneNumber())
//                lst_user_massage.add(user);
            }


        }
        @Override
        public void onFailure(Call<ArrayList<User>> call, Throwable t) {
            Toast.makeText(getActivity(), "Fail", Toast.LENGTH_SHORT).show();
        }
    });

}
    public void setAdapter(ArrayList<User>usersList){

        adapter= new UserMessageAdapter(getActivity(), R.layout.list_message_user, lst_user_massage);
        lv_user_massage.setAdapter(adapter);
        //set su kien onClick cho tung item online user
        lv_user_massage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                createRoom(MainActivity.phoneNumber, usersList.get(position).getPhoneNumber(),usersList.get(position));
            }
        });
//        lv_online_user.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                createRoom(MainActivity.phoneNumber, usersList.get(position).getPhoneNumber(),usersList.get(position));
//            }
//        });
    }
    private void getInfoMainActivity() {
        Call<List<User>> call= ApiService.apiService.getInfoMainActivity(MainActivity.uniqueId);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> lst_userResult = response.body();
                User user= lst_userResult.get(0);
                MainActivity.displayName=user.getDisplayName();
                MainActivity.gender=user.getGender();
                MainActivity.birthday=user.getBirthday();
                MainActivity.address=user.getAddress();
                MainActivity.mail=user.getEmail();
                MainActivity.password=user.getPassword();
                MainActivity.phoneNumber=user.getPhoneNumber();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getActivity(), "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
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
