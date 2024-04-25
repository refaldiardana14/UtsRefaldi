package com.example.utsrefaldi;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.utsrefaldi.Api.ApiConfig;
import com.example.utsrefaldi.Models.Items;
import com.example.utsrefaldi.databinding.ActivityDetailBinding;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String username = getIntent().getStringExtra("nama");
        getDetailGithub(username);
    }

    private void getDetailGithub(String username) {

        Call<Items> resCall = ApiConfig.getApiService().getDetailUser(username);
        resCall.enqueue(new Callback<Items>() {
            @Override
            public void onResponse(@NonNull Call<Items> call, @NonNull Response<Items> response) {
                if (response.isSuccessful()){
                    Items items = response.body();
                    binding.tvUsername.setText(items.getLogin());
                    binding.tvBio.setText(items.getBio());
                    Glide.with(DetailActivity.this)
                            .load(items.getAvatarUrl())
                            .into(binding.imgProfile);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Items> call, @NonNull Throwable t) {
                Log.e("DetailActivity", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}