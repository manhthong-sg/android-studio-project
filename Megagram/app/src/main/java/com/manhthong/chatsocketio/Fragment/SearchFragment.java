package com.manhthong.chatsocketio.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.manhthong.chatsocketio.Adapter.RecyclerViewAdapter;
import com.manhthong.chatsocketio.Model.Image_Message;
import com.manhthong.chatsocketio.R;

import java.util.ArrayList;

public class SearchFragment extends Fragment  {
    TextView btn_cancel;
    RecyclerView recyclerView_search;
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
        final AutoCompleteTextView edt_search =  view.findViewById(R.id.edt_search);
        btn_cancel=view.findViewById(R.id.btn_cancel);
        recyclerView_search=view.findViewById(R.id.ryceclerView_search);
        edt_search.post(new Runnable() {
            @Override
            public void run() {
                edt_search.requestFocus();
                InputMethodManager imgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imgr.showSoftInput(edt_search, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        //set sk cho btn cancel
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed(); //close current fragment
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //loadProduct();

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
}
