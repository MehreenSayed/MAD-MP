package com.example.madmp.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.madmp.Interface.ChangeNumberItemsListener;
import com.example.madmp.domain.CatDomain;

import java.util.ArrayList;

public class ManagementWishlist{
    private Context context;
    private TinyDB tinyDB;

    public ManagementWishlist(Context context) {
        this.context = context;
        this.tinyDB=new TinyDB(context);
    }
    public void insertCat(CatDomain item){
        ArrayList<CatDomain> listCat=getListWish();
        boolean existAlready=false;
        int n=0;
        for(int i=0;i<listCat.size();i++){
            if(listCat.get(i).getTitle().equals(item.getTitle())){
                existAlready=true;
                n=i;
                break;
            }
        }
        if(existAlready){
            listCat.get(n).setNumberInCart(item.getNumberInCart());
        }else {
            listCat.add(item);
        }
        tinyDB.putListObject("WishList", listCat);
        Toast.makeText(context,"Added to wishlist",Toast.LENGTH_SHORT).show();
    }

    public ArrayList<CatDomain> getListWish(){
        return tinyDB.getListObject("WishList");
    }

    public void plusNumberCat(ArrayList<CatDomain> listCat, int position, ChangeNumberItemsListener changeNumberItemsListener){
        listCat.get(position).setNumberInCart(listCat.get(position).getNumberInCart() + 1);
        tinyDB.putListObject("Wishlist", listCat);
        changeNumberItemsListener.changed();
    }

    public void minusNumberCat(ArrayList<CatDomain> listCat,int position,ChangeNumberItemsListener changeNumberItemsListener){
        if(listCat.get(position).getNumberInCart()==1){
            listCat.remove(position);
        }else {
            listCat.get(position).setNumberInCart(listCat.get(position).getNumberInCart()-1);
        }
        tinyDB.putListObject("Wishlist", listCat);
        changeNumberItemsListener.changed();
    }

    public Double getTotalFee(){
        ArrayList<CatDomain> listCat=getListWish();
        double fee=0;
        for (int i=0;i<listCat.size();i++){
            fee=fee+(listCat.get(i).getFee()*listCat.get(i).getNumberInCart());
        }
        return fee;
    }
}
