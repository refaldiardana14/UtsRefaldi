package com.example.utsrefaldi;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.utsrefaldi.Api.ApiConfig;
import com.example.utsrefaldi.Models.GithubRes;
import com.example.utsrefaldi.databinding.ActivityMainBinding;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private UsersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        adapter = new UsersAdapter();
        String username = "Refaldi";
        getGithurUsers(username);

    }

    private void getGithurUsers(String username) {

        Call<GithubRes> resCall = ApiConfig.getApiService().getUsers(username);
        resCall.enqueue(new Callback<GithubRes>() {
            @Override
            public void onResponse(@NonNull Call<GithubRes> call, @NonNull Response<GithubRes> response) {
                if (response.isSuccessful()){
                    binding.rvList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    binding.rvList.setAdapter(adapter);
                    adapter.submitList(response.body().getItems());
                }
            }

            @Override
            public void onFailure(@NonNull Call<GithubRes> call, @NonNull Throwable t) {
                Log.e("MainActivity", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}