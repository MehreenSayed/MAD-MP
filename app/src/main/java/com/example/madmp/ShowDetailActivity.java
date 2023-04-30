package com.example.madmp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.madmp.Helper.ManagementWishlist;
import com.example.madmp.domain.CatDomain;

public class ShowDetailActivity extends AppCompatActivity {
    private TextView addToWishlistBtn;
    private TextView titleTxt,feeTxt,discriptionTxt,numberOrderTxt;
    private ImageView plusBtn,minusBtn,picCat;
    private CatDomain object;
    int numberOrder=1;
    private ManagementWishlist managementWishlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        managementWishlist=new ManagementWishlist(this);
        initView();
        getBundle();
    }

    private void getBundle() {
        object=(CatDomain) getIntent().getSerializableExtra("object");
        int drawableResourseId=this.getResources().getIdentifier(object.getPic(),"drawable",this.getPackageName());
        Glide.with(this).load(drawableResourseId).into(picCat);

        titleTxt.setText(object.getTitle());
        feeTxt.setText("₹" + object.getFee());
        discriptionTxt.setText(object.getDiscription());
        numberOrderTxt.setText(String.valueOf(numberOrder));

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOrder=numberOrder+1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(numberOrder>1){
                    numberOrder=numberOrder-1;
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        addToWishlistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setNumberInCart(numberOrder);
                managementWishlist.insertCat(object);
            }
        });
    }

    private void initView(){
        addToWishlistBtn=findViewById(R.id.addToWishlistBtn);
        titleTxt=findViewById(R.id.titletxt);
        feeTxt=findViewById(R.id.priceTxt);
        discriptionTxt=findViewById(R.id.discriptionTxt);
        numberOrderTxt=findViewById(R.id.numberOrderTxt);
        plusBtn=findViewById(R.id.plusBtn);
        minusBtn=findViewById(R.id.minusBtn);
        picCat=findViewById(R.id.catPic);
    }
}