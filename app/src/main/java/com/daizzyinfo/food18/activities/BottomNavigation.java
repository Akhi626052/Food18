package com.daizzyinfo.food18.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.daizzyinfo.food18.R;
import com.daizzyinfo.food18.fragments.FragmentHome;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class BottomNavigation extends AppCompatActivity {

    ChipNavigationBar chip_nav_bar;
    @Override
    protected void onResume() {
        chip_nav_bar.setItemSelected(R.id.navigation_home,true);
        openFragment(new FragmentHome());
 //    loadFrag(new HomeFragment(),true);
        super.onResume();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        chip_nav_bar = findViewById(R.id.chip_nav_bar);


        chip_nav_bar.setItemSelected(R.id.navigation_home,true);
        chip_nav_bar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {

            @Override
            public void onItemSelected(int item) {

                int  id = item;

                if(id==R.id.navigation_home)
                {
                    openFragment(new FragmentHome());

                }
                else if(id==R.id.My_Order)
                {

                    Intent intent = new Intent(BottomNavigation.this, MyOrder.class);
                    startActivity(intent);

                }else if(id==R.id.nav_myCart)
                {
                    Intent intent = new Intent(BottomNavigation.this, MyCart.class);
                    startActivity(intent);

                }
                else if(id==R.id.navigation_Setting)
                {
                    Intent intent = new Intent(BottomNavigation.this, Setting.class);
                    startActivity(intent);
                }

            }

        });


    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(BottomNavigation.this);
        builder.setMessage("Are you sure! , you want to exit?");
        builder.setTitle("Confirm Exit.......!!!");
        builder.setIcon(R.drawable.waning);
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            super.onBackPressed();
            finishAffinity();

        });
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });
        builder.setNeutralButton("Cancel", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.dismiss();


        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

}