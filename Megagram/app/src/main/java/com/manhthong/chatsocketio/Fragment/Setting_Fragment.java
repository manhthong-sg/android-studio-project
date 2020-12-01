package com.manhthong.chatsocketio.Fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.manhthong.chatsocketio.LoginActivity;
import com.manhthong.chatsocketio.MyProfile;
import com.manhthong.chatsocketio.R;
import com.manhthong.chatsocketio.UpdatePassword;
import com.manhthong.chatsocketio.aboutus;
import com.manhthong.chatsocketio.contactus;
import com.manhthong.chatsocketio.service;

public class Setting_Fragment extends Fragment {
    TextView tv_changesPass;
    Button btnLogout;
    TextView tv_myProfile, tv_aboutUs, tv_service, tv_contactUs;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_setting,container,false);
        //tham chieu
        tv_changesPass=view.findViewById(R.id.tv_changesPass);
        btnLogout=view.findViewById(R.id.btnLogOut);
        tv_myProfile=view.findViewById(R.id.tv_editProfile);
        tv_aboutUs=view.findViewById(R.id.tv_aboutUs);
        tv_service=view.findViewById(R.id.tv_service);
        tv_contactUs=view.findViewById(R.id.tv_contactUs);
        //set su kien khi click vao dong "Changes Password"
        tv_changesPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), UpdatePassword.class);
                startActivity(intent);
            }
        });
        //set onCLick EditProfile
        tv_myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MyProfile.class);
                startActivity(intent);
            }
        });
        //set onCLick about us
        tv_aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), aboutus.class);
                startActivity(intent);
            }
        });
        //set onClick btnLogout
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(), LoginActivity.class);
                getActivity().finish();
                startActivity(intent);
            }
        });
        //onClick cho service
        tv_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), service.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.tv_website).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked("https://www.facebook.com/loc.huu.54966834/");
            }
        });
        view.findViewById(R.id.tv_mail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked("https://mail.google.com/mail/u/0/#inbox");
            }
        });
        //contact us
        tv_contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), contactus.class);
                startActivity(intent);
            }
        });


        return view;
    }
    // Chuyen sang dang trang web
    public void clicked(String url)
    {
        Intent intent= new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
