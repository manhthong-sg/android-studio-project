package com.manhthong.chatsocketio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Message_Fragment extends Fragment  {
    //khai bao danh sach nguoi dung nhan tin
    ListView lv_user_massage;
    List<User_Message> lst_user_massage;
    UserMessageAdapter adapter;
    //khai bao thanh online
    private ImagesAdapter mAdapter;
    RecyclerView recyclerView;
    ArrayList<Image_Message> lst_images;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_message, container, false);

        //anh xa
        lv_user_massage=view.findViewById(R.id.lv_userMessage);
        recyclerView=view.findViewById(R.id.recyclerView);
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
//        lst_images.add(new Image_Message(R.drawable.avatar_hanh_le));
//        lst_images.add(new Image_Message(R.drawable.avatar_hanh_le));
//        lst_images.add(new Image_Message(R.drawable.avatar_hanh_le));
//        lst_images.add(new Image_Message(R.drawable.avatar_hanh_le));
//        lst_images.add(new Image_Message(R.drawable.avatar_hanh_le));
//        lst_images.add(new Image_Message(R.drawable.avatar_hanh_le));
//        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(horizontalLayoutManagaer);
//
//        mAdapter = new ImagesAdapter(getActivity(), lst_images);
//        recyclerView.setAdapter(mAdapter);
//

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


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
//        recyclerView.setHasFixedSize(true);
//
//        // use a linear layout manager
//        layoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(layoutManager);
//
//        // specify an adapter (see also next example)
//        mAdapter = new MyAdapter(lst_images);
//        recyclerView.setAdapter(mAdapter);
        return view;

    }
}
