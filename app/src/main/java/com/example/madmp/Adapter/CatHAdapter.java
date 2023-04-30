package com.example.madmp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.madmp.R;
import com.example.madmp.domain.catHDomain;

import java.util.ArrayList;

public class CatHAdapter extends RecyclerView.Adapter<CatHAdapter.ViewHolder> {
    ArrayList<catHDomain>catHDomains;

    public CatHAdapter(ArrayList<catHDomain> catHDomains){
        this.catHDomains=catHDomains;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cath, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.CatHName.setText(catHDomains.get(position).getTitle());
        String picUrl="";
        switch (position){
            case 0:{
                picUrl="catfood";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cath_bg));
                break;
            }
            case 1:{
                picUrl="catcare";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cath_bg));
                break;
            }
            case 2:{
                picUrl="cates";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cath_bg));
                break;
            }
            case 3:{
                picUrl="catcutep";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cath_bg));
                break;
            }
        }
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.CatHPic);
    }

    @Override
    public int getItemCount() {
        return catHDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView CatHName;
        ImageView CatHPic;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CatHName = itemView.findViewById(R.id.catHname);
            CatHPic = itemView.findViewById(R.id.catHpic);
            mainLayout = itemView.findViewById(R.id.main_layout);
        }
    }
}
