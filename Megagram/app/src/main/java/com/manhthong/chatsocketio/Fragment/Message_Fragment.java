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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.manhthong.chatsocketio.Adapter.RecyclerViewAdapter;
import com.manhthong.chatsocketio.Adapter.UserMessageAdapter;
import com.manhthong.chatsocketio.Message_Activity;
import com.manhthong.chatsocketio.Model.Image_Message;
import com.manhthong.chatsocketio.R;
import com.manhthong.chatsocketio.Model.User_Message;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
public class Message_Fragment extends Fragment  {
    //khai bao danh sach nguoi dung nhan tin
    ListView lv_user_massage;
    List<User_Message> lst_user_massage;
    UserMessageAdapter adapter;
    EditText edt_search;

    //request socketIO
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://172.16.96.144:3000");
        } catch (URISyntaxException e) {}
    }
    //test recyclerview
    private static final String TAG = "Message_Fragment";

    //vars

    private ArrayList<Image_Message> mImageUrls = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_message, container, false);
        mSocket.connect();
        //anh xa
        lv_user_massage=view.findViewById(R.id.lv_userMessage);
        edt_search=view.findViewById(R.id.edt_search);
        //them du lieu vao list nguoi nhan tin

        lst_user_massage= new ArrayList<>();
        lst_user_massage.add(new User_Message("Minh Hạnh", "nhớ a quá à >< . .", "16:04", R.drawable.avatar_minh_hanh));
        lst_user_massage.add(new User_Message("Như Ý", "em yêu anh. .", "15:44", R.drawable.avatar_nhu_y));
        lst_user_massage.add(new User_Message("Hữu Lộc", "thằng nhóc này. .", "15:44", R.drawable.avatar_huuloc));
        lst_user_massage.add(new User_Message("Dự phòng 2", "cuối tuần này a rảnh k :3 . .", "14:50", R.drawable.avatar_nhat_vy));
        lst_user_massage.add(new User_Message("Dự phòng 1", "hôm nay a bận r . .", "00:00", R.drawable.avatar_uyen));
        lst_user_massage.add(new User_Message("Thanh Trường", "ê tao bảo . .", "16:04", R.drawable.avatar_thanhtruong));
        lst_user_massage.add(new User_Message("Hạnh Lê", "Làm ny tớ nhé . .", "12:35", R.drawable.avatar_hanh_le));
        lst_user_massage.add(new User_Message("Mạnh Thông", "ccc . .", "12:35", R.drawable.avatar_thong));

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

        //do du lieu vao listview
        adapter= new UserMessageAdapter(getActivity(), R.layout.list_message_user, lst_user_massage);
        lv_user_massage.setAdapter(adapter);
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

}
