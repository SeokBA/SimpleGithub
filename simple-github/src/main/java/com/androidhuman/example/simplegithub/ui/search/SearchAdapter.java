package com.androidhuman.example.simplegithub.ui.search;

import com.androidhuman.example.simplegithub.R;
import com.androidhuman.example.simplegithub.api.model.GithubRepo;
import com.androidhuman.example.simplegithub.ui.GlideApp;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.RepositoryHolder> {

    private List<GithubRepo> items = new ArrayList<>();

    private ColorDrawable placeholder = new ColorDrawable(Color.GRAY);

    @Nullable
    private ItemClickListener listener;

    @Override
    public RepositoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RepositoryHolder(parent);
    }

    @Override
    public void onBindViewHolder(RepositoryHolder holder, int position) {
        final GithubRepo repo = items.get(position);

        // 저장소 소유자의 프로필 사진을 표시합니다.
        GlideApp.with(holder.itemView.getContext())
                .load(repo.owner.avatarUrl)
                .placeholder(placeholder)
                .into(holder.ivProfile);

        // 저장소 정보를 표시합니다.
        holder.tvName.setText(repo.fullName);
        holder.tvLanguage.setText(TextUtils.isEmpty(repo.language)
                ? holder.itemView.getContext().getText(R.string.no_language_specified)
                : repo.language);

        // 리스트 항목 클릭 리스너를 지정합니다.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != listener) {
                    listener.onItemClick(repo);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(@NonNull List<GithubRepo> items) {
        this.items = items;
    }

    public void setItemClickListener(@Nullable ItemClickListener listener) {
        this.listener = listener;
    }

    public void clearItems() {
        this.items.clear();
    }

    // 리스트에 표시될 항목의 뷰를 담당하는 뷰홀더를 정의합니다.
    static class RepositoryHolder extends RecyclerView.ViewHolder {

        ImageView ivProfile;

        TextView tvName;

        TextView tvLanguage;

        RepositoryHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_repository, parent, false));

            ivProfile = itemView.findViewById(R.id.ivItemRepositoryProfile);
            tvName = itemView.findViewById(R.id.tvItemRepositoryName);
            tvLanguage = itemView.findViewById(R.id.tvItemRepositoryLanguage);
        }
    }

    // 항목 클릭 리스너 인터페이스를 정의합니다.
    public interface ItemClickListener {

        void onItemClick(GithubRepo repository);
    }
}