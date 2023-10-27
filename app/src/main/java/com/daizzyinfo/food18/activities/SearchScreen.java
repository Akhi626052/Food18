package com.daizzyinfo.food18.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.daizzyinfo.food18.R;
import com.daizzyinfo.food18.adapter.SearchAdapter;
import com.daizzyinfo.food18.model.SearchModel;

import java.util.ArrayList;
import java.util.List;

public class SearchScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        RecyclerView recyclerViewSearch;
        List<SearchModel> searchModels = new ArrayList<>();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);

        searchModels.add(new SearchModel(R.drawable.food1,"Pizza","Extra Pizza","4.5","199 Rs."));
        searchModels.add(new SearchModel(R.drawable.food2,"Burger","Extra Burger","4.6","299 Rs."));
        searchModels.add(new SearchModel(R.drawable.food3,"Pizza","Extra Pizza","4.5","199 Rs."));
        searchModels.add(new SearchModel(R.drawable.food4,"Burger","Extra Burger","4.6","299 Rs."));


        @SuppressLint({"MissingInflatedId", "LocalSuppress"})

        ImageView backImgSearch = findViewById(R.id.backImgSearch);

      recyclerViewSearch=findViewById(R.id.RVSearch);

        SearchAdapter searchAdapter = new SearchAdapter(searchModels,this);
        recyclerViewSearch.setAdapter(searchAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewSearch.setLayoutManager(manager);

        backImgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}