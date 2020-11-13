package com.manhthong.chatsocketio.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.manhthong.chatsocketio.Image_Message;
import com.manhthong.chatsocketio.R;

import java.util.ArrayList;
import java.util.List;

public class ImagesAdapter  extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {

    public static  final int MSG_TYPE_LEFT = 0;
    public static  final int MSG_TYPE_RIGHT = 1;
    Typeface MR,MRR;


    private Context mContext;
    private List<Image_Message> lst_Image;
    private int img_online_user;


    public ImagesAdapter(Context mContext, List<Image_Message> lst_Image){
        this.lst_Image = lst_Image;
        this.mContext = mContext;

        MRR = Typeface.createFromAsset(mContext.getAssets(), "fonts/myriadregular.ttf");
        MR = Typeface.createFromAsset(mContext.getAssets(), "fonts/myriad.ttf");

    }

    @NonNull
    @Override
    public ImagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.list_active_img_user, parent, false);
            return new ImagesAdapter.ViewHolder(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.list_active_img_user, parent, false);
            return new ImagesAdapter.ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesAdapter.ViewHolder holder, int position) {

        Image_Message image_message = lst_Image.get(position);
        holder.profile_image.setBackgroundResource(image_message.getImgUser());

    }

    @Override
    public int getItemCount() {
        return lst_Image.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        public TextView show_message;
        public ImageView profile_image;
        public TextView txt_seen;
        public TextView time_tv;
        public ViewHolder(View itemView) {
            super(itemView);
            profile_image = itemView.findViewById(R.id.imgUser);

        }

//        public String convertTime(String time){
//            SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
//            String dateString = formatter.format(new Date(Long.parseLong(time)));
//            return dateString;
//        }
    }

//    @Override
//    public int getItemViewType(int position) {
//        fuser = FirebaseAuth.getInstance().getCurrentUser();
//        if (mChat.get(position).getSender().equals(fuser.getUid())){
//            return MSG_TYPE_RIGHT;
//        } else {
//            return MSG_TYPE_LEFT;
//        }
//    }
}