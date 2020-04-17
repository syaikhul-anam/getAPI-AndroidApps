package com.example.reqresclientandroid.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.reqresclientandroid.R;
import com.example.reqresclientandroid.data.model.UserResponse;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.ViewHolder> {
    private final Context context;
    private List<UserResponse> userResponses;
    private final OnItemClickListener listener;


    public MainListAdapter(Context context, List<UserResponse> userResponses, OnItemClickListener itemClickListener) {
        this.context = context;
        this.userResponses = userResponses;
        this.listener = itemClickListener;
    }

    public void updateData(List<UserResponse> newData){
        userResponses = newData;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(UserResponse item);
    }

    @NonNull
    @Override
    public MainListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.user_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainListAdapter.ViewHolder holder, int position) {
        final UserResponse userData = userResponses.get(position);

        holder.bind(context, userData, listener);


    }

    @Override
    public int getItemCount() {
        return userResponses.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgUser;
        TextView txtName;
        TextView txtEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUser = itemView.findViewById(R.id.img_user);
            txtName = itemView.findViewById(R.id.txt_name);
            txtEmail = itemView.findViewById(R.id.txt_email);
        }

        public void bind(Context context, UserResponse userData, OnItemClickListener listener) {
            txtName.setText(userData.getFirstName()+ " " +userData.getLastName());
            txtEmail.setText(userData.getEmail());

            Glide.with(context).load(userData.getAvatar()).into(imgUser);

            itemView.setOnClickListener(v -> listener.onItemClick(userData));
        }
    }
}
