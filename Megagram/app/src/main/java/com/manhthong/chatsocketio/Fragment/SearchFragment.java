package com.manhthong.chatsocketio.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.manhthong.chatsocketio.Adapter.OnlineUserAdapter;
import com.manhthong.chatsocketio.Adapter.RecyclerViewAdapter;
import com.manhthong.chatsocketio.Adapter.SearchUserAdapter;
import com.manhthong.chatsocketio.Adapter.UserSearchAdapter;
import com.manhthong.chatsocketio.MainActivity;
import com.manhthong.chatsocketio.Message_Activity;
import com.manhthong.chatsocketio.Model.Image_Message;
import com.manhthong.chatsocketio.Model.Room;
import com.manhthong.chatsocketio.Model.User;
import com.manhthong.chatsocketio.R;
import com.manhthong.chatsocketio.api.ApiService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment implements UserSearchAdapter.UserSearchInterface {
    TextView btn_cancel;
    RecyclerView recyclerView_search;
    ArrayList<User> usersList;
    UserSearchAdapter userSearchAdapter;

    RecyclerView listViewSearchResult;
    SearchView searchView;
    Button btnShowMore;

    //SwipeRefreshLayout srlProduct;
    public SearchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private ArrayList<Image_Message> mImageUrls = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);



        searchView =  view.findViewById(R.id.actvSearch);
        listViewSearchResult=view.findViewById(R.id.rcv_searchResult);
        searchView.requestFocusFromTouch();
        usersList=new ArrayList<>();
        loadUser();
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        //RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        listViewSearchResult.setLayoutManager(layoutManager2);
        listViewSearchResult.setItemAnimator(new DefaultItemAnimator());
        userSearchAdapter = new UserSearchAdapter(usersList,MainActivity.phoneNumber,SearchFragment.this);
        listViewSearchResult.setAdapter(userSearchAdapter);

//        gridLayoutManager = new GridLayoutManager(SearchActivity.this, 2);
//        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
//        myAdapter = new RecyclerDataAdapter(list,userID,SearchActivity.this) ;
//        myrv.setLayoutManager(gridLayoutManager);
//        myrv.setAdapter(myAdapter);
        setSearchView(userSearchAdapter);



        btn_cancel=view.findViewById(R.id.btn_cancel);

        recyclerView_search=view.findViewById(R.id.ryceclerView_search);


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).switchFragment(new Message_Fragment());//close current fragment
                //getContext();
            }
        });


        //add du lieu user info search
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
        //RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView_search.setLayoutManager(layoutManager);
        recyclerView_search.setItemAnimator(new DefaultItemAnimator());
        RecyclerViewAdapter recyclerViewAdapteradapter = new RecyclerViewAdapter(getActivity(), mImageUrls);
        recyclerView_search.setAdapter(recyclerViewAdapteradapter);
        return view;
    }

    public void setSearchView(final UserSearchAdapter myAdapter){
        searchView.requestFocusFromTouch();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                searchFurniture(newText);
                return false;
            }
        });
    }
    private void searchFurniture(String newText) {
        ArrayList<User> tmp = new ArrayList<>();
        if (usersList == null) {
            listViewSearchResult.setVisibility(View.GONE);
            return;
        }
        for(User product : usersList){
            if(product.getDisplayName().toLowerCase().contains(newText.toLowerCase()) || product.getPhoneNumber().toLowerCase().contains(newText.toLowerCase())){
                tmp.add(product);
            }

//            if(product.ge().toLowerCase().equals(newText.toLowerCase())){
//                tmp.add(product);
//            }

        }
        if(tmp.size() > 0){
            userSearchAdapter.filterList(tmp);
            listViewSearchResult.setVisibility(View.VISIBLE);
        }
        if(newText.isEmpty()){
            listViewSearchResult.setVisibility(View.GONE);
        }
    }
    public void createRoom(String user1, String user2, User user) {
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

            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Toast.makeText(getActivity(), "Fail", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        //hide menu navigation when click to edittext search
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void showDetail(User user) {
        //((MainActivity) getActivity()).(product);
    }
}
