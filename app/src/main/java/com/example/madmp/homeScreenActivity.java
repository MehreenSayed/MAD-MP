package com.example.madmp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.madmp.Adapter.CatHAdapter;
//import com.example.madmp.domain.catHDomain;
import com.example.madmp.Adapter.CatAdapter;
import com.example.madmp.More.CareActivity;
import com.example.madmp.More.CatFoodActivity;
import com.example.madmp.More.EmergencyServicesActivity;
import com.example.madmp.domain.CatDomain;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Locale;

public class homeScreenActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    TextView User;
    Button logout;

    private RecyclerView.Adapter adapter2;
    private RecyclerView recyclerViewCatList;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen2);

        mAuth=FirebaseAuth.getInstance();
        User=findViewById(R.id.user);
        String email = mAuth.getCurrentUser().getEmail();
        User.setText(email);

        logout=findViewById(R.id.logout);
        logout.setOnClickListener(view -> {
            mAuth.signOut();
            Toast.makeText(homeScreenActivity.this,"Logged out successfully",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(homeScreenActivity.this,login.class));
        });

        recyclerViewCat();
        recyclerViewCat2();
        bottomNavigation();
        CatFoodOnclick();
        CatESOnclick();
        CatCareOnclick();

    }

    private void CatFoodOnclick() {
        ImageView catFood=findViewById(R.id.catFoodImg);
        catFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(homeScreenActivity.this, CatFoodActivity.class));
            }
        });
    }

    private void CatESOnclick() {
        ImageView catFood=findViewById(R.id.catESImg);
        catFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(homeScreenActivity.this, EmergencyServicesActivity.class));
            }
        });
    }

    private void CatCareOnclick() {
        ImageView catFood=findViewById(R.id.catCareImg);
        catFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(homeScreenActivity.this, CareActivity.class));
            }
        });
    }

    private void bottomNavigation(){
        FloatingActionButton floatingActionButton=findViewById(R.id.wishlistBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(homeScreenActivity.this,CartActivity.class));
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user=mAuth.getCurrentUser();
        if(user==null){
            startActivity(new Intent(homeScreenActivity.this,login.class));
        }
    }

    private void recyclerViewCat(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCatList=findViewById(R.id.recyclerView);
        recyclerViewCatList.setLayoutManager(linearLayoutManager);

        ArrayList<CatDomain> catList=new ArrayList<>();
        catList.add(new CatDomain("Ragdoll cat","ragdoll_cat","The Ragdoll cat is a breed of domestic cat known for its large size and docile, relaxed temperament. They are characterized by their blue eyes, long silky coat, and a tendency to flop or go limp when picked up, which is where the breed gets its name Ragdoll.",300.0));
        catList.add(new CatDomain("Maine Coon Cat", "maine_coon_cat", "The Maine Coon cat is a large, domesticated cat breed that is known for its distinctive physical appearance, including its long, shaggy coat, bushy tail, and large size. They are one of the oldest natural cat breeds in North America and are known to be very friendly and loving with people.", 400.0));
        catList.add(new CatDomain("British Shorthair Cat", "british_shorthair_cat", "The British Shorthair cat is a popular domestic cat breed that is known for its round face, chubby cheeks, and stocky build. They have a dense, soft, and plush coat that comes in a variety of colors. They are a calm and easy-going breed that makes great companions for families and individuals.", 450.0));
        catList.add(new CatDomain("Scottish Fold Cat","scottish_fold_cat","The Scottish Fold cat is a domestic cat breed that is known for its unique folded ears, which give them a distinctive \"owl-like\" appearance. They have a medium-to-large body and a round head. They have a wide variety of coat colors and patterns and can have both short and long hair. They are known for being affectionate, intelligent, and adaptable cats that make great companions.",500.0));
        catList.add(new CatDomain("Sphynx Cat","sphynx_cat","The Sphynx cat is a breed of cat known for its hairless appearance. They are affectionate and sociable animals that make good pets. They are also known for their intelligence and playfulness. They require regular grooming to keep their skin clean and healthy, and they can be prone to certain health issues such as skin allergies. They come in a variety of colors and patterns and are recognized by major cat registries. They are a rare breed.",200.0));
        catList.add(new CatDomain("Siamese Cat","siamese_cat","The Siamese cat is a breed of domestic cat that originated in Thailand, also known as Siam. They are known for their distinctive coloring, with a pale body and darker points on the face, ears, paws, and tail. They have a slim build, long bodies, and tails and have a sociable and talkative nature. Siamese cats are known to be very vocal and may become quite chatty with their owners.",250.0));
        catList.add(new CatDomain("Persian Cat","persian_cat","The Persian cat is a long-haired breed of cat characterized by its round face and shortened muzzle. They have calm and docile personalities, making them a popular choice as household pets. They come in a variety of colors and patterns, including white, silver, and bicolor. They require regular grooming to maintain their long, thick fur.",440.0));
        catList.add(new CatDomain("Bengal Cat","bengal_cat","The Bengal cat is a breed of domestic cat that was created by breeding an Asian leopard cat with a domestic cat. They are known for their unique and exotic appearance, with a coat that is covered in spots, marbled patterns, or rosettes. They are typically larger than most domestic cats and have a strong, athletic build.",350.0));
        catList.add(new CatDomain("Burmese Cat","burmese_cat","The Burmese cats are a breed of domestic cat that originated in Southeast Asia and was developed in the United States in the 1930s. They are known for their short, shiny, and close-lying coat that comes in a variety of colors, including sable, brown, blue, and champagne. Burmese cats are known for their distinctive roundness and their muscular, athletic build.",550.0));
        catList.add(new CatDomain("Russian Blue Cat", "russian_blue_cat", "The Russian Blue cat is a breed of domestic cat with a distinctive blue-gray coat. They are known for their intelligence, affectionate nature, and playful personalities. They are generally considered to be a healthy and hardy breed, with a lifespan of around 15 years. They are also known for their low shedding and hypoallergenic coat, making them a popular choice for people with allergies.", 750.0));

        adapter2=new CatAdapter(catList);
        recyclerViewCatList.setAdapter(adapter2);
    }

    private void recyclerViewCat2(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCatList=findViewById(R.id.recyclerView2);
        recyclerViewCatList.setLayoutManager(linearLayoutManager);

        ArrayList<CatDomain> catList=new ArrayList<>();
        catList.add(new CatDomain("Exotic Shorthair Cat","exotic_shorthair_cat","Exotic cats are bred to meet the Persian standard in almost every way with one exception: their coats. Exotics, unlike their Persian counterparts, have short, thick, dense coats, making them popular among people who enjoy the Persian personality but don’t want the hassle or the time required for daily grooming.",350.0));
        catList.add(new CatDomain("American Shorthair Cat", "american_shorthair_cat", "The breed is known to have a very even temperament with a good disposition and keen intelligence. Another testament to its mellow nature is the shorthair’s ability to get along with other pets and its gentle nature around children. A shorthair is considered an ideal pet for a working family with children.", 400.0));
        catList.add(new CatDomain("Abyssinian Cat", "abyssinian_cat", "Lively and expressive, with slightly wedge-shaped heads, half-cupped ears, medium length bodies and well-developed muscles, Abyssinians have long, slender legs and their coats are short and close-lying to their bodies. Their overall appearance bears a resemblance to ancient Egyptian cats. Abyssinians are not lap cats, but are affectionate, loyal and normally mix well with children and other pets. Known for their curiosity, playfulness and need to explore their surroundings, Abyssinians are people-oriented cats who like to participate in the activities taking place around them.", 450.0));
        catList.add(new CatDomain("Devon Rex Cat","devon_rex_cat","The Devon Rex is a relatively newer breed of cats, discovered by accident in the region of Devonshire, England, in 1960 and has been called many things: a pixie cat, an alien cat, a cat that looks like an elf — or a bat. It is also known to behave more like a dog than like a cat. With its unique appearance, the breed has captured the attention of cat lovers worldwide—and the hearts of its families with its lovable, quirky and mischievous personality.",500.0));
        catList.add(new CatDomain("Munchkin Cat","munchkin_cat","The munchkin cat gone on to secure itself in the world of cat lovers, known for its affectionate, playful and clever nature. One of the characteristics shared by many munchkin cats is their ability to perch on their hind legs like prairie dogs.",200.0));
        catList.add(new CatDomain("Bombay Cat","bombay_cat","The Bombay cat has golden eyes and a black coat that's soft as velvet and shiny as patent leather. With a tight, muscular body, she sways as she walks, with a bearing similar to that of the Indian black leopard. For all her athletic agility, she’s also an affectionate companion with a mellow disposition.",250.0));
        catList.add(new CatDomain("Birman Cat","birman_cat","Birmans are another color-pointed cat like the Siamese and ragdoll. They have blue eyes and a medium-long coat but no undercoat, the primary trait that sets them apart from Persians and Himalayans. Birmans were the original stock for breeding ragdolls, so they look very similar, but they have slightly different markings and personalities. They are fun, social cats that love attention—from their chosen person. They are more of a one-person cat than others.",440.0));
        catList.add(new CatDomain("Oriental Shorthair Cat","oriental_shorthair_cat","At a glance, you may think this slender cat looks like a Siamese, but the Oriental shorthair is a separate breed. Unlike Siamese cats, they usually have green eyes and many color patterns and colors. The Oriental can also have long hair. Oriental shorthair cats are prone to skin cancer and getting cold due to their lack of fur; its often recommended to wear sweaters.",350.0));
        catList.add(new CatDomain("Himalayan Cat","himalayan_cat","Like the Persian, the Himalayan cat is considered a sub-breed in some cat associations and a separate breed in others. Himalayans were bred from crossing Persians and Siamese to achieve their color points. They love attention and affection from their owners, have sweet personalities, and are playful.",550.0));
        catList.add(new CatDomain("Egyptian mau", "egyptian_mau", "They are known for their athleticism and are some of the fastest runners among domestic cats–reaching speeds of up to 30 miles an hour. In addition to their striking appearance as one of the few naturally spotted cat breeds in the world, Egyptian maus are friendly and interactive with their family members. Within the family, they may form a strong bond with one select person. The Egyptian mau tends to be aloof with strangers, though they will warm up to new people when given time.", 750.0));

        adapter2=new CatAdapter(catList);
        recyclerViewCatList.setAdapter(adapter2);
    }
}