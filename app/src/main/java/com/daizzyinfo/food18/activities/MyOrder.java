package com.daizzyinfo.food18.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.daizzyinfo.food18.R;
import com.daizzyinfo.food18.adapter.OrderHistoryAdapter;
import com.daizzyinfo.food18.adapter.OrderUpcomingAdapter;
import com.daizzyinfo.food18.model.OrderHistoryModel;
import com.daizzyinfo.food18.model.OrderUpcomingModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MyOrder extends AppCompatActivity {

 RecyclerView RVUpcoming,RVHistory;


 List<OrderUpcomingModel> model = new ArrayList<>();

  List<OrderHistoryModel> models= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        RVUpcoming = findViewById(R.id.RVUpcoming);
        RVHistory = findViewById(R.id.RVHistory);


        openToolBar();
        initOnClickListener();


        model.add(new OrderUpcomingModel("Order ID: 5678904","24/05/23","Rs. 199","Skye,Earth Corporate Park,Ab Road , 605 ,Indore","2","Upcoming"));
        model.add(new OrderUpcomingModel("Order ID: 5678904","24/05/23","Rs. 199","Skye,Earth Corporate Park,Ab Road , 605 ,Indore","2","Upcoming"));



        OrderUpcomingAdapter adapter = new OrderUpcomingAdapter(this,model);
         RVUpcoming.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        RVUpcoming.setLayoutManager(manager);


        models.add(new OrderHistoryModel("Order ID: 5678904","24/05/23","Rs. 199","Skye,Earth Corporate Park,Ab Road , 605 ,Indore","2","Delivery Failed"));
        models.add(new OrderHistoryModel("Order ID: 5678904","24/05/23","Rs. 199","Skye,Earth Corporate Park,Ab Road , 605 ,Indore","2","Delivery Completed"));
        models.add(new OrderHistoryModel("Order ID: 5678904","24/05/23","Rs. 199","Skye,Earth Corporate Park,Ab Road , 605 ,Indore","2","Delivery Failed"));


        OrderHistoryAdapter adapter1 = new OrderHistoryAdapter(this,models);
        RVHistory.setAdapter(adapter1);

        LinearLayoutManager manager1 = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        RVHistory.setLayoutManager(manager1);





    }



    private void initOnClickListener(){



    }













    protected void openToolBar(){
        View view = findViewById(R.id.Header);
        TextView TxtHeader =view.findViewById(R.id.TxtHeader);
        TxtHeader.setText("My Order");
        ImageView ImgBack=view.findViewById(R.id.ImgBack);
        ImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}