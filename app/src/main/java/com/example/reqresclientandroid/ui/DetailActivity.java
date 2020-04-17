package com.example.reqresclientandroid.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.reqresclientandroid.R;
import com.example.reqresclientandroid.data.model.BaseRespone;
import com.example.reqresclientandroid.data.model.UserResponse;
import com.example.reqresclientandroid.data.remote.ApiClient;
import com.example.reqresclientandroid.data.remote.ApiInterface;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    public static final String EXT_ID = "DetailActivity.id";

    private Context context = this;
    private TextView txtName;
    private ApiInterface apiInterface;
    private ProgressDialog progressDialog;
    private int selectedUserId;
    private ImageView images;

    public static void start(Context context, int userId){
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXT_ID, userId);
        context.startActivity(intent);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtName = findViewById(R.id.txt_name);
        images = findViewById(R.id.images);

        selectedUserId = getIntent().getIntExtra(EXT_ID,0);
        getDataUser();
    }

    private void showProgress(boolean show) {
        if (show){
            progressDialog = new ProgressDialog(DetailActivity.this);
            progressDialog.setMessage("Loading..,");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }else {
            progressDialog.dismiss();
        }
    }
    private void getDataUser(){

        if (apiInterface == null) {
            apiInterface = ApiClient.getClient().create(ApiInterface.class);
        }

        showProgress(true);

        Call<BaseRespone> call = apiInterface.getUser(selectedUserId);
        call.enqueue(new Callback<BaseRespone>() {
            @Override
            public void onResponse(Call<BaseRespone> call, Response<BaseRespone> response) {
                showProgress(false);
                BaseRespone baseRespone = response.body();
                UserResponse userResponse = null;
                if (baseRespone != null){
                    userResponse = baseRespone.getData();
                }
                if (userResponse != null){
                    txtName.setText(String.format("%s %s",userResponse.getFirstName(),userResponse.getLastName()));
                }
                if (userResponse != null){
                    Glide.with(getBaseContext())
                            .load(userResponse.getAvatar())
                            .into(images);
                }

            }

            @Override
            public void onFailure(Call<BaseRespone> call, Throwable t) {
                showProgress(false);
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
