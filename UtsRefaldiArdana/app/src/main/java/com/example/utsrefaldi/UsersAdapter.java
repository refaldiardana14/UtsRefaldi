package com.example.utsrefaldi;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.utsrefaldi.Models.Items;
import com.example.utsrefaldi.databinding.ListBinding;

public class UsersAdapter extends ListAdapter<Items, UsersAdapter.ViewHolder> {

    public UsersAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListBinding binding = ListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.ViewHolder holder, int position) {
        Items item = getItem(position);
        holder.bind(item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ListBinding binding;

        public ViewHolder(ListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Items items) {
            binding.tvUsername.setText(items.getLogin());
            Glide.with(itemView.getContext())
                    .load(items.getAvatarUrl())
                    .into(binding.imgProfile);

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra("nama", items.getLogin());
                view.getContext().startActivity(intent);
            });
        }
    }

    private static final DiffUtil.ItemCallback<Items> DIFF_CALLBACK = new DiffUtil.ItemCallback<Items>() {
        @Override
        public boolean areItemsTheSame(Items oldItem, Items newItem) {
            return oldItem.getLogin().equals(newItem.getLogin());
        }

        @Override
        public boolean areContentsTheSame(Items oldItem, Items newItem) {
            return oldItem.getLogin().equals(newItem.getLogin()) && oldItem.getAvatarUrl().equals(newItem.getAvatarUrl()) &&
                    (oldItem.getBio() != null ? oldItem.getBio().equals(newItem.getBio()) : newItem.getBio() == null);
        }
    };
}