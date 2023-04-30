package com.example.madmp.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.madmp.R;
import com.example.madmp.ShowDetailActivity;
import com.example.madmp.domain.CatDomain;

import java.util.ArrayList;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.ViewHolder> {
    ArrayList<CatDomain> popularcat;

    public CatAdapter(ArrayList<CatDomain> popularcat){
        this.popularcat = popularcat;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cat, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(popularcat.get(position).getTitle());
        holder.fee.setText(String.valueOf(popularcat.get(position).getFee()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(popularcat.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.pic);

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.itemView.getContext(),ShowDetailActivity.class);
                intent.putExtra("object",popularcat.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularcat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,fee;
        ImageView pic;
        TextView addBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            fee=itemView.findViewById(R.id.fee);
            pic=itemView.findViewById(R.id.pic);
            addBtn=itemView.findViewById(R.id.addBtn);
        }
    }
}
