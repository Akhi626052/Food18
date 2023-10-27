//package com.daizzyinfo.food18.activities;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.daizzyinfo.food18.adapter.PopularAdapter;
//import com.daizzyinfo.food18.model.PopularModel;
//import com.daizzyinfo.food18.R;
//import com.daizzyinfo.food18.adapter.CtgAdopter;
//import com.daizzyinfo.food18.model.CtgModel;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MainActivity extends AppCompatActivity {
//
//    LinearLayout myAddClick;
//    ImageView Search;
//    TextView PopAllItm;
//    TextView CtgAllItm;
//
//    ImageView img;
//    RecyclerView recyclerViewCtg;
//    RecyclerView recyclerViewPop;
//    List<CtgModel> ctgModels = new ArrayList<>();
//
//    List<PopularModel> popularModels = new ArrayList<>();
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//        ctgModels.add(new CtgModel(R.drawable.burger,"Pizza"));
//        ctgModels.add(new CtgModel(R.drawable.burger,"Pizza"));
//        ctgModels.add(new CtgModel(R.drawable.burger,"Pizza"));
//        ctgModels.add(new CtgModel(R.drawable.burger,"Pizza"));
//        ctgModels.add(new CtgModel(R.drawable.burger,"Pizza"));
//        ctgModels.add(new CtgModel(R.drawable.burger,"Pizza"));
//
//
//        popularModels.add(new PopularModel(R.drawable.food,"Pizza","Rs 199","10 % Off","Extra Pizza","4.5"));
//        popularModels.add(new PopularModel(R.drawable.food1,"Pizza","Rs 199","10 % Off","Extra Pizza","4.5"));
//        popularModels.add(new PopularModel(R.drawable.food2,"Pizza","Rs 199","10 % Off","Extra Pizza","4.5"));
//        popularModels.add(new PopularModel(R.drawable.food3,"Pizza","Rs 199","10 % Off","Extra Pizza","4.5"));
//
//
//
//
//        recyclerViewCtg=findViewById(R.id.CtgRecyclerview);
//
//        recyclerViewPop=findViewById(R.id.PopularRecyclerview);
//
//
//
//        CtgAdopter ctgAdopter = new CtgAdopter(ctgModels,this);
//        recyclerViewCtg.setAdapter(ctgAdopter);
//
//        LinearLayoutManager managerCtg = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
//        recyclerViewCtg.setLayoutManager(managerCtg);
//
//
//
//        PopularAdapter popularAdapter = new PopularAdapter(popularModels,this);
//        recyclerViewPop.setAdapter(popularAdapter);
//
//        GridLayoutManager manager2 = new GridLayoutManager(this,2,LinearLayoutManager.VERTICAL,false);
//        recyclerViewPop.setLayoutManager(manager2);
//
//
//        CtgAllItm = findViewById(R.id.CtgAllItm);
//        CtgAllItm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, AllCategories.class);
//                startActivity(intent);
//            }
//        });
//
//        PopAllItm=findViewById(R.id.PopAllItm);
//        PopAllItm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, AllItems.class);
//                startActivity(intent);
//            }
//        });
//
//        img=findViewById(R.id.MyCart);
//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, MyCart.class);
//                startActivity(intent);
//            }
//        });
//
//        Search = findViewById(R.id.Search);
//        Search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, SearchScreen.class);
//                startActivity(intent);
//            }
//        });
//
//
//        myAddClick=findViewById(R.id.myAddClick);
//        myAddClick.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, MyAddress.class);
//                startActivity(intent);
//            }
//        });
//
//
//    }
//}