package com.example.madmp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.madmp.Helper.ManagementWishlist;
import com.example.madmp.Interface.ChangeNumberItemsListener;
import com.example.madmp.R;
import com.example.madmp.domain.CatDomain;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    private ArrayList<CatDomain> catDomains;
    private ManagementWishlist managementWishlist;
    private ChangeNumberItemsListener changeNumberItemsListener;

    public CartListAdapter(ArrayList<CatDomain> catDomains, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.catDomains = catDomains;
        this.managementWishlist = new ManagementWishlist(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(catDomains.get(position).getTitle());
        holder.feeEachItem.setText(String.valueOf(catDomains.get(position).getFee()));
        holder.totalEachAnimal.setText(String.valueOf(Math.round((catDomains.get(position).getNumberInCart() * catDomains.get(position).getFee()) * 100) / 100));
        holder.num.setText(String.valueOf(catDomains.get(position).getNumberInCart()));

        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(catDomains.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.pic);

        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementWishlist.plusNumberCat(catDomains,position,new ChangeNumberItemsListener(){
                    @Override
                    public void changed(){
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementWishlist.minusNumberCat(catDomains,position,new ChangeNumberItemsListener(){
                    @Override
                    public void changed(){
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return catDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,feeEachItem;
        ImageView pic,plusItem,minusItem;
        TextView totalEachAnimal,num;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.titleTxt);
            feeEachItem=itemView.findViewById(R.id.feeEachAnimal);
            pic=itemView.findViewById(R.id.picW);
            totalEachAnimal = itemView.findViewById(R.id.totalEachAnimal);
            num=itemView.findViewById(R.id.numberATxt);
            plusItem=itemView.findViewById(R.id.plusWbtn);
            minusItem = itemView.findViewById(R.id.minusWbtn);
        }
    }
}
