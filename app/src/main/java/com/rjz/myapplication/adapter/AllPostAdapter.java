package com.rjz.myapplication.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rjz.myapplication.R;
import com.rjz.myapplication.model.PostModel;

import java.util.List;

public class AllPostAdapter extends RecyclerView.Adapter<AllPostAdapter.NewsViewHolder> {


    private Context context;
    private List<PostModel> apiObjectList;

    public AllPostAdapter(Context context, List<PostModel> apiObjects) {
        this.context = context;
        this.apiObjectList = apiObjects;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        PostModel apiObject = apiObjectList.get(position);
        holder.tvUserId.setText("Post ID : " + apiObject.getId()+" User ID : "+apiObject.getUserId());
        holder.tvTitle.setText(apiObject.getTitle());
        holder.tvBody.setText(apiObject.getBody());
    }

    @Override
    public int getItemCount() {
        return apiObjectList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        public TextView tvUserId, tvTitle, tvBody;

        public NewsViewHolder(View itemView) {
            super(itemView);
            tvUserId = (TextView) itemView.findViewById(R.id.tvUserId);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvBody = (TextView) itemView.findViewById(R.id.tvBody);
        }
    }
}

