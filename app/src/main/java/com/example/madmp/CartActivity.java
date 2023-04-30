package com.example.madmp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.madmp.Adapter.CartListAdapter;
import com.example.madmp.Helper.ManagementWishlist;
import com.example.madmp.Interface.ChangeNumberItemsListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CartActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementWishlist managementWishlist;
    TextView totalFeetxt,taxtext,deliverytxt,totaltxt,emptytxt;
    private double tax;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        managementWishlist=new ManagementWishlist(this);
        initView();
        initList();
        CalculateWishlist();
       // bottomNavigation();
    }

//    private void bottomNavigation(){
//        FloatingActionButton floatingActionButton=findViewById(R.id.wishlistBtn);
//
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(CartActivity.this,CartActivity.class));
//            }
//        });
//    }
    private void initView(){
        recyclerViewList=findViewById(R.id.recyclerViewC);
        totalFeetxt=findViewById(R.id.totalFeeTxt);
        taxtext=findViewById(R.id.taxTxt);
        deliverytxt=findViewById(R.id.deliveryTxt);
        totaltxt=findViewById(R.id.totalTxt);
        emptytxt=findViewById(R.id.emptyTxt);
        scrollView=findViewById(R.id.sv);
    }
    private void initList(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter=new CartListAdapter(managementWishlist.getListWish(),this,new ChangeNumberItemsListener(){
            @Override
            public void changed(){
                CalculateWishlist();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if(managementWishlist.getListWish().isEmpty()){
            emptytxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else{
            emptytxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void CalculateWishlist(){
        double percentTax=0.02;
        double delivery=10;

        tax=Math.round((managementWishlist.getTotalFee()*percentTax)*100)/100;
        double total=Math.round((managementWishlist.getTotalFee()+tax+delivery)*100)/100;
        double animalTotal=Math.round(managementWishlist.getTotalFee()*100)/100;

        totalFeetxt.setText("₹" + animalTotal);
        taxtext.setText("₹" + tax);
        deliverytxt.setText("₹" + delivery);
        totaltxt.setText("₹"+total);
    }
}