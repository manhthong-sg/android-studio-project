package com.manhthong.chatsocketio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.manhthong.chatsocketio.Fragment.Active_Status_Fragment;
import com.manhthong.chatsocketio.Fragment.Message_Fragment;
import com.manhthong.chatsocketio.Fragment.Setting_Fragment;
import com.manhthong.chatsocketio.Model.User;
import com.manhthong.chatsocketio.api.ApiService;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static String uniqueId;
    public static String displayName;
    public static String mail;
    public static String password;
    public static String phoneNumber;
    public static String address;
    public static String gender;
    public static String birthday;

//    private Socket mSocket;
//    {
//        try {
//            mSocket = IO.socket("http://172.168.10.233:3000");
//        } catch (URISyntaxException e) {}
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //nhận dữ liệu của phiên đăng nhập này
        User user_current=(User) getIntent().getSerializableExtra("user_data");
        uniqueId=user_current.getPhoneNumber();
        displayName=user_current.getDisplayName();
        phoneNumber=user_current.getPhoneNumber();
        password=user_current.getPassword();
        mail=user_current.getEmail();
        birthday=user_current.getBirthday();
        address=user_current.getAddress();
        gender=user_current.getGender();

        //Toast.makeText(this, uniqueId+" "+ displayName+" "+ password+" "+phoneNumber, Toast.LENGTH_SHORT).show();

        bottomNav.getMenu().findItem(R.id.nav_message).setChecked(true);

        if(savedInstanceState == null){
            loadFragment(new Message_Fragment());
        }
        //I added this if statement to keep the selected fragment when rotating the device
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Message_Fragment()).commit();
        }
    }
    public void switchFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_out_up, R.anim.slide_in_up, R.anim.slide_out_up, R.anim.slide_in_up)
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_message:
                            selectedFragment = new Message_Fragment();
                            break;
                        case R.id.nav_activeStatus:
                            selectedFragment = new Active_Status_Fragment();
                            break;
                        case R.id.nav_setting:
                            selectedFragment = new Setting_Fragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };
    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}