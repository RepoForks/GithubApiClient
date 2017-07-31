package com.example.vitali.githubapiclient.ui.profile;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vitali.githubapiclient.R;
import com.example.vitali.githubapiclient.data.network.model.Repository;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder> {

    private List<Repository> repositories;
    private final RepositoryClickListener listener;

    RepositoryAdapter(RepositoryClickListener listener) {
        repositories = new ArrayList<>();
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Repository currentRepository = repositories.get(position);
        holder.tvName.setText(currentRepository.getName());
        holder.tvUrl.setText(currentRepository.getUrl());
        holder.tvDescription.setText(currentRepository.getDescription());
        try {
            Picasso.with(holder.itemView.getContext())
                    .load(currentRepository.getOwner().getAvatarUrl())
                    .into(holder.ivAvatar);
        } catch (NullPointerException e) {
            Log.d("MyTag", "onBindViewHolder: AvatarUrl is null");
        }
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    void setRepositories(List<Repository> repos) {
        this.repositories = repos;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView ivAvatar;
        TextView tvName;
        TextView tvUrl;
        TextView tvDescription;

        ViewHolder(View itemView) {
            super(itemView);
            ivAvatar = (ImageView) itemView.findViewById(R.id.ivUser);
            cardView = (CardView) itemView.findViewById(R.id.card_persons);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvUrl = (TextView) itemView.findViewById(R.id.tvUrl);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);

            itemView.setOnClickListener(v -> listener.onClick(null));
        }
    }

    interface RepositoryClickListener {

        void onClick(Repository repository);
    }
}