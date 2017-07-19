package com.example.vitali.githubapiclient.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vitali.githubapiclient.R;
import com.example.vitali.githubapiclient.data.model.Repos;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private final Context context;
    private OnItemClickCallback callback;
    private List<Repos> reposes = new ArrayList<>();

    public UserAdapter(Context context, List<Repos> reposes) {
        this.context = context;
        this.reposes = reposes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Repos repos = reposes.get(position);
        holder.tvName.setText(repos.getName());
        holder.tvUrl.setText(repos.getUrl());
        holder.tvDescription.setText(repos.getDescription());
        if (repos.getOwner().getAvatarUrl() != null) {
            Picasso.with(context)
                    .load(repos.getOwner().getAvatarUrl())
                    .into(holder.ivAvatar);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onClick(reposes.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return reposes.size();
    }

    public void setCallback(OnItemClickCallback callback) {
        this.callback = callback;
    }

    public interface OnItemClickCallback {
        void onClick(Repos user);
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
        }
    }
}